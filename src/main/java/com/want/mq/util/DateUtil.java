package com.want.mq.util;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class DateUtil {
	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	public static final SimpleDateFormat getDateFormat() {
		SimpleDateFormat format = threadLocal.get();
		if (format == null) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			threadLocal.set(format);
		}
		return format;
	}
	//支持的格式为“YYYY-MM-DD HH:mm:ss”和“YYYY-MM-DD”
	public static boolean isDateTime(String datetime){
		  Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		  return p.matcher(datetime).matches();
		}
}
