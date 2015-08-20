package com.zzy.tool.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: LanguageType 
* @Description: 定义一个枚举用以验证文件的路径 
* @author chenshuai 
* @date 2015-7-23 下午5:34:25 
 * 百度语言种类
 * 
 * 中文		zh	英语		en
 * 日语		jp	韩语		kor
 * 西班牙语	spa	法语		fra
 * 泰语		th	阿拉伯语	ara
 * 俄语	    ru	葡萄牙语	pt
 * 粤语		yue	文言文	wyw
 * 简体中文	zh	自动检测	auto
 * 德语		de	意大利语	it
 * 荷兰语		nl	希腊语	el
*
 */
public class LanguageTool{
	
	public static final String memory_en= "英语";
    public static final String memory_zh= "简体中文";
    public static final String memory_ara = "阿拉伯语";
    public static final String memory_kor = "韩语";
    public static final String memory_ru = "俄语";
    public static final String memory_it = "意大利语";
    public static final String memory_jp = "日语";
    public static final String memory_fra = "法语";
    
    public static Map<String,String> memoryLANGMap=new HashMap<String,String>();
    static{
    	memoryLANGMap.put(LanguageType.en.toString(), LanguageTool.memory_en);
    	memoryLANGMap.put(LanguageType.zh.toString(), LanguageTool.memory_zh);
    	memoryLANGMap.put(LanguageType.ara.toString(), LanguageTool.memory_ara);
    	memoryLANGMap.put(LanguageType.kor.toString(), LanguageTool.memory_kor);
    	memoryLANGMap.put(LanguageType.ru.toString(), LanguageTool.memory_ru);
    	memoryLANGMap.put(LanguageType.it.toString(), LanguageTool.memory_it);
    	memoryLANGMap.put(LanguageType.jp.toString(), LanguageTool.memory_jp);
    	memoryLANGMap.put(LanguageType.fra.toString(), LanguageTool.memory_fra);
    }
    
    
    public static final String youDao_en= "eng";
    public static final String youDao_kor = "ko";
    public static final String youDao_jp = "jap";
    public static final String youDao_fra = "fr";
    
    public static Map<String,String> youDaoLANGMap=new HashMap<String,String>();
    static{
    	youDaoLANGMap.put(LanguageType.en.toString(), LanguageTool.youDao_en);
    	youDaoLANGMap.put(LanguageType.kor.toString(), LanguageTool.youDao_kor);
    	youDaoLANGMap.put(LanguageType.jp.toString(), LanguageTool.youDao_jp);
    	youDaoLANGMap.put(LanguageType.fra.toString(), LanguageTool.youDao_fra);
    }
    
    
    
    
    
}
