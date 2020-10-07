package com.cases.compressOssPic;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.jianghu.dao.Database;
import com.common.PicUtil;

/**
 * 下载OSS图片到本地
 * 
 * @author wangjinlong
 * @createTime 2018年11月20日 上午8:57:11
 */
public class DownloadOssPic {
	public static void main(String[] args) throws IOException {
		long maxSize = 512 * 1024;// 512K
		// 设计构件
		String tableName = "EOBMPROMST";
		// 下载路径
		String outPutPath = "C:/Users/wangjinlong/Desktop/";
		// Setp1:下载文件
		downLoadMaxFile(tableName, outPutPath);
		// Step2:删除小文件
		deleteSmallFile(tableName, outPutPath, maxSize);
		// Step3:压缩文件
		comPressPic(tableName, outPutPath, maxSize);
		// Step4:删除原始文件，去掉压缩后文件后缀名
		deleteAndUpdate(tableName, outPutPath);
	}

	/**
	 * 删除更新文件
	 * 
	 * @param tableName
	 * @param outPutPath
	 * @throws IOException
	 */
	private static void deleteAndUpdate(String tableName, String outPutPath) throws IOException {
		File folder = new File(outPutPath + tableName);
		File[] fileArr = folder.listFiles();
		int count = fileArr.length;
		int i = 1;
		for (File file : fileArr) {
			System.out.println("进度:" + i++ + "/" + count);
			String fileName = file.getName();
			if (fileName.contains("_7.")) {// 压缩后的文件
				int index = fileName.indexOf("_");
				String headName = fileName.substring(0, index);
				file.renameTo(new File(outPutPath + tableName + "/" + headName));
			} else {// 源文件
				file.delete();
			}
		}
	}

	/**
	 * 删除小文件
	 * 
	 * @param tableName
	 * @param outPutPath
	 * @throws IOException
	 */
	private static void deleteSmallFile(String tableName, String outPutPath, long maxSize)
			throws IOException {
		File folder = new File(outPutPath + tableName);
		File[] fileArr = folder.listFiles();
		int count = fileArr.length;
		int i = 1;
		for (File file : fileArr) {
			System.out.println("进度:" + i++ + "/" + count);
			if (file.length() < maxSize) {
				file.delete();
			}
		}
	}

	/**
	 * 压缩文件
	 * 
	 * @param tableName
	 * @param outPutPath
	 * @throws IOException
	 */
	private static void comPressPic(String tableName, String outPutPath, long maxSize) throws IOException {
		File folder = new File(outPutPath + tableName);
		File[] fileArr = folder.listFiles();
		int count = fileArr.length;
		int i = 1;
		for (File file : fileArr) {
			System.out.println("压缩进度:" + i++ + "/" + count);
			String fileName = file.getName();
			int index = fileName.indexOf(".");
			String headName = fileName.substring(0, index);
			String endName = fileName.substring(index + 1);
			String newFilePath = outPutPath + tableName + "/" + headName + "_7." + endName;
			comPressOn(file, newFilePath, maxSize);
		}
	}

	private static void comPressOn(File file, String newFilePath, long maxSize) throws IOException {
		if (file.length() > maxSize) {
			PicUtil.changeSize(file, newFilePath, 0.7F);
			File file2 = new File(newFilePath);
			comPressOn(file2, newFilePath, maxSize);
		}
	}

	/**
	 * 下载文件
	 */
	private static void downLoadMaxFile(String tableName, String outPutPath) {
		Connection connection = null;
		try {
			connection = Database.getconnection();
			// 获取appId
			String appId = getAppId(tableName);
			// 获取所有满足条件的OSS对象的Uuid
			Map<String, String> map = getOssUuid(connection, appId, tableName);
			if (!map.isEmpty()) {
				OssUtil.downloadFile(map, tableName, outPutPath);
			}
			System.out.println(appId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Database.closeresouce(connection);
		}
	}

	/**
	 * 获取所有图片的OSS主键
	 * 
	 * @param connection
	 * @param appId
	 * @param maxSize
	 * @param tableName
	 * @throws Exception
	 */
	private static Map<String, String> getOssUuid(Connection connection, String appId,
			String tableName)
			throws Exception {
		String getPicPathSql = "select oss.FILEUUID,split_part(doc.doc_nam,'.', length(replace(doc.doc_nam,'.','--'))-" +
				"length(doc.doc_nam)+1) EXTEND_NAM from dkosspartmst oss,dkdocmst doc," + tableName
				+ " bm where doc.doc_id = oss.doc_id and "
				+ "doc.app_id = ? and doc.business_type = '01' and oss.partnumber = '-1' and bm.bzz_no = doc.pkval_id";
		ResultSet rs = Database.executeQuery(connection, getPicPathSql, appId);
		// key 是Uuid, value是文件的后缀名
		Map<String, String> map = new HashMap<String, String>();
		while (rs.next()) {
			map.put(rs.getString("FILEUUID"), rs.getString("EXTEND_NAM"));
		}
		return map;
	}

	/**
	 * 获取附件对应的appId
	 * 
	 * @param connection
	 * @return
	 */
	private static String getAppId(String tableName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("EOBMBZZMST", "B1EOG05000");// 设计构件
		map.put("EOBMPROMST", "B1EOG04999");// 产品构件
		String getAppIdSql = "select app_id from dkapptblmst where table_id = ? and pgm_id = ?";
		String appId = Database.getUniqueStringValue(getAppIdSql, tableName, map.get(tableName));
		return appId;
	}
}
