package cn.aparke.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * IO工具类 把一个文本文件逐行加载到内存的lineList对象中
 * 
 * @author aparke
 */
public class IOUtils {
	public static List<String> getLineList(String Filepath, String charset) throws Exception {
		// 将文件通过javaio读取成一行数据的集合
		// 把一个文件通过utf8编码一行一行读成一个集合，对于任何一个文件都适用
		FileInputStream fileInputStream = new FileInputStream(Filepath);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line = null;
		
		ArrayList<String> lineList = new ArrayList<>();
		while ((line=br.readLine())!=null) {
			lineList.add(line);//如果读到不是null，就添加到集合
		}
		br.close();
		
		return lineList;
	}

}
