package cn.ymq.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Dictionary implements Serializable {
	private Integer dtid;
	private String item;
	private String title;
	private String value;
	
	public Integer getDtid() {
		return dtid;
	}
	public void setDtid(Integer dtid) {
		this.dtid = dtid;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
