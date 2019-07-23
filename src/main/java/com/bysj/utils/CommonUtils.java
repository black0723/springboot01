package com.bysj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public class CommonUtils {

	/**
	 * 获取系统当前日期
	 * 
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 获取系统当前日期和时间
	 * 
	 * @return
	 */
	public static String getNowDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 格式化日期，返回字符串类型
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(date);
	}

	public static String getDateTime(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 设置日期格式
		return df.format(date);
	}

	public static String getDate(Date date, String strformat) {
		if (date == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(strformat);// 设置日期格式
		return df.format(date);
	}

	/**
	 * 格式化日期，返回日期类型
	 * 
	 * @param fromDate
	 * @return
	 */
	public static Date toDateFormat(Date fromDate) {
		String str = getDate(fromDate);
		if (str == null)
			return null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2012-02-24
		date = java.sql.Date.valueOf(str);
		return date;
	}

	public static Date toDateFormat(Date fromDate, String strformat) {
		String str = getDate(fromDate);
		if (str == null)
			return null;
		DateFormat format = new SimpleDateFormat(strformat);
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2012-02-24
		date = java.sql.Date.valueOf(str);
		return date;
	}

	public static Date toDate(String date) {
		return toDate(date, "yyyy-MM-dd");
	}

	public static Date toDate(Object date) {
		if (date == null)
			return null;
		return toDate(date.toString(), "yyyy-MM-dd");
	}

	public static Date toDateTime(String date) {
		return toDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date toDate(String date, String strformat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strformat);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String str = UUID.randomUUID().toString();
		String uuid = str.replace("-", "");
		return uuid;
	}

	/**
	 * (最小值+Math.random()*(最大值-最小值+1))
	 * 
	 * @param max
	 * @param min
	 * @return
	 */
	public static int getRandom(int max, int min) {
		// (int)(1+Math.random()*(10-1+1));//从1到10的int型随数
		return (int) (min + Math.random() * max - min + 1);
	}

	/**
	 * Java文件操作 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot);
			}
		}
		return filename;
	}

	/**
	 * Java文件操作 获取不带扩展名的文件名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileNameWithoutExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	/**
	 * 当前日期的年
	 * 
	 * @return
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 当年日期的月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		if (month.length() == 1) {
			month = "0" + month;
		}
		return month;
	}

	/**
	 * 当前日期的天
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		return day;
	}

	/**
	 * 获取时间间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long[] getDiff(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;

		try {
			one = sdf.parse(date1);
			two = sdf.parse(date2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff = Math.abs(time1 - time2);
			day = diff / (24 * 60 * 60 * 1000);
			hour = diff / (60 * 60 * 1000) - day * 24;
			min = diff / (60 * 1000) - day * 24 * 60 - hour * 60;
			sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
		} catch (Exception e) {
		}
		long[] times = { day, hour, min, sec };
		return times;
	}
	
	/**
	 * 读文件
	 * @param filename
	 * @return
	 */
	public static byte[] toByteArray2(File file) {

		if (!file.exists()) {
			return null;
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
