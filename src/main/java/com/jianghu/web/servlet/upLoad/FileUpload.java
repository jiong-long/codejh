package com.jianghu.web.servlet.upLoad;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.common.PicUtil;
import com.common.Tools;
import com.jianghu.web.servlet.BaseServlet;

/**
 * 文件上传servlet,继承BaseServlet
 * 
 * @creatTime 2017年6月3日 下午5:17:56
 * @author jinlong
 * 
 */
public class FileUpload extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件上传
	 * 
	 * @creatTime 2017年6月3日 下午5:19:32
	 * @author jinlong
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tools.uploadFile(request);
		// 使用fileinput上传时，必须返回json格式的任意值，否则后台上传成功，控件显示失败
		response.setContentType("text/json;charset=utf-8");
		JSONObject object = new JSONObject();
		response.getWriter().print(object.toString());
		// 既不转发也不重定向
		return "";
	}

	public String getExsitFile(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("text/json;charset=utf-8");

		JSONObject object = new JSONObject();
		String filePath = "/jiong/page/3D/images/a2.png";
		object.put("path", filePath);
		object.put("fileName", "a2.png");
		object.put("fileId", "110");
		HttpSession session = request.getSession();
		//获取工程中文件的绝对路径
		String realPath = session.getServletContext().getRealPath("/page/3D/images/a2.png");
		BufferedImage image = PicUtil.asBufferedImage(realPath);
		object.put("width", image.getWidth());
		object.put("height", image.getHeight());

		JSONArray array = new JSONArray();
		array.put(object);
		response.getWriter().print(array.toString());
		// 转发到/upLoad.jsp页面
		return "";
	}

	/**
	 * 根据图片路径显示图片
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年6月10日 下午11:38:14
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String showPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getParameter("path");
		// 本地文件路径
		File file = new File(path);
		// 获取输出流
		OutputStream outputStream = response.getOutputStream();
		FileInputStream fileInputStream = new FileInputStream(file);
		// 读数据
		byte[] data = new byte[fileInputStream.available()];
		fileInputStream.read(data);
		fileInputStream.close();
		// 回写
		response.setContentType("JPG");
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
		return "";
	}

	/**
	 * 删除图片
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年6月10日 下午11:39:05
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String deletePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileId = request.getParameter("fileId");
		System.out.println("删除图片：fileId=" + fileId);
		JSONArray array = new JSONArray();
		array.put("");
		response.getWriter().print(array.toString());
		return "";
	}

	// TODO 多文件上传与显示与下载，包括数据库

	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		//处理请求  
		//读取要下载的文件  
		File f = new File("E:/好久不见.mp3");
		if (f.exists()) {
			FileInputStream fis = null;
			ServletOutputStream out = null;
			try {
				fis = new FileInputStream(f);
				String filename = URLEncoder.encode(f.getName(), "utf-8"); //解决中文文件名下载后乱码的问题  
				byte[] b = new byte[fis.available()];
				fis.read(b);
				response.setCharacterEncoding("utf-8");
				response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
				//获取响应报文输出流对象  
				out = response.getOutputStream();
				out.write(b);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
