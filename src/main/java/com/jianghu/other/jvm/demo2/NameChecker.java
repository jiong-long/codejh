package com.jianghu.other.jvm.demo2;

import java.util.EnumSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic.Kind;

/**
 * 程序名称规范的编译期插件
 * 如果程序命令不合规范，将会输出一个编译器的 WARNING 信息
 * 
 * @author wangjinlong
 * @datetime May 24, 2020 3:47:05 PM
 *
 */
public class NameChecker {
	private final Messager messager;
	
	public enum KeyWord {
		PUBLIC, STATIC, FINAL
	};

	
	NameCheckScanner nameCheckScanner = new NameCheckScanner();
	
	public NameChecker(ProcessingEnvironment processingEnv) {
		this.messager = processingEnv.getMessager();
	}

	/**
	 * 对Java方法程序名称进行检查
	 * 类或接口：符合 驼式命令法，首字母大写
	 * 方法、字段、实例变量：符合 驼式命令法，首字母小写
	 * 常量：全部大写
	 * 
	 * @param element
	 */
	public void chechNames(Element element) {
		nameCheckScanner.scan(element);
	}
	
	/**
	 * 名称检查器实现类，继承了 JDK1.8中新提供的ElementScanner8
	 * 将会以 Visitor 模式访问抽象语法书中的元素
	 * 
	 * @author wangjinlong
	 * @datetime May 24, 2020 3:55:08 PM
	 *
	 */
	public class NameCheckScanner extends ElementScanner8<Void, Void>{
		
		/**
		 * 检查Java类
		 */
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			super.visitType(e, p);
			return null;
		}
		
		/**
		 * 检查方法命令是否合法
		 * @return
		 */
		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if(e.getKind() == ElementKind.METHOD) {
				Name name = e.getSimpleName();
				if(name.contentEquals(e.getEnclosingElement().getSimpleName())) {
					messager.printMessage(Kind.WARNING, "一个普通方法" + name + "不应该与类名重复，避免与构造函数产生混淆", e);
				}
				checkCamelCase(e, false);
			}
			super.visitExecutable(e, p);
			return null;
		}
		
		/**
		 * 检查变量命令是否合法
		 */
		@Override
		public Void visitVariable(VariableElement e, Void p) {
			//如果这个 Variable 是枚举或常量，则按大写命令检查，否则按照驼式命令法规则检查
			if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
				checkAllCaps(e);
			} else {
				checkCamelCase(e, false);
			}
			return null;
		}
		
		/**
		 * 大写命令检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母
		 * @param e
		 */
		private void checkAllCaps(VariableElement e) {
			String name = e.getSimpleName().toString();
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if(!Character.isUpperCase(firstCodePoint)) {
				conventional = false;
			} else {
				boolean previousUnderscore = false;
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if(cp == (int) '_') {
						if(previousUnderscore) {
							conventional = false;
							break;
						}
						previousUnderscore = true;
					} else {
						previousUnderscore = false;
						if(!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
							conventional = false;
							break;
						}
					}
				}
			}
			
			if(!conventional) {
				messager.printMessage(Kind.WARNING, "常量" + name + "应当全部以大写字母或下划线命令，并且以字母开头", e);
			}
		}

		/**
		 * 判断一个变量是否是常量
		 * @param e
		 * @return
		 */
		@SuppressWarnings("unlikely-arg-type")
		private boolean heuristicallyConstant(VariableElement e) {
			if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
				return true;
			} else if(e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(KeyWord.PUBLIC, KeyWord.STATIC, KeyWord.FINAL))) {
				return true;
			}
			return false;
		}

		/**
		 * 检查传入的 Element 是否符合驼式命令法，如果不符合，则输出警告信息
		 * @param e
		 * @param b
		 */
		private void checkCamelCase(Element e, boolean b) {
			String name = e.getSimpleName().toString();
			boolean previousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if(Character.isUpperCase(firstCodePoint)) {
				previousUpper = true;
				if(!b) {
					messager.printMessage(Kind.WARNING, "名称" + name + "应当以小写字母开头", e);
					return;
				}
			} else if(Character.isLowerCase(firstCodePoint)) {
				if(b) {
					messager.printMessage(Kind.WARNING, "名称" + name + "应当以大写字母开头", e);
					return;
				}
			} else {
				conventional = false;
			}
			
			if(conventional) {
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if(Character.isUpperCase(cp)) {
						if(previousUpper) {
							conventional = false;
							break;
						}
						previousUpper = true;
					} else {
						previousUpper = false;
					}
				}
			}
			
			if(!conventional) {
				messager.printMessage(Kind.WARNING, "名称" + name + "应当符合驼式命令法", e);
			}
		}
	}
}
