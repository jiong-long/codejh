package com.common;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKSimilarity;

import com.jianghu.domain.lucene.AnalysisEnum;
import com.jianghu.domain.lucene.LuceneField;

/**
 * lucene相关工具类
 * 
 * @author wangjinlong
 * 
 * @creatTime 2017年9月28日 下午2:25:28
 */
public class LuceneUtil {
	private static LuceneUtil luceneUtil = new LuceneUtil();
	// 版本
	private static Version version = Version.LUCENE_35;
	// lucene文件存储路径文件
	// System.getProperty("user.dir")开发环境中可能是由于部署方式不同，所以获取的是eclipse的路径，暂时写死
	// private static File index_file = new File(System.getProperty("user.dir")
	// +
	// "\\" + "jiong\\lucene");
	private static File index_file = new File("C:\\" + "jiong\\lucene");
	// 分词器
	private static Analyzer analyzer = AnalysisEnum.IKANALYZER;
	// 查询分析器
	private static QueryParser parser;
	// 高亮前缀
	private static String prefixHTML = "<font color='red'>";
	// 高亮后缀
	private static String suffixHTML = "</font>";

	private static IndexWriter indexWriter;

	private static IndexReader indexReader;

	public static LuceneUtil instance() {
		return luceneUtil;
	}

	public static void main(String[] args) throws Exception {
		LuceneField field = new LuceneField();
		field.setNo(110L);
		field.setName("兔宝宝");
		field.setContent("中华人民共和国河南省信阳市111");

		LuceneField field1 = new LuceneField();
		field1.setNo(120L);
		field1.setName("牛宝宝");
		field1.setContent("其他国家都没有中间的国好");

		List<LuceneField> list = new ArrayList<LuceneField>();
		list.add(field);
		list.add(field1);

		LuceneUtil luceneUtil = LuceneUtil.instance();

		// luceneUtil.deleteAllIndex();
		luceneUtil.writeIndex(list);
		// luceneUtil.writeIndex(field1);
		// luceneUtil.updateDocument(field1);
		// luceneUtil.deleteIndexById("120");
		// List<LuceneField> listS = luceneUtil.search("宝宝");
		// for (LuceneField luceneField : listS) {
		// System.out.println(luceneField.getName());
		// }
	}

