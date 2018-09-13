package com.jianghu.core.func.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 将整个 XML 加载内存中，形成文档对象，所有对 XML 操作都对内存中的文档对象进行。
 * 首选 DOM 编程简单
 * @author wangjinlong
 *
 */
public class DomDemo {
	public static void main(String[] args) throws Exception {
		String xmlPath = "src/com/jianghu/core/func/xml/books.xml";
		//readXml(xmlPath);
		//writeXml(xmlPath);
		//addXml(xmlPath);
		//updateXml(xmlPath);
		deleteXml(xmlPath);
	}
	
	public static Document getDocument(String xmlPath) throws Exception{
		// 构建工厂
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 通过工厂获得解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 使用解析器加载xml文档
		return db.parse(xmlPath);
		
	}
	
	/**
	 * 读取XML中的内容
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void readXml(String xmlPath) throws Exception{
		Document document = getDocument(xmlPath);
		// 此时d就是xml文档，通过操作d来过去xml中的元素
		// 将图书的名称打印出来，获取name标签，存储在NodeList集合中
		NodeList nl = document.getElementsByTagName("name");
		// 打印name标签的数量
		System.out.println("图书数量为：" + nl.getLength());
		// 遍历集合
		for (int i = 0; i < nl.getLength(); i++) {
			// 获取节点
			Node node = nl.item(i);
			// 将节点强转为元素，以便获取其中的内容
			Element e = (Element) node;
			// 获取节点名称
			System.out.println(e.getNodeName());// name
			// 获取节点类型，1代表元素
			System.out.println(e.getNodeType());// 1
			// 获取节点的值，null代表元素节点的值
			System.out.println(e.getNodeValue());// null
			// 获取节点的准确名称
			System.out.println(e.getFirstChild().getNodeValue());
			System.out.println(e.getTextContent());
		}
	}
	
	/**
	 * 增加节点
	 * @param xmlPath
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void addXml(String xmlPath) throws Exception{
		Document document = getDocument(xmlPath);
		// 创建一个book节点 <book></book>
		Element newbook = document.createElement("book");
		// 向book中添加属性<book id="b007">
		newbook.setAttribute("id", "b007");
		// 创建一个name节点 <name></name>
		Element newname = document.createElement("name");
		// 向name中写入值 .net
		newname.setTextContent(".net");
		// 将<name></name>加入到 <book></book>中去
		newbook.appendChild(newname);
		// 获取xml的更节点books
		Element root = document.getDocumentElement();
		// 将<book></book>加入到<books></books>
		root.appendChild(newbook);
		
		saveDocument(document);
	}
	
	/**
	 * 修改节点内容
	 * @param xmlPath
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void updateXml(String xmlPath) throws Exception{
		Document document = getDocument(xmlPath);
		//从name开始查找要修改的图书
		NodeList nodelist =	document.getElementsByTagName("name");
		for (int i = 0; i < nodelist.getLength(); i++) {
			//获取当前图书的节点
			Node node = nodelist.item(i);
			if (node.getTextContent().equals("php")) {
				//获取图书的价格
				Element price = (Element)node.getNextSibling().getNextSibling();
				//将String转为int
				int p = Integer.parseInt(price.getTextContent());
				//修改价格
				p = p * 2;
				//将修改后的价格写入内存中
				price.setTextContent(p + "");
			}
		}
		
		saveDocument(document);
	}
	
	public static void deleteXml(String xmlPath) throws Exception{
		Document document = getDocument(xmlPath);
		
		NodeList nodeList =	document.getElementsByTagName("name");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element node = (Element) nodeList.item(i);
			if (node.getTextContent().equals("php")) {
				//获取该节点的父节点<name>
				Element book = (Element) node.getParentNode();
				//通过<name>的父节点来删除<name>
				book.getParentNode().removeChild(book);
				i--;
			}
		}
		
		saveDocument(document);
	}
	
	/**
	 * 将document中元素写入到xml中
	 * @param document
	 * @throws Exception
	 */
	public static void saveDocument(Document document) throws Exception{
		//保存xml文件  
        TransformerFactory transformerFactory = TransformerFactory.newInstance();  
        Transformer transformer = transformerFactory.newTransformer();  
        DOMSource domSource = new DOMSource(document);  
          
        //设置编码类型  
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new FileOutputStream(new File("src/com/jianghu/core/func/xml/back.xml")));  
          
        //把DOM树转换为xml文件  
        transformer.transform(domSource, result); 
	}
}
