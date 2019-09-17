package cn.aparke.controler;

//主函数入口
import java.util.ArrayList;
import java.util.List;

import cn.aparke.manager.DataSearchManager;
						 
public class SystemControler {
	public static void main(String[] args) throws Exception {
		String ipLibSource = "ip_location_relation.txt";
		String ip1 = "222.89.6.11";
		String ip2 = "222.89.6.12";
		String ip3 = "222.89.6.13";
		String ip4 = "222.89.6.14";
		List<String> ipList = new ArrayList<String>();
		ipList.add(ip1);
		ipList.add(ip2);
		ipList.add(ip3);
		ipList.add(ip4);
		
		DataSearchManager dataSearchManager = new DataSearchManager(ipLibSource);

		for (String ip : ipList) {
			long startTS = System.currentTimeMillis();
			String location = dataSearchManager.getLocationByIPString(ip);
			long endTS = System.currentTimeMillis();
			System.out.println(endTS - startTS);
			System.out.println(location);
		}
	}
}
