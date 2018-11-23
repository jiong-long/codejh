package com.jianghu.core.func.compressOssPic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.OSSObject;

public class OssUtil {
	// OSS连接信息
	private static String endpoint = "endpoint";
	private static String accessKeyId = "accessKeyId";
	private static String accessKeySecret = "accessKeySecret";
	private static String bucketName = "bucketName";

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("87FBB68D3DA54FD8BC19C9E5122D3068", "jpg");
		downloadFile(map, "EOBMBZZMST", "C:/Users/wangjinlong/Desktop/");
	}

	/**
	 * 批量下载文件
	 * 
	 * @param keyList
	 */
	public static void downloadFile(Map<String, String> map, String tableName, String outPutPath) {
		OSSClient ossClient = new OSSClient(endpoint, new DefaultCredentialProvider(accessKeyId, accessKeySecret), null);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			Set<String> keySet = map.keySet();
			int i = 1;
			int size = map.size();
			System.out.println("一共发现【" + size + "】个满足条件的图片");
			for (String fileKey : keySet) {
				System.out.println("下载进度：" + i++ + "/" + size);
				// 判断文件是否存在
				boolean exsit = ossClient.doesObjectExist(bucketName, tableName + "/" + fileKey);
				if (exsit) {
					OSSObject ossObject = ossClient.getObject(bucketName,
							tableName + "/" + fileKey);
					if (ossObject != null) {
						inputStream = ossObject.getObjectContent();
						outputStream = new FileOutputStream(
								outPutPath + tableName + "/" + fileKey + "." + map.get(fileKey));
						IOUtils.copy(inputStream, outputStream);
						outputStream.flush();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ossClient != null) {
					ossClient.shutdown();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
