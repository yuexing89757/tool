package com.zzy.tool.charTest;

import java.util.Scanner;

import org.junit.Test;

public class WordNum {
	public static void main(String[] args) {
		int count = 0;
		String sentence = "I am Geng.X.y,she is my girlfriend.Lowood?what is that?";
		Scanner s = new Scanner(sentence).useDelimiter(" |,|\\?|\\.");
		while (s.hasNext()) {
			count++;
			String abc=s.next();
			System.out.print(abc);
			System.out.println(":"+abc.length());
		}
		System.out.println("这段短文单词的个数是：" + count);
	}
	

	
	@SuppressWarnings("resource")
	public Boolean wordLengthTooLong(String sentence){
		Scanner s = new Scanner(sentence).useDelimiter(" |,|\\?|\\.");
		while (s.hasNext()) {
			if(s.next().length()>20) {
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void te(){
		System.out.println(wordLengthTooLong("sdfdsfd  ewrew  fdsfdsfdsfdsfdsfdsfdsfdsasdas"));
	}
	

}