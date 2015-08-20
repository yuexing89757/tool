package com.zzy.tool.enumtype;

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
 * 俄罗斯语	ru	葡萄牙语	pt
 * 粤语		yue	文言文	wyw
 * 白话文		zh	自动检测	auto
 * 德语		de	意大利语	it
 * 荷兰语		nl	希腊语	el
*
 */
public enum ConverLanguageType implements IEnumCode{
	zh_en,zh_ara,zh_jp,zh_kor,zh_fra,en_ara,en_zh,;

	public String getCode() {
		return this.name();
	}

	public boolean equals(String code) {
		return this.name().equalsIgnoreCase(code);
	}

}
