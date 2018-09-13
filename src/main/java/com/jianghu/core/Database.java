package com.jianghu.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Database类
 * 
 * @author jinlong
 * 
 */
public class Database {
	private static final String DRIVERCLASS;
	private static final String URL;
	// private static final String USERNAME;
	// private static final String PSW;
	// 读取配置文件中的内容
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		DRIVERCLASS = bundle.getString("sqlite.driver");
		URL = bundle.getString("sqlite.url");
		// USERNAME = bundle.getString("mysql.username");
		// PSW = bundle.getString("mysql.password");
	}

	public static void main(String[] args) throws Exception {
	}

	/**
	 * 获得连接connection
	 * 
	 * @return connection对象
	 * @throws Exception
	 */
	public static Connection getconnection() throws Exception {
		LoadDriver();
		try {
			// return DriverManager.getConnection(URL, USERNAME, PSW);
			Properties pro = new Properties();
			// sqlite解决getDate时日期格式不正确的问题
			pro.put("date_string_format", "yyyy-MM-dd HH:mm:ss"); // 默认是yyyy-MM-dd HH:mm:ss.SSS
			return DriverManager.getConnection(URL, pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载数据库驱动
	 * 
	 * @throws ClassNotFoundException
	 */
	private static void LoadDriver() throws ClassNotFoundException {
		Class.forName(DRIVERCLASS);
	}

	public static void closeresouce(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

	public static void closeresouce(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			statement = null;
		}
	}

	public static void closeresouce(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
	}

	public static void closeresouce(ResultSet resultSet, boolean flag) {
		if (flag) {
			closeresouceAll(resultSet);
		} else {
			closeresouce(resultSet);
		}

	}

	public static void closeresouceAll(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				Statement statement = resultSet.getStatement();
				Connection connection = statement.getConnection();
				closeresouce(resultSet, statement, connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接（不带结果集）
	 * 
	 * @param statement
	 * @param connection
	 */
	public static void closeresouce(Statement statement, Connection connection) {
		closeresouce(statement);
		closeresouce(connection);
	}

	/**
	 * 关闭连接（带结果集）
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void closeresouce(ResultSet resultSet, Statement statement, Connection connection) {
		closeresouce(resultSet);
		closeresouce(statement);
		closeresouce(connection);
	}

	/**
	 * 执行查询，将resultSet封装为List（可变参数）
	 * 
	 * @param sql
	 *            查询的SQL语句
	 * @param cls
	 *            实体类
	 * @param params
	 *            查询条件
	 * @return List结果集
	 */
	public static <T> List<T> executeQuery(String sql, Class<T> cls, Object... params) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>(); // 实例化返回的数据集合
		try {
			connection = getconnection();
			prepareStatement = connection.prepareStatement(sql);
			if (params.length > 0) {
				// 循环参数
				for (int i = 0; i < params.length; i++) {
					prepareStatement.setObject(i + 1, params[i]);
				}
			}
			// 获得实体类中所有字段的名称（区分大小写）
			Field[] fields = cls.getDeclaredFields();
			rs = prepareStatement.executeQuery();
			// 把resultSet结果集转换为List<T>
			ResultSetMetaData mataData = rs.getMetaData();
			int closLen = mataData.getColumnCount(); // 拿到总列数
			while (rs.next()) {
				// 通过反射机制创建一个实例
				T resultObject = cls.newInstance();
				for (int n = 0; n < closLen; n++) {
					String cols_Name = mataData.getColumnName(n + 1);
					Object cols_value = rs.getObject(cols_Name);
					if (cols_value == null) {
						cols_value = "";
					}
					for (Field field : fields) {
						if (cols_Name.equalsIgnoreCase(field.getName())) {
							cols_Name = field.getName();
						}
					}
					// 该参数必须为实体类中的属性，oracle数据库中字段都是大写，所以要用字段去匹配实体类中的属性
					Field filed = cls.getDeclaredField(cols_Name);
					filed.setAccessible(true); // 打开javabean=model的访问权限
					filed.set(resultObject, cols_value);
				}
				list.add(resultObject);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeresouce(rs, prepareStatement, connection);
		}
		return null;
	}

	/**
	 * 执行查询，将resultSet封装为List（带参数数组）
	 * 
	 * @param sql
	 *            查询的SQL语句
	 * @param cls
	 *            实体类
	 * @param params
	 *            查询条件
	 * @return List结果集
	 */
	public static <T> List<T> executeQuery(String sql, Class<T> cls, String[] params) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>(); // 实例化返回的数据集合
		try {
			connection = getconnection();
			prepareStatement = connection.prepareStatement(sql);
			if (params.length > 0) {
				// 循环参数
				for (int i = 0; i < params.length; i++) {
					prepareStatement.setObject(i + 1, params[i]);
				}
			}
			// 获得实体类中所有字段的名称（区分大小写）
			Field[] fields = cls.getDeclaredFields();
			rs = prepareStatement.executeQuery();
			// 把resultSet结果集转换为List<T>
			ResultSetMetaData mataData = rs.getMetaData();
			int closLen = mataData.getColumnCount(); // 拿到总列数
			while (rs.next()) {
				// 通过反射机制创建一个实例
				T resultObject = cls.newInstance();
				for (int n = 0; n < closLen; n++) {
					String cols_Name = mataData.getColumnName(n + 1);
					Object cols_value = rs.getObject(cols_Name);
					if (cols_value == null) {
						cols_value = "";
					}
					for (Field field : fields) {
						if (cols_Name.equalsIgnoreCase(field.getName())) {
							cols_Name = field.getName();
						}
					}
					// 该参数必须为实体类中的属性，oracle数据库中字段都是大写，所以要用字段去匹配实体类中的属性
					Field filed = cls.getDeclaredField(cols_Name);
					filed.setAccessible(true); // 打开javabean=model的访问权限
					filed.set(resultObject, cols_value);
				}
				list.add(resultObject);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeresouce(rs, prepareStatement, connection);
		}
		return null;
	}

	/**
	 * 执行SQL语句获取唯一的String的值
	 * 
	 * @param sql
	 *            sql语句
	 * @return 唯一的String值或者""
	 */
	public static String getUniqueStringValue(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getconnection();
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}
			rs = statement.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeresouce(rs, statement, connection);
		}
		return "";
	}

	// TODO 通用保存和修改方法
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void executeSave(String sql, Class<?> cls) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getconnection();
			statement = connection.createStatement();
			Method[] methods = cls.getDeclaredMethods();
			List<?> list = new ArrayList();
			for (Method method : methods) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeresouce(statement, connection);
		}
	}

	/**
	 * 执行SQL语句（List集合）
	 * 
	 * @param sql
	 *            执行的SQL语句
	 * @param params
	 *            参数数组
	 * @throws Exception
	 */
	public static void executeUpdate(String sql, List<?> params) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getconnection();
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					statement.setObject(i + 1, params.get(i));
				}
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeresouce(statement, connection);
		}
	}

	/**
	 * 执行SQL语句（可变参数）
	 * 
	 * @param sql
	 *            执行的SQL语句
	 * @param params
	 *            可变参数
	 * @throws Exception
	 */
	public static void executeUpdate(String sql, Object... params) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			// 连接数据库
			connection = getconnection();
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeresouce(statement, connection);
		}
	}

	/**
	 * 执行SQL(批处理)
	 * 
	 * @creatTime 2016年10月23日 下午8:13:39
	 * @author jinlong
	 * @param sql
	 * @param params
	 * @throws Exception
	 */
	public static void executeUpdateBatch(String sql, List<Object[]> list) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			// 连接数据库
			connection = getconnection();
			statement = connection.prepareStatement(sql);
			for (Object[] objects : list) {
				// 设置参数，暂未处理日期等特殊类型，可能存在问题
				for (int i = 0; i < objects.length; i++) {
					statement.setObject(i + 1, objects[i]);
				}
				// 添加到批处理中
				statement.addBatch();
			}
			// 执行批处理
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeresouce(statement, connection);
		}
	}

	public static void executeUpdateBatch(Connection connection, String sql, List<Object[]> list) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (Object[] objects : list) {
				// 设置参数，暂未处理日期等特殊类型，可能存在问题
				for (int i = 0; i < objects.length; i++) {
					statement.setObject(i + 1, objects[i]);
				}
				// 添加到批处理中
				statement.addBatch();
			}
			// 执行批处理
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeresouce(statement);
		}
	}

	/**
	 * 执行批量sql，不带参数
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月10日 下午7:26:29
	 * @param sqlList
	 * @throws Exception
	 */
	public static void executeUpdateBatch(List<String> sqlList) throws Exception {
		Connection connection = null;
		Statement statement = null;
		try {
			// 连接数据库
			connection = getconnection();
			statement = connection.createStatement();
			for (String sql : sqlList) {
				// 添加到批处理中
				statement.addBatch(sql);
			}
			// 执行批处理
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeresouce(statement, connection);
		}
	}

	/**
	 * 执行SQL获得ResultSet对象（可变参数）
	 * 
	 * @param sql
	 * @param params
	 * @return ResultSet
	 * @throws Exception
	 */
	public static ResultSet executeQuery(String sql, Object... params) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 连接数据库
			connection = getconnection();
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}
			// 该处不能再将sql传入，否则当存在参数时，会报【不支持的网络数据类型或表示法】
			resultSet = statement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet executeQuery(Connection connection, String sql, Object... params) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 连接数据库
			statement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					statement.setObject(i + 1, params[i]);
				}
			}
			// 该处不能再将sql传入，否则当存在参数时，会报【不支持的网络数据类型或表示法】
			resultSet = statement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
