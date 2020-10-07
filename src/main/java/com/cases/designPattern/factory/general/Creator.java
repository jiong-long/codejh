package com.cases.designPattern.factory.general;

public abstract class Creator {
	/**
	 * 创建一个产品对象，传入参数类型可以自行设置 通常为String,Enum,Class等，也可以为空
	 * 
	 * @param c
	 * @return
	 */
	public abstract <T extends Product> T createProduct(Class<T> c);
}