	/**
	 * 查询
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午11:25:04
	 * @param keyword
	 * @return
	 * @throws IOException
	 */
	public List<LuceneField> search(String keyword) throws Exception {
		IndexSearcher searcher = null;
		List<LuceneField> list = new ArrayList<LuceneField>();
		try {
			if (index_file != null && index_file.exists() && index_file.isDirectory() && index_file.listFiles().length > 1) {
				indexReader = IndexReader.open(FSDirectory.open(index_file));
				searcher = new IndexSearcher(indexReader);
				searcher.setSimilarity(new IKSimilarity());
				if (keyword.indexOf("name:") < 0) {
					keyword = "(name:" + keyword + " OR content:" + keyword + ")";
				}
				// 第二个参数，默认在哪个属性中查找，属性名称为new Field()的第一个字段
				parser = new QueryParser(version, "name", analyzer);
				// 支持*开头的查询条件，但是开销较大
				parser.setAllowLeadingWildcard(true);
				Query query = parser.parse(keyword);
				// true,降序排列
				Sort sort = new Sort(new SortField("no", SortField.INT, true));
				// 暂未找到查询所有的方法，可能lucene是针对大数量的
				TopDocs topDocs = searcher.search(query, 10, sort);
				// 获取所有的查询结果，与对应的得分
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				// 暂时写死10个，正常的业务中应为每页的数量
				int end = topDocs.totalHits > 10 ? 10 : topDocs.totalHits;
				for (int i = 0; i < end; i++) {
					Document doc = searcher.doc(scoreDocs[i].doc);
					LuceneField luceneField = new LuceneField();

					// 高亮显示命中的词
					SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(prefixHTML, suffixHTML);
					Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
					highlighter.setTextFragmenter(new SimpleFragmenter());

					String name = highlighter.getBestFragment(analyzer, "name", doc.get("name"));
					if (name == null) {
						luceneField.setName(doc.get("name"));
					} else {
						luceneField.setName(name);
					}

					String content = highlighter.getBestFragment(analyzer, "content", doc.get("content"));
					if (content == null)
						luceneField.setContent(doc.get("content"));
					else {
						luceneField.setContent(content);
					}
					luceneField.setNo(Tools.object2Long(doc.get("no")));
					list.add(luceneField);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeIndexSearcher(searcher);
			closeIndexReader();
		}

		return list;
	}

	/**
	 * 更新Document
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午10:34:04
	 * @param luceneField
	 * @throws Exception
	 */
	public void updateDocument(LuceneField luceneField) throws Exception {
		Document doc = builderDocument(luceneField);
		// 查询在no字段上出现String.valueOf(luceneField.getNo())值的文档
		Term term = new Term("no", String.valueOf(luceneField.getNo()));
		indexWriter = getIndexWriter();
		indexWriter.updateDocument(term, doc);
		closeIndexWriter();
	}

	/**
	 * 当对应记录删除时，删除单个索引（删除）
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午10:28:32
	 * @param id
	 * @throws Exception
	 */
	public void deleteIndexById(String id) throws Exception {
		Term term = new Term("no", String.valueOf(id));
		indexWriter = getIndexWriter();
		indexWriter.deleteDocuments(term);
		closeIndexWriter();
	}

	/**
	 * 删除文档包括文件（批量删除）
	 * 
	 * @author wangjinlong
	 * @throws IOException
	 * @throws CorruptIndexException
	 * @throws Exception
	 * @creatTime 2017年9月29日 下午10:19:50
	 */
	public void deleteAllIndex() throws Exception {
		// 删除所有文档后会新建，所以直接删除文件即可
		deleteAllFile(index_file);
	}

	/**
	 * 删除所有的索引文件(删除文件)
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午10:10:21
	 */
	private void deleteAllFile(File indexFile) {
		File[] files = indexFile.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				// 不要删除锁文件
				if (!"write.lock".equals(files[i].getName())) {
					files[i].delete();
				}
			}
		}
	}

	/**
	 * 批量将LuceneField对象变为索引对象（批量增加）
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午10:06:44
	 * @param list
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public void writeIndex(List<LuceneField> list) throws Exception {
		for (LuceneField luceneField : list) {
			writeDocumen(luceneField);
		}
	}

	/**
	 * 将LuceneField对象变为索引对象，写索引（增加）
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午10:04:10
	 * @param luceneField
	 * @throws Exception
	 */
	public void writeIndex(LuceneField luceneField) throws Exception {
		writeDocumen(luceneField);
	}

	/**
	 * 将document对象写入到索引对象中去
	 * 
	 * @creatTime 2017年10月2日 下午12:17:10
	 * @author jinlong
	 * @param luceneField
	 * @throws Exception
	 */
	private void writeDocumen(LuceneField luceneField) throws Exception {
		Document document = builderDocument(luceneField);
		indexWriter = getIndexWriter();
		indexWriter.addDocument(document);
		closeIndexWriter();
	}

