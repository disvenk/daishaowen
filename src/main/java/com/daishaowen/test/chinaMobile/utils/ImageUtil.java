package com.daishaowen.test.chinaMobile.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/**
 * @author jiying
 * @date 2015年9月12日
 * @version 1.0
 */
public class ImageUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ImageUtil.class);

	public static boolean validImage(final InputStream in) {

		boolean result = false;

		try {
			final BufferedImage image = ImageIO.read(in);
			if (image != null) {
				result = true;
			}
			in.close();
		} catch (final IOException e) {
			LOGGER.error("ImageUtil.validImage", e);
		}

		return result;

	}

	public static String getImageFormat(final InputStream in) {

		String type = null;
		try {
			final ImageInputStream imageInput = ImageIO
					.createImageInputStream(in);
			final Iterator<ImageReader> iterator = ImageIO
					.getImageReaders(imageInput);
			if (!iterator.hasNext()) {
				return null;
			}
			final ImageReader reader = iterator.next();
			type = reader.getFormatName().toLowerCase();
			imageInput.close();
			in.close();
		} catch (final IOException e) {
			LOGGER.error("ImageUtil.getImageFormat", e);
		}

		return type;
	}

	public static String generateRelativeDir() {

		final Calendar now = Calendar.getInstance();
		final int[] element = { now.get(Calendar.YEAR),
				now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH),
				now.get(Calendar.HOUR_OF_DAY) };
		final StringBuffer path = new StringBuffer();
		for (int i = 0; i < element.length; i++) {
			path.append("/").append(element[i]);
		}

		return path.toString();
	}

	public static String makeDir(final String dir) {

		final File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;

	}

	public static String saveFile(final InputStream in, final String dir,
			final String fileName) throws IOException {

		ImageUtil.makeDir(dir);
		final String filePath = dir + "/" + fileName;
		FileOutputStream out = null;
		out = new FileOutputStream(filePath);
		FileCopyUtils.copy(in, out);
		out.close();
		in.close();

		return filePath;

	}

	public static String generateFileId() {

		final UUID uuid = UUID.randomUUID(); // uuid
		final String id = uuid.toString().replace("-", "");

		final Date date = new Date();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
		final String time = formatter.format(date); // time

		return time + id;
	}

	public static boolean deleteFile(final String filePath) {

		boolean flag = false;
		final File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}

		return flag;
	}

	/**
	 * get image in bytes form
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] getImage(final String filePath) throws IOException {

		byte[] imageBytes = null;
		final File file = new File(filePath);
		final BufferedImage image = ImageIO.read(file);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", baos);
		imageBytes = baos.toByteArray();
		baos.close();
		return imageBytes;
	}

	/*
	 * public static void main(final String args[]) { final File file = new
	 * File("target/test-classes/images/test.jpg");
	 * System.out.println(file.getAbsolutePath()); }
	 */

}
