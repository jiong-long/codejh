package com.jianghu.other.jvm;

import java.lang.reflect.Method;

/**
 * JavaClass 执行工具
 * @author wangjinlong
 *
 */
public class JavaClassExecuter {
	/**
	 * 执行外部传过来的代表一个 Java 类的 byte 数组
	 * 将输入类的 byte 数组中代表 java.lang.System 的 CONSTANT_Utf8_info 常量修改为劫持后的 HackSystem 类
	 * 执行方法为该类的 static main(String[] args）方法，输出结果为该类向 System.out/err 输出的信息
	 * 
	 * @param classBytes 代表一个 Java 类的 byte 数组
	 * @return 执行结果
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String execute(byte[] classBytes) {
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classBytes);
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/jianghu/other/jvm/HackSystem");
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class clazz = loader.loadByte(modiBytes);
		try {
			Method method = clazz.getMethod("main", String[].class);
			method.invoke(null, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace(HackSystem.out);
		}
		return HackSystem.getBufferString();
	}
}
