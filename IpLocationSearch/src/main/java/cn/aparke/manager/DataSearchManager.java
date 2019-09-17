package cn.aparke.manager;

import java.util.List;

import cn.aparke.pojo.IpAndLocationPojo;
import cn.aparke.utils.IpAndLongConvert;

public class DataSearchManager {
	private IpAndLocationPojo[] sortedPojoArray = null;

	// 定义有参构造类，将字符转为集合，将集合在转为数组
	public DataSearchManager(String ipAddressLib) throws Exception {

		// 实例化当前类的时候
		// 将数据文件中的字符串转为集合
		List<IpAndLocationPojo> toList = DataLoadManager.StringToList(ipAddressLib);
		// 定义一个对象数组
		IpAndLocationPojo[] pojoArray = new IpAndLocationPojo[0];
		// 将集合转为数组，利用二分查找解决最终问题
		sortedPojoArray = toList.toArray(pojoArray);
	}

	// 定义二分查找字符串方法，核心算法
	public int getIndexByBinarySearch(IpAndLocationPojo[] sortedArray, int startPos, int endPos, long ipLong) {
		// 判断startPos>endPos 处理有顺序的数组 由小到大
		if (startPos < 0 || endPos > sortedArray.length || startPos > endPos) {
			return -1;
		}
		//定义中间位置的索引
		int middle =(startPos+endPos)/2;
		//如果查找的这个ipLong 大于中间位置的结束ip ，递归右边继续查找
		if (ipLong>sortedArray[middle].getEndIpLong()) {
			return getIndexByBinarySearch(sortedArray, middle+1, endPos, ipLong);
		}
		//如果查找的这个ipLong 小于中间位置的结束ip ，递归左边继续查找
		if (ipLong<sortedArray[middle].getEndIpLong()) {
			return getIndexByBinarySearch(sortedArray,startPos , middle-1, ipLong);
		}
		//命中则返回索引位置
		return middle;

	}
	
	//调用二分查找函数
	// 判断查找的ip地址合法性判断
	public  String getLocationByIPString(String ip) throws Exception { // ipAddressLib ip库
		String[] numArray = ip.split("\\.");
		for (String num : numArray) {
			try {
				int numIntValue = Integer.parseInt(num);
				if (numIntValue < 0 || numIntValue > 255) {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
		
		int startPos = 0;
		int endPos = sortedPojoArray.length - 1;

		long aidIpLong = IpAndLongConvert.IpToLong(ip);
		int pos = getIndexByBinarySearch(sortedPojoArray, startPos, endPos, aidIpLong);
		if (pos > -1) {
			return sortedPojoArray[pos].getLocation();
		}
		return null;// 没找到
	}

}