	/**
	 * lucene核心API
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午9:12:47
	 * @return
	 * @throws IOException
	 */
	// 在Lucene程序中，成功创建IndexWriter对象以后，会在索引库中出现一个锁文件，
	// 这个锁文件是当前这个IndexWriter的锁，如果调用indexWriter.close()关闭了链接，
	// 则将会把这个锁文件删除，也就是说，它释放了锁。释放以后，第二个IndexWriter再开启是没有问题的。
	// 如果不关闭，同时开了两个或以上的IndexWriter，会抛出一个异常。导致程序终止。
	// 解决方案就是：通过某种方法保证IndexWriter对象只创建一个。
	private static IndexWriter getIndexWriter() throws IOException {
		if (indexWriter == null) {
			synchronized (LuceneUtil.class) {
				if (indexWriter == null) {
					IndexWriterConfig indexWriterConfig = new IndexWriterConfig(version, analyzer);
					// OpenMode.CREATE 创建或覆盖
					// OpenMode.APPEND 追加
					// OpenMode.CREATE_OR_APPEND 如果不存在则创建，否则追加
					indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

					// 索引update、deleted、add、update、deleted、add反反复复，导致索引“千仓百孔”、“指针琳琳散散”、
					// “无用数据或者辅助数据增多”，最后影响相同的查询逻辑，越到后面检索性能逐渐糟糕。使用该方法将索引合并，替代writer.optimize();
					LogMergePolicy mergePolicy = new LogByteSizeMergePolicy();
					// 达到3个文件时就和合并
					mergePolicy.setMergeFactor(5);
					indexWriterConfig.setMergePolicy(mergePolicy);

					// 是控制写入一个新的segment前内存中保存的document的数目，设置较大的数目可以加快建索引速度，默认为10。
					indexWriterConfig.setMaxBufferedDocs(100);
					IndexWriter writer = new IndexWriter(FSDirectory.open(index_file), indexWriterConfig);
					return writer;
				}
			}
		}
		return indexWriter;
	}

	/**
	 * 关闭IndexWriter
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年10月1日 上午1:58:01
	 * @throws Exception
	 */
	private static void closeIndexWriter() throws Exception {
		if (indexWriter != null) {
			indexWriter.close();
		}
		indexWriter = null;
	}

	/**
	 * 关闭IndexReader
	 * 
	 * @creatTime 2017年10月2日 下午8:27:11
	 * @author jinlong
	 * @param indexReader
	 * @throws Exception
	 */
	private static void closeIndexReader() throws Exception {
		if (indexReader != null) {
			indexReader.close();
		}
		indexReader = null;
	}

	private static void closeIndexSearcher(IndexSearcher indexSearcher) throws Exception {
		if (indexSearcher != null) {
			indexSearcher.close();
		}
		indexSearcher = null;
	}

	/**
	 * 将lucene相关字段转换为document对象
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月29日 下午8:34:24
	 * @param luceneField
	 * @return
	 */
	private Document builderDocument(LuceneField luceneField) {
		// Field.Store.YES:存储字段值（未分词前的字段值）
		// Field.Store.NO:不存储,存储与索引没有关系

		// Field.Index.ANALYZED:分词建索引
		// Field.Index.ANALYZED_NO_NORMS:分词建索引，但是Field的值不像通常那样被保存，而是只取一个byte，这样节约存储空间
		// Field.Index.NOT_ANALYZED:不分词且索引，把整个内容作为一个索引
		// Field.Index.NOT_ANALYZED_NO_NORMS:不分词建索引，Field的值去一个byte保存

		Document document = new Document();
		Field id = new Field("no", String.valueOf(luceneField.getNo()), Field.Store.YES, Field.Index.NOT_ANALYZED);
		Field title = new Field("name", HtmlUtil.removeHtml(luceneField.getName()), Field.Store.YES, Field.Index.ANALYZED);
		// 该字段为主要字段，计算得分的时候会比重会比较大
		title.setBoost(10.0F);
		Field content = new Field("content", HtmlUtil.removeHtml(luceneField.getContent()), Field.Store.YES, Field.Index.ANALYZED);
		document.add(id);
		document.add(title);
		document.add(content);
		return document;
	}

	/**
	 * 获取分词结果 ,便于调试与增加相关自定义字典
	 * 
	 * @param 输入的字符串
	 * @return 分词结果
	 * @throws IOException
	 */
	public static List<String> getWords(String query) throws IOException {
		List<String> result = new ArrayList<String>();
		TokenStream stream = null;
		try {
			stream = analyzer.tokenStream("", new StringReader(query));
			CharTermAttribute attr = stream.addAttribute(CharTermAttribute.class);
			stream.reset();
			while (stream.incrementToken()) {
				result.add(attr.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
