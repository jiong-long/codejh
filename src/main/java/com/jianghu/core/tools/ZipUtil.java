package com.jianghu.core.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipUtil {
	// 流操作缓冲区大小
	private static final int BUFFER = 1024;
	private static long length = 0;

	public static void main(String argv[]) throws IOException {
		// 解压
		// unZip("F:/ckeditor.zip", "F:/");

		// 压缩
		zip("F:/ckeditor", "F:/11.zip");
	}

	/**
	 * 解压文件
	 * 
	 * @creatTime 2017年10月22日 下午11:49:51
	 * @author jinlong
	 * @param zipFilePath
	 *            压缩包路径
	 * @param outputDirectory
	 *            文件输出路径
	 * @return true 成功 false 失败
	 */
	public static Boolean unZip(String zipFilePath, String outputDirectory) {
		try {
			// 判断文件输出路径是否存在，不存在就创建
			createDirectory(outputDirectory, "");
			ZipFile zipFile = new ZipFile(zipFilePath);
			// 获取压缩包中所有的实体
			Enumeration<?> entries = zipFile.getEntries();
			// 遍历每一个实体
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) entries.nextElement();
				System.out.println("unziping " + zipEntry.getName());
				if (zipEntry.isDirectory()) {// 是文件夹，创建该文件夹
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					f.mkdir();
				} else {// 是文件
					String fileName = zipEntry.getName();
					fileName = fileName.replace('\\', '/');
					if (fileName.indexOf("/") != -1) {
						createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));
					}
					File file = new File(outputDirectory + zipEntry.getName());
					file.createNewFile();
					// 流操作，将实体写到输出路径上
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(file);

					byte[] by = new byte[BUFFER];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("解压失败：" + e.getMessage());
			return false;
		}
		System.out.println("解压成功");
		return true;
	}

	public static Boolean zip(String fileName, String zipName) {
		File sourceFile = new File(fileName);
		List<File> listFile = getAllFiles(sourceFile);
		try {
			// 获取压缩包的名称
			if (zipName == null || "".equals(zipName)) {
				if (sourceFile.isDirectory()) {
					zipName = fileName + ".zip";
				} else {
					char ch = '.';
					zipName = fileName.substring(0, fileName.lastIndexOf((int) ch)) + ".zip";
				}
			}
			// 流相关操作
			BufferedInputStream bis = null;
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(zipName));
			ZipOutputStream zipos = new ZipOutputStream(bos);
			byte data[] = new byte[BUFFER];
			for (int i = 0; i < listFile.size(); i++) {
				File file = listFile.get(i);
				// 获取从压缩文件名开始的每个文件的名称
				// ckeditor\adapters\jquery.js =
				// ("F:/ckeditor",new File("F:\ckeditor\adapters\jquery.js"))
				zipos.putNextEntry(new ZipEntry(getEntryName(fileName, file)));
				bis = new BufferedInputStream(new FileInputStream(file));

				int count;
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					zipos.write(data, 0, count);
				}
				bis.close();
				zipos.closeEntry();
			}
			zipos.close();
			bos.close();
			showDetail(sourceFile, new File(zipName));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("压缩失败");
			return false;
		}
		System.out.println("压缩成功");
		return true;
	}

	private static String getEntryName(String base, File file) {
		File baseFile = new File(base);
		String filename = file.getPath();
		if (baseFile.getParentFile().getParentFile() == null) {
			return filename.substring(baseFile.getParent().length());
		}
		return filename.substring(baseFile.getParent().length() + 1);
	}

	/**
	 * 显示压缩相关信息
	 * 
	 * @creatTime 2017年10月23日 上午12:07:50
	 * @author jinlong
	 * @param sourceFile
	 * @param targetFile
	 */
	private static void showDetail(File sourceFile, File targetFile) {
		long sourceFileLength = getDirectoryLength(sourceFile);
		long targetFileLength = targetFile.length();
		System.out.println("源文件大小为：" + sourceFileLength + " bytes");
		System.out.println("压缩后为：" + targetFileLength + " bytes");
		System.out.println("压缩率：" + ((double) targetFileLength / (double) sourceFileLength) * 100 + "%");
	}

	/**
	 * 获取文件的大小
	 * 
	 * @creatTime 2017年10月23日 上午12:02:14
	 * @author jinlong
	 * @param path
	 * @return
	 */
	private static long getDirectoryLength(File path) {
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				getDirectoryLength(files[i]);
			}
		} else {
			// path.length() 获取文件的大小
			length += path.length();
		}
		return length;
	}

	/**
	 * 获取路径下所有的文件
	 * 
	 * @creatTime 2017年10月23日 上午12:00:43
	 * @author jinlong
	 * @param sourceFile
	 * @return
	 */
	private static List<File> getAllFiles(File sourceFile) {
		List<File> fileList = new ArrayList<File>();
		if (sourceFile.isDirectory()) {
			File[] files = sourceFile.listFiles();
			for (int i = 0; i < files.length; i++) {
				fileList.addAll(getAllFiles(files[i]));
			}
		} else {
			fileList.add(sourceFile);
		}
		return fileList;
	}

	/**
	 * 当解压路径不存在时，创建该路径
	 * 
	 * @creatTime 2017年10月22日 下午11:37:25
	 * @author jinlong
	 * @param directory
	 * @param subDirectory
	 */
	private static void createDirectory(String directory, String subDirectory) {
		File file = new File(directory);
		if (subDirectory == "" && !file.exists()) {
			file.mkdir();
		} else if (subDirectory != "") {
			String[] dir = subDirectory.replace('\\', '/').split("/");
			for (int i = 0; i < dir.length; i++) {
				File subFile = new File(directory + File.separator + dir[i]);
				if (!subFile.exists()) {
					subFile.mkdir();
				}
				directory += File.separator + dir[i];
			}
		}
	}
}