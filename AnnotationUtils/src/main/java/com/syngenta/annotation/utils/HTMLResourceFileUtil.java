package com.syngenta.annotation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class HTMLResourceFileUtil {
	public String readHtmlFromResource(String filePath)throws FileNotFoundException{
		String htmlText = "";
		ClassLoader classLoader = getClass().getClassLoader();

		File htmlFile = new File(classLoader.getResource(filePath).getFile());
		
		FileInputStream fis = new FileInputStream(htmlFile);
		Scanner scanner = new Scanner(fis);
		while(scanner.hasNext()){
			htmlText+=scanner.nextLine()+"\n";
		}
		scanner.close();
		
		
		return htmlText;
	}
}
