package com.jianghu.core.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 图片相关工具类
 * 
 * @author seawind
 * 
 */
public class PicUtil {
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// changeSize("F:/123.jpg", "F:/111.jpg", 300, 300, true);
		changeSize("F:/A77660F71FD746AD8D3A2DE51257E92A.png",
				"F:/A77660F71FD746AD8D3A2DE51257E92A.png", 0.5f);
		// rotateImg("F:/123.jpg", "F:/111.jpg", 190);
		// watermark("F:/123.jpg", "F:/111.jpg", "F:/222.jpg", Positions.BOTTOM_RIGHT, 0.5f);
		// sourceRegion("F:/123.jpg", "F:/111.jpg", Positions.BOTTOM_RIGHT, 1000, 1000);
		// sourceRegion("F:/123.jpg", "F:/111.jpg", 1000, 1000, 1000, 1000);
		// outputFormat("F:/123.jpg", "F:/111.png", "png");
		// BufferedImage image = asBufferedImage("F:/123.jpg");
		// System.out.println(image.getWidth());
		// System.out.println(image.getHeight());
	}

	/**
	 * 
	 * 指定大小进行压缩
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param width
	 *            改变后的宽度（keepAspect为true时类似于css的max-width）
	 * @param height
	 *            改变后的高度（keepAspect为true时类似于css的max-height）
	 * @param keepAspect
	 *            是否保持原有图片比例
	 * @throws IOException
	 */
	public static void changeSize(String oldFilePath, String newFilePath, int width, int height, boolean keepAspect) throws IOException {
		Thumbnails.of(oldFilePath).size(width, height).keepAspectRatio(keepAspect).toFile(newFilePath);
	}

	/**
	 * 按照比例进行压缩
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param size
	 *            0-1之间，按照比例缩小
	 * @throws IOException
	 */
	public static void changeSize(String oldFilePath, String newFilePath, float size) throws IOException {
		Thumbnails.of(oldFilePath).scale(size).toFile(newFilePath);
	}

	/**
	 * 旋转
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param angle
	 *            旋转角度,正数：顺时针; 负数：逆时针
	 * @throws IOException
	 */
	public static void rotateImg(String oldFilePath, String newFilePath, double angle) throws IOException {
		Thumbnails.of(oldFilePath).scale(1).rotate(angle).toFile(newFilePath);
	}

	/**
	 * 水印
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param markFilePath
	 *            水印图片地址
	 * @param positions
	 *            水印位置
	 * @param opacity
	 *            透明度
	 * @throws IOException
	 */
	public static void watermark(String oldFilePath, String newFilePath, String markFilePath, Positions positions, float opacity) throws IOException {
		Thumbnails.of(oldFilePath).scale(1).watermark(positions, ImageIO.read(new File(markFilePath)), opacity).outputQuality(0.8f).toFile(newFilePath);
	}

	/**
	 * 裁剪
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param position
	 *            裁剪位置
	 * @param width
	 *            裁剪宽度
	 * @param height
	 *            裁剪高度
	 * @throws IOException
	 */
	public static void sourceRegion(String oldFilePath, String newFilePath, Position position, int width, int height) throws IOException {
		Thumbnails.of(oldFilePath).sourceRegion(position, width, height).scale(1).toFile(newFilePath);
	}

	/**
	 * 裁剪（自定义裁剪坐标）
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param localWidth
	 *            裁剪开始宽度
	 * @param localHeight
	 *            裁剪开始高度
	 * @param width
	 *            裁剪宽度
	 * @param height
	 *            裁剪高度
	 * @throws IOException
	 */
	public static void sourceRegion(String oldFilePath, String newFilePath, int localWidth, int localHeight, int width, int height) throws IOException {
		Thumbnails.of(oldFilePath).sourceRegion(localWidth, localHeight, width, height).scale(1).toFile(newFilePath);
	}

	/**
	 * 转化图像格式
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @param newFilePath
	 *            新图片地址
	 * @param format
	 *            新图片格式
	 * @throws IOException
	 */
	public static void outputFormat(String oldFilePath, String newFilePath, String format) throws IOException {
		Thumbnails.of(oldFilePath).scale(1).outputFormat(format).toFile(newFilePath);
	}

	/**
	 * 返回BufferedImage对象，可以获取图片相关信息
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage asBufferedImage(String oldFilePath) throws IOException {
		BufferedImage thumbnail = Thumbnails.of(oldFilePath).scale(1).asBufferedImage();
		return thumbnail;
	}

	/**
	 * 获取图片的OutputStream对象
	 * 
	 * @param oldFilePath
	 *            原图片地址（项目中的图片可以用相对路径 images/test.jpg）
	 * @return
	 * @throws IOException
	 */
	public static OutputStream toOutputStream(String oldFilePath) throws IOException {
		OutputStream os = new FileOutputStream(oldFilePath);
		return os;
	}
}
