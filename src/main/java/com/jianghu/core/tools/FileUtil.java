package com.jianghu.core.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

/**
 * 文件相关工具类
 * 
 * @creatTime 2017年5月11日 下午8:18:52
 * @author jinlong
 * 
 */
public class FileUtil {
	// 工作空间名称
	private static final String WORKSPACE = "E:\\Workspace3,E:\\Workspaces2,E:\\Workspaces";
	// 读取配置文件中的内容
	// static {
	// ResourceBundle bundle = ResourceBundle.getBundle("file");
	// WORKSPACE = bundle.getString("workSpace");
	// }

	public static void main(String[] args) {
		try {
			String deskPath = getDeskPath();// 桌面的路径
			// String proName = getCurrProName();// 项目名称
			String proName = getDeskProNam();
			if ("".equals(proName)) {
				System.out.println("桌面上没有找到项目或者存在多个，请确认！！！");
				System.out.println("失败！！！");
				return;
			}
			// String getClassPath = getClassesPath();// classes路径
			String getClassPath = getClassesPath(proName);
			// 获取导出到桌面上的所有java文件
			File file = new File(deskPath + "\\" + proName + "\\src");
			List<String> list = new ArrayList<String>();
			getAllFile(file, list);
			for (String string : list) {
				String[] str = string.split("src");
				// 获取java文件对应的class文件位置
				String fromPath = getClassPath + str[1].substring(1).replaceAll(".java", ".class");
				String toPath = deskPath + "\\" + proName + fromPath.split(proName)[1] + proName
						+ fromPath.split(proName)[2];
				// 判断该java有没有内部类
				// No.1 去掉结尾的class
				int lastSplit = fromPath.lastIndexOf(".");
				String newPath = fromPath.substring(0, lastSplit);
				// No.2然后以最后一个\区分出class名称与文件夹名称
				lastSplit = newPath.lastIndexOf("\\");
				String classPath = newPath.substring(0, lastSplit);
				String fileName = newPath.substring(lastSplit + 1);
				// No.3获取该文件路径下（不包括文件夹）中该名称开头的文件
				List<String> innerClassList = findAllFileByName(classPath, fileName);
				for (String innerClass : innerClassList) {
					// No.4 将内部类转出
					String innerFromPath = fromPath.replace(fileName + ".class", innerClass);
					String innerToPath = toPath.replace(fileName + ".class", innerClass);
					copyFile(innerFromPath, innerToPath);
				}
				copyFile(fromPath, toPath);
			}
			// 复制完成之后，删除src目录
			File deleteFile = new File(deskPath + "\\" + proName + "\\src");
			String returnFlag = "";
			deleteFileBatch(deleteFile, returnFlag);
			if ("fail".equals(returnFlag)) {
				System.out.println("src文件夹删除失败，请手动删除！！！");
			}
			System.out.println("成功");
		} catch (Exception e) {
			System.out.println("失败：" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件
	 * 
	 * @creatTime 2017年5月11日 下午11:21:06
	 * @author jinlong
	 * @param fromPath
	 *            全路径，包括文件名
	 * @param toPath
	 *            全路径，包括文件名
	 * @throws IOException
	 */
	public static void copyFile(String fromPath, String toPath) throws IOException {
		File file = new File(fromPath);
		copyFile(file, toPath);
	}

	/**
	 * 复制文件到指定文件目录
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年5月22日 下午10:47:18
	 * @param file
	 *            需要复制的文件
	 * @param toPath
	 *            需要复制到的文件夹（需要文件名）
	 * @throws IOException
	 */
	public static void copyFile(File file, String toPath) throws IOException {
		// 最后一个为文件，不去掉会创建为文件夹
		String createToPath = toPath.substring(0, toPath.lastIndexOf("\\"));
		File toPathFile = new File(createToPath);
		// 如果复制到的目录不存在，就创建目录
		if (!toPathFile.exists()) {
			toPathFile.mkdirs();
		}
		File toFile = new File(toPath);
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(toFile);
		BufferedInputStream bfis = new BufferedInputStream(fis);
		BufferedOutputStream bfos = new BufferedOutputStream(fos);
		int ch = 0;
		while ((ch = bfis.read()) != -1) {
			bfos.write(ch);
		}
		bfos.close();
		bfis.close();
	}

	/**
	 * 获取当前项目名称
	 * 
	 * @creatTime 2017年5月11日 下午10:18:21
	 * @author jinlong
	 * @return
	 */
	public static String getCurrProName() {
		// 该路径为项目的全路径，需要截取(限制条件：该Java文件必须在项目中)
		String proPath = System.getProperty("user.dir");
		int beginIndex = proPath.lastIndexOf("\\") + 1;
		return proPath.substring(beginIndex);
	}

	/**
	 * 获取桌面上项目的名称
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月6日 下午9:20:59
	 * @return
	 */
	public static String getDeskProNam() {
		String returnFileName = "";
		// 桌面的路径
		String deskPath = getDeskPath();
		// 获取桌面上所有的文件
		File allFile = new File(deskPath);
		// 遍历桌面上所有的文件
		File[] fileStr = allFile.listFiles();
		int i = 0;// 用于判断桌面上是否存在多个项目文件夹
		if (fileStr != null) {
			for (File file : fileStr) {
				if (file.isDirectory()) {// 是文件夹
					// 获取文件夹的名字
					String fileName = file.getName();
					if (fileName.startsWith("jianghu")) {// LiEMS
						// 为了防止桌面上出现多个满足条件的的文件夹，取最近半个小时修改的
						long modifiTime = file.lastModified();
						// 使用System.currentTimeMillis()替代new Date().getTime()
						long time = System.currentTimeMillis() - modifiTime;
						if (time < 30 * 60 * 1000) {
							// 满足条件的项目名称
							returnFileName = fileName;
							i++;
						}
					}
				}
			}
		}
		if (i != 1) {// 没有找到,或者多个
			returnFileName = "";
		}
		return returnFileName;
	}

	/**
	 * 获得该工程classes路径（该文件需要在项目中）
	 * 
	 * @creatTime 2017年5月11日 下午10:01:00
	 * @author jinlong
	 * @return
	 * @throws IOException
	 */
	public static String getClassesPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		return path.substring(1).replaceAll("/", "\\\\");
	}

	/**
	 * 获得该工程classes路径（通过项目名称）
	 * 
	 * @creatTime 2017年5月11日 下午10:01:00
	 * @author jinlong
	 * @return
	 */
	public static String getClassesPath(String proNam) throws IOException {
		String trueWorkSpace = WORKSPACE;
		if (trueWorkSpace.contains(",")) {
			trueWorkSpace = getWorkSpaceFromPro(trueWorkSpace, proNam);
		}
		return trueWorkSpace + "\\" + proNam + "\\jiong\\WEB-INF\\classes\\";
	}

	/**
	 * 取项目存在的WorkSpace
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年9月6日 下午11:55:05
	 * @param workSpace
	 * @return
	 */
	public static String getWorkSpaceFromPro(String workSpaces, String proNam) {
		// 遍历每个workSpaces，找到存在项目的workSpaces
		String[] workSpace = workSpaces.split(",");
		for (String string : workSpace) {
			File file = new File(string);
			File[] listFile = file.listFiles();
			if (listFile != null) {
				for (File file2 : listFile) {
					System.out.println(file2.getName());
					if (proNam.equals(file2.getName())) {
						return string;
					}
				}
			}
		}
		return "";
	}

	/**
	 * 获得桌面的路径绝对路径
	 * 
	 * @creatTime 2017年5月11日 下午9:56:22
	 * @author jinlong
	 * @return
	 */
	public static String getDeskPath() {
		return FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
	}

	/**
	 * 批量删除指定文件或者文件夹（包括里面内容）
	 * 
	 * @creatTime 2017年5月11日 下午10:35:49
	 * @author jinlong
	 * @param f
	 * @param returnFlag
	 *            由于递归，使用返回值的方法较为麻烦，所以使用传入参数的方式，返回fail为失败，其他均成功
	 */
	public static void deleteFileBatch(File f, String returnFlag) {
		File[] files = f.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					deleteFileBatch(file, returnFlag);
				} else {
					boolean flag = file.delete();
					if (flag == false) {
						returnFlag = "fail";
					}
				}
			}
		}
		boolean flag = f.delete();
		if (flag == false) {
			returnFlag = "fail";
		}
	}

	/**
	 * 批量删除List中的文件(包括文件夹)
	 * 
	 * @creatTime 2017年5月11日 下午9:29:25
	 * @author jinlong
	 * @param fileList
	 * @return fail：失败 success：成功
	 */
	public static String deleteFileBatch(List<String> fileList) {
		// 删除文件夹的时候，删除的文件夹必须为空
		for (int i = fileList.size() - 1; i >= 0; i--) {
			File file = new File(fileList.get(i));
			boolean flag = file.delete();
			if (flag == false) {// 删除失败
				return "fail";
			}
		}
		return "success";
	}

	/**
	 * 获取指定扩展名的文件
	 * 
	 * @creatTime 2017年5月11日 下午9:20:57
	 * @author jinlong
	 * @param file
	 * @param ext
	 *            扩展名
	 * @return List<String>
	 */
	public static List<String> findAllFileByExt(File file, String ext) {
		List<String> fileList = new ArrayList<String>();
		List<String> newFileList = new ArrayList<String>();
		getAllFile(file, fileList);
		for (String fileStr : fileList) {
			if (fileStr.endsWith(ext)) {
				newFileList.add(fileStr);
			}
		}
		return newFileList;
	}

	/**
	 * 获取当前目录下所有的复合要求的内部类
	 * 
	 * @creatTime 2017年5月11日 下午9:20:57
	 * @author jinlong
	 * @param file
	 * @param ext
	 *            扩展名
	 * @return List<String>
	 */
	public static List<String> findAllFileByName(String filePath, String startName) {
		File file = new File(filePath);
		List<String> fileList = new ArrayList<String>();
		File[] files = file.listFiles();// 获得该目录下所有的文件和文件夹
		if (files != null) {
			for (File f : files) {// 遍历
				if (f.isFile()) {// 是文件
					String fileName = f.getName();
					if (fileName.startsWith(startName + "$")) {
						fileList.add(f.getName());
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 获得所有的file
	 * 
	 * @creatTime 2017年5月11日 下午9:07:31
	 * @author jinlong
	 * @param dir
	 * @param fileList
	 */
	public static void getAllFile(File dir, List<String> fileList) {
		File[] files = dir.listFiles();// 获得该目录下所有的文件和文件夹
		if (files != null) {// 没有Java文件
			for (File f : files) {// 遍历
				if (f.isDirectory()) {// 如果是文件夹，递归
					getAllFile(f, fileList);
				} else {
					fileList.add(f.toString());
				}
			}
		}
	}

	/**
	 * 获得所有的文件以及文件夹
	 * 
	 * @creatTime 2017年5月11日 下午8:57:16
	 * @author jinlong
	 * @param dir
	 *            文件或者文件夹
	 * @param fileList
	 *            由于递归调用该方法，不能在方法中new List
	 */
	public static void getAllFileAndDir(File dir, List<String> fileList) {
		fileList.add(dir.toString());
		File[] files = dir.listFiles();// 获得该目录下所有的文件和文件夹
		if (files != null) {
			for (File f : files) {// 遍历
				if (f.isDirectory()) {// 如果是文件夹，递归
					getAllFileAndDir(f, fileList);
				} else {
					fileList.add(f.toString());
				}
			}
		}
	}
}
