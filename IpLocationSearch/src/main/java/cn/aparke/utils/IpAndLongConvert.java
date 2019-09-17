package cn.aparke.utils;
//此类实现两个方法
//ip To long
//long To Ip
public class IpAndLongConvert {
	// 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	public static long IpToLong(String strIp) {
		// 定义一个long数组存放ip
		Long[] ip = new Long[4];
		// 1.先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");// 返回字符串的索引
		int position2 = strIp.indexOf(".", position1 + 1);// 从第一个小数点后开始找
		int position3 = strIp.indexOf(".", position2 + 1);
		
		// 2.将每个.之间的字符串转换成整型substring()字符串的截取参数：beginIndex 开始处的索引（包括）
		// endindex 结尾处索引（不包括）
		ip[0] = Long.parseLong(strIp.substring(0, position1));// 左闭右开
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));// 小数点的索引需要再加一位
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		

		// 左移一位相当于乘以2的一次方，左移n位相当于乘以2的n次方
		// 192.168.1.1
		// 1. 192 * 256^3 ->  2^8^3->2^24  //左移24位 
		// 2. 168 * 256^2 ->  2^8^2->2^16  //左移16位
		// 3. 1 * 256^1   -> 2^8           //左移8位
		// 4. 1 * 256^0					   //不动
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
	
	// 将十进制整数形式转换成127.0.0.1形式的ip地址
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer();

		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));//>>> 无符号右移运算符和右移运算符的主要区别在于负数的计算，因为无符号右移是高位补0，移多少位补多少个0。
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16)); //& 0x00FFFFFF 就是整数的高8位清0其它24位保留
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8)); //& 0x0000FFFF屏掉前两个字节
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));//& 0x000000FF屏掉前三个字节
		return sb.toString();
		/**
		  *  通常int是4个字节，& 0x00ffffff作用是只取后三个字节的值，屏掉第一个字节。
		 * 0x00FFFFFF是什么呢？为什么要&0x00FFFFFF呢？
		 * 0x00FFFFFF是一个16进制数，它的二进制表示形式为00000000 11111111 11111111 11111111
		 * &0x00FFFFFF，它可以使前8为置为0，后24位是1或者0还是不变的。
		 */
	}

	public static void main(String[] args) {

		// 测试，上线后可删除
		String ip = "1.26.112.1";
		long ipLong = IpToLong(ip);
		String ipString = longToIP(ipLong);
		System.out.println(ipLong);
		System.out.println(ipString);
	}

}
