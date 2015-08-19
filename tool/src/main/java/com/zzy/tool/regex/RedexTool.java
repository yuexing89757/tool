package com.zzy.tool.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RedexTool {

	public static void main(String[] args) {
		getChinese("郭凯歌123");
	}

	
	
	/** 
     * 中文提取 
     * @param str 
     * @author 罗嗣金 
     * @date 2009-11-10 上午12:27:17 
     */  
    public static void getChinese(String str){  
        String regex = "[\u4E00-\u9FA5]";//[//u4E00-//u9FFF]为汉字   
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            sb.append(matcher.group());  
        }  
        System.out.println(sb);  
    } 
    
    
    
	/** 
     * 英文提取 
     * @param str 
     * @author 罗嗣金 
     * @date 2009-11-10 上午12:27:17 
     */  
    @Test
    public  void getEnglish(){  
    	String str="() قرار تحكيم CIETAC، الصين، 18 نيسان/أبريل 1991، متاح على الانترنت في الموقع http://www.cietac-sz.org.cn/cietac/alfx/Case/My_03.htm (الدليل لم يعكس شروط التسليم في العقد).";
        String regex = "[a-zA-Z]";//[//u4E00-//u9FFF]为汉字   
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            sb.append(matcher.group());  
        }  
        System.out.println(sb);  
    } 
    
	/** 
     * 网址提取 
     * @param str 
     * @author 罗嗣金 
     * @date 2009-11-10 上午12:27:17 
     */  
    @Test
    public  void getUrl(){  
    	String str="s.rihs-aeby@gichd.org(الدليل لم يعكس شروط التسليم في العقد).";
        String regex = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";//[//u4E00-//u9FFF]为汉字   
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            sb.append(matcher.group());  
        }  
        System.out.println(sb);  
    }  
    
    /** 
     * 邮箱提取 
     * @param str 
     * @author 罗嗣金 
     * @date 2009-11-10 上午12:27:17 
     */  
    @Test
    public void getEmail(){  
    	//String str="guokaige@163.com阿斯达";
    	String str="s.rihs-aeby@gichd.org الدليل لم يعكس شروط التسليم في العقد ";
        String regex2="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$?";
        Pattern pattern = Pattern.compile(regex2);  
        Matcher matcher = pattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            sb.append(matcher.group());  
        }  
        System.out.println(sb);  
    }  
    
    @Test
    public void replacestr(){
    	String str="guokaige@163.com阿斯达";
    	//String str="guokaige@163.com  الدليل لم يعكس شروط التسليم في العقد ";
        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";  
        
        String st2r=str.replace("guo","");
       System.out.println(st2r);
    }
    
}
