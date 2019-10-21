package com.zx.soft.util;

import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.daishaowen.test.duanyan.Assert;
import org.apache.commons.collections.CollectionUtils;


public class TxtFileUtil {

	/**
	 * [导出到txt]
	 * 
	 * @author Administrator
	 * @version [版本,2019-1-2]
	 * @throws UnsupportedEncodingException
	 */
	public static void exportTxt(HttpServletResponse response, String text, String fileName) {
		BufferedOutputStream buff = null;
		ServletOutputStream outStr = null;
		try {
			response.setCharacterEncoding("utf-8");
			// 设置响应的内容类型
			response.setContentType("text/plain");
			// 设置文件的名称和格式
			response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			outStr = response.getOutputStream();
			buff = new BufferedOutputStream(outStr);
			buff.write(text.getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			// LOGGER.error("导出文件文件出错:{}",e);
		} finally {
			try {
				buff.close();
				outStr.close();
			} catch (Exception e) {
				// LOGGER.error("关闭流对象出错 e:{}",e);
			}
		}
	}

	/**
	 * [将text长度不足maxWidth的补空格]
	 * 
	 * @author Administrator
	 * @version [版本,2019-1-2]
	 */
	public static String getPlace(String text, Integer maxWidth) {
		int textSize = text.length();
		for (int i = 0; i < maxWidth - textSize; i++) {
			text = text + " ";
		}
		return text;
	}

	/**
	 * [计算dataList某列的最大长度]
	 * 
	 * @author Administrator
	 * @version [版本,2019-1-2]
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static <T> Integer getMaxWidth(List<T> dataList, String columnName) throws IllegalArgumentException, IllegalAccessException {
		Assert.isTrue(CollectionUtils.isNotEmpty(dataList), "没有数据可导出");
		Integer dataSize = dataList.size();
		int length = 0;
		if (Map.class.isAssignableFrom(dataList.get(0).getClass())) {
			for (int h = 0; h < dataSize; h++) {
				String data = ((Map) dataList.get(h)).get(columnName).toString();
				if (data.length() > length) {
					length = data.length();
				}
			}
		} else {
			Field[] fields = dataList.get(0).getClass().getDeclaredFields();
			for (int h = 0; h < dataSize; h++) {
				for (int j = 0; j < fields.length; j++) {
					if (fields[j].getName().equalsIgnoreCase(columnName)) {
						fields[j].setAccessible(true);
						String data = (String) fields[j].get(dataList.get(h));
						if (data.length() > length) {
							length = data.length();
						}
					}
				}
			}
		}
		return length;

	}
}
