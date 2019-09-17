package cn.aparke.manager;

import java.util.ArrayList;
import java.util.List;

import cn.aparke.pojo.IpAndLocationPojo;
import cn.aparke.utils.IOUtils;

//定义一个管理类，组装各个工具实现方法类
//数据加载类
public class DataLoadManager {
	
	public static List<IpAndLocationPojo> StringToList(String ipAddressSource) throws Exception {
		// 把文本文件加载到内存的字符串集合中
		List<String> lineList = IOUtils.getLineList(ipAddressSource, "UTF-8");
		// 把String集合解析成对应的集合对象
		List<IpAndLocationPojo> pojoList = getPojoList(lineList);
		return pojoList;
	}
	
	//定义方法 把String集合解析成对应的集合对象
	public static List<IpAndLocationPojo> getPojoList(List<String> lineList) {
		// 读取数据每行（每行都作为一个集合的对象）
		// 0.0.0.0 0.255.255.255 IANA 保留地址
		// 1.0.0.0 1.0.0.255 澳大利亚 亚太互联网络信息中心

		ArrayList<IpAndLocationPojo> pojoList = new ArrayList<IpAndLocationPojo>();
		// 逐行处理
		for (String line : lineList) {
			line = line.trim();// 去掉每行数据前后的空格
			if (line.length() == 0) {
				continue;
			}
			String[] colArray = line.split("\t");// 分析要处理的数据文件，看是什么隔开的是空格 还是table
			if (colArray.length != 3) { // 判断是否为三个字段，如果没有不要处理
				continue;
			}
			String startIp = colArray[0];
			String endIp = colArray[1];
			String location = colArray[2];
			// 将每个处理好的数据作为一个对象存到集合中
			IpAndLocationPojo pojo = new IpAndLocationPojo(startIp, endIp, location);
			pojoList.add(pojo);
		}
		return pojoList;
	}
	
	//测试类
	public static void main(String[] args) throws Exception {
		String ipAddressSource = "ip_location_relation.txt";// 处理文件位置
		//调用字符串转集合
		List<IpAndLocationPojo> pojoList = StringToList(ipAddressSource);
		
		//打印每个对象
		for (IpAndLocationPojo pojo : pojoList) {
			System.out.println(pojo);
		}
	}
}
