package cn.aparke.pojo;

import cn.aparke.utils.IpAndLongConvert;

public class IpAndLocationPojo {

	//根据数据文件分析对象
	private String StartIp = null;
	private String EndIp = null;
	private String location = null;
	//以下两个参数在二分查找的时候需要用
	private long startIpLong;
	private long endIpLong;
	
	public IpAndLocationPojo(String startIp, String endIp, String location) {
		super();
		StartIp = startIp;
		EndIp = endIp;
		this.location = location;
		
		//此处调用ipToLong方法，用于二分查找时使用
		this.startIpLong = IpAndLongConvert.IpToLong(StartIp);
		this.endIpLong = IpAndLongConvert.IpToLong(EndIp);
	}
	
	
	public long getStartIpLong() {
		return startIpLong;
	}
	public void setStartIpLong(long startIpLong) {
		this.startIpLong = startIpLong;
	}
	public long getEndIpLong() {
		return endIpLong;
	}
	public void setEndIpLong(long endIpLong) {
		this.endIpLong = endIpLong;
	}
	@Override
	public String toString() {
		return "IpAndLocationPojo [StartIp=" + StartIp + ", EndIp=" + EndIp + ", location=" + location + "]";
	}

	public String getStartIp() {
		return StartIp;
	}
	public void setStartIp(String startIp) {
		StartIp = startIp;
	}
	public String getEndIp() {
		return EndIp;
	}
	public void setEndIp(String endIp) {
		EndIp = endIp;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
