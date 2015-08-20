package com.zzy.tool.enumtype;

public enum ParseUrlType implements IEnumCode{

	JSOUP,HTTPUNIT;

	public String getCode() {
		return this.name();
	}

	public boolean equals(String code) {
		return this.name().equalsIgnoreCase(code);
	}

}
