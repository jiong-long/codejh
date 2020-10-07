package com.cases.jvm.demo1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * 为 JavaClass 劫持 java.lang.System
 * 除了 out 和 err 外，其余的都直接转发给 System 处理
 * 
 * @author wangjinlong
 *
 */
public class HackSystem {
	public final static InputStream in = System.in;
	private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	public final static PrintStream out = new PrintStream(buffer);
	public final static PrintStream err = out;
	
	public static String getBufferString() {
		return buffer.toString();
	}
	
	public static void clearBuffer() {
		buffer.reset();
	}
	
	public static void setSecurityManager(final SecurityManager s) {
		System.setSecurityManager(s);
	}
	
	public static SecurityManager getSecurManager() {
		return System.getSecurityManager();
	}
	
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static void arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length) {
		System.arraycopy(src, srcPos, dest, destPos, length);
	}
	
	public static int identityHashCode(Object x) {
		return System.identityHashCode(x);
	}
	
	//后面所有的方法都和 System 中一致
}
