package com.daishaowen.test.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * [Ftp工具类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public class FtpUtils {
	private static Logger logger = LoggerFactory.getLogger(FtpUtils.class);

	private volatile static ChannelSftp sftp;

	private static Session sshSession = null;
	private static Channel channel = null;

	/**
	 * [获取SFTP实例,双重锁确保SFTP单例]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	private static ChannelSftp getChannelSftpInstance(String host, int port, String username, String password) throws Exception {
		if (sftp == null) {
			synchronized (FtpUtils.class) {
				if (sftp == null) {
					JSch jsch = new JSch();
					sshSession = jsch.getSession(username, host, port);
					sshSession.setPassword(password);
					sshSession.setConfig("StrictHostKeyChecking", "no");
					sshSession.connect();
					channel = sshSession.openChannel("sftp");
					channel.connect();
					sftp = (ChannelSftp) channel;
				}
			}
		}
		return sftp;
	}

	/**
	 * [向FTP服务器上传文件,多线程低调用此方法,所以用同步]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public static synchronized boolean uploadFile(String host, int port, String username, String password, String ftpFilePath, InputStream input) {
		try {
			getChannelSftpInstance(host, port, username, password).put(input, ftpFilePath, ChannelSftp.OVERWRITE);
			return true;
		} catch (Exception e) {
			logger.error("上传文件到FTP服务器失败!", e);
			return false;
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				logger.error("关闭输入流失败!");
			}
		}
	}

	/**
	 * [关闭SFTP连接]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public static void disconnect() {
		if (sftp != null && sftp.isConnected()) {
			sftp.quit();
			sftp = null;
		}
		if (channel != null && channel.isConnected()) {
			channel.disconnect();
			channel = null;
		}
		if (sshSession != null && sshSession.isConnected()) {
			sshSession.disconnect();
			sshSession = null;
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File("D:/ftp.txt");
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			InputStream input = getChannelSftpInstance("42.99.0.141", 22, "LSHNSGS", "LSHNSGS2017").get(
					"/BUS10157/upload/mqsiarchive/20000100672000000001BUS10157201712034001.txt");
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = input.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			input.close();
			System.out.println("文件下载完成!");

			file = new File("D:/20000100672000000001BUS10159201712043003.txt");
			input = new FileInputStream(file);
			getChannelSftpInstance("42.99.0.141", 22, "LSHNSGS", "LSHNSGS2017").put(input, "/BUS10159/upload/" + file.getName(),
					ChannelSftp.OVERWRITE);
			System.out.println("文件上传完成!");
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}