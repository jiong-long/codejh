package com.jianghu.other.jvm.demo2;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;

//可以用 * 表示支持所有的 Annotations
@SupportedAnnotationTypes("*")
//只支持 JDK1.8 的 Java 代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor{
	
	private NameChecker nameChecker;
	
	/**
	 * 初始化名称检查插件
	 */
	@Override
	public void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		nameChecker = new NameChecker(processingEnv);
	}

	/**
	 * 对输入的语法树的各个节点进行名称检查
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if(!roundEnv.processingOver()) {
			for (Element element: roundEnv.getRootElements()) {
				nameChecker.chechNames(element);
			}
		}
		//只对命令进行检查，不需要修改语法树的内容，因此返回都是 false
		return false;
	}

}
