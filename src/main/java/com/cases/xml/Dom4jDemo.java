package com.cases.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * DOM4J读取XML文件
 * DOM4J性能最好，连Sun的JAXM也在用DOM4J。目前许多开源项目中大量采用DOM4J，
 * 例如大名鼎鼎的Hibernate也用DOM4J来读取XML配置文件。如果不考虑可移植性，那就采用DOM4J。
 * @author wangjinlong
 */
public class Dom4jDemo {
    public static void main(String[] args) throws DocumentException {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
        Document document = reader.read(new File("src/com/jianghu/core/func/xml/books.xml"));
        // 通过document对象获取根节点bookstore
        Element bookStore = document.getRootElement();
        // 通过element对象的elementIterator方法获取迭代器
        Iterator<?> it = bookStore.elementIterator();
        // 遍历迭代器，获取根节点中的信息（书籍）
        while (it.hasNext()) {
            System.out.println("=====开始遍历某一本书=====");
            Element book = (Element) it.next();
            // 获取book的属性名以及 属性值
            @SuppressWarnings("unchecked")
			List<Attribute> bookAttrs = book.attributes();
            for (Attribute attr : bookAttrs) {
                System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
            }
            Iterator<?> itt = book.elementIterator();
            while (itt.hasNext()) {
                Element bookChild = (Element) itt.next();
                System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
            }
            System.out.println("=====结束遍历某一本书=====");
        }
    }
}
