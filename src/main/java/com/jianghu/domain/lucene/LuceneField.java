package com.jianghu.domain.lucene;

/**
 * lucune字段,自定义字段，跟随业务变动
 * @author wangjinlong
 *
 * @creatTime 2017年9月29日 下午8:26:20
 */
public class LuceneField {
	//业务主键
	public Long no;
	//名称
	public String name;
	//内容
	public String content;
	
	public LuceneField(){
		
	}
	public LuceneField(Long no, String name, String content) {
		super();
		this.no = no;
		this.name = name;
		this.content = content;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
