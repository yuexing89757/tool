package com.zzy.tool.dateformat;

import java.text.ParseException;
import java.util.Date;

public class DateTool {

	public static void main(String[] args) {
		/*
         * 以下代码用于向大家展示各个时间日期类对象的包含组件。
         */
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        System.out.println(sqlDate.toString()); // 输出结果：2012-09-01
        
        
        java.sql.Time sqlTime = new java.sql.Time(System.currentTimeMillis());
        System.out.println(sqlTime.toString()); // 输出结果：12:35:11
        
        
        java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
        System.out.println(utilDate.toString()); // 输出结果：Sat Sep 01 12:37:34 CST 2012
        
        
        java.util.Calendar cl = java.util.Calendar.getInstance();
        System.out.println(cl.getTime().toString()); // 输出结果：Sat Sep 01 12:39:51 CST 2012
        
        
        
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(sqlDate)); // 输出：2012-09-01 13:20:41 
        System.out.println(simpleDateFormat.format(utilDate)); // 输出：2012-09-01 13:20:41
        System.out.println(simpleDateFormat.format(cl.getTime())); // 输出：2012-09-01 13:20:41
        
        
        //字符串转日期     //Date或者String转化为时间戳  
        try {
			Date date = simpleDateFormat.parse("2015-08-05 11:24:00");
			System.out.println(date.getTime());        // 输出：  1438745040000
			
			//linux时间戳
			Long unixTimestamp = date.getTime()/1000; 
			System.out.println(unixTimestamp);     // 输出：  1438745040
			
		} catch (ParseException e) {
			e.printStackTrace();
		}//解析到一个时间
       
    
        
        
        
	}
	
	

}
