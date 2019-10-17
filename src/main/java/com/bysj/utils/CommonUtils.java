package com.bysj.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类
 *
 * @author Administrator
 */
public class CommonUtils {
	
		
	/**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

	/**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取系统当前日期时间
     *
     * @return
     */
    public static String getNow() {
        return getNow("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取系统当前日期和时间
     *
     * @return
     */
    public static String getNow(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 格式化日期，返回字符串类型
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        return getDate(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期，返回字符串类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDate(Date date, String format) {
        if (date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
        return df.format(date);
    }

    /**
     * 格式化日期，返回日期类型
     *
     * @param date
     * @return
     */
    public static Date toDate(Date date) {
        return toDate(date, "yyyy-MM-dd");
    }

    public static Date toDate(Date date, String format) {
        String dateStr = getDate(date);
        return toDate(dateStr, format);
    }

    public static Date toDate(String date) {
        return toDate(date, "yyyy-MM-dd");
    }

    public static Date toDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
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
        long[] times = {day, hour, min, sec};
        return times;
    }

	/**
     * 写文件
     *
     * @param path
     * @return
     */
	public static void write(String path, String content) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File(path);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 读文件
     *
     * @param file
     * @return
     */
    public static byte[] toByteArray(File file) {

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

    /**
     * 去掉空白
     *
     * @param text
     * @return
     */
    public String trim1(String text) {
        int len = text.length();
        int st = 0;
        char[] val = text.toCharArray();
        char p;
        while ((st < len) && ((p = val[st]) <= ' ' || p == 160 || p == 12288)) {
            st++;
        }
        while ((st < len) && ((p = val[len - 1]) <= ' ' || p == 160 || p == 12288)) {
            len--;
        }
        return ((st > 0) || (len < text.length())) ? text.substring(st, len) : text;
    }
}
