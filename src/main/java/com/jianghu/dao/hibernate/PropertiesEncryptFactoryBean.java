package com.jianghu.dao.hibernate;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

import com.jianghu.core.tools.EncryptUtil;

/**
 * 对dao.xml加密的信息进行解密
 * 
 * @creatTime 2019年2月27日 下午7:50:42
 * @author jinlong
 */
public class PropertiesEncryptFactoryBean implements FactoryBean<Object> {
	private Properties properties;

	public Object getObject() throws Exception {
		return getProperties();
	}

	public Class<Properties> getObjectType() {
		return java.util.Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties inProperties) {
		this.properties = inProperties;
		String originalUsername = properties.getProperty("user");
		String originalPassword = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		if (originalUsername != null) {
			String newUsername = EncryptUtil.decrypt(originalUsername);
			properties.put("user", newUsername);
		}
		if (originalPassword != null) {
			String newPassword = EncryptUtil.decrypt(originalPassword);
			properties.put("password", newPassword);
		}
		if (jdbcUrl != null) {
			String newJdbcUrl = EncryptUtil.decrypt(jdbcUrl);
			properties.put("jdbcUrl", newJdbcUrl);
		}
	}
}
