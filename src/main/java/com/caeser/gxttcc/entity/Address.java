package com.caeser.gxttcc.entity;

public class Address {
	//地址主键
	private Integer addId;
	//地址名称
	private String addName;
	//具体地址描述
	private String addContent;
	//联系人姓名
	private String addPhoneOwner;
	//联系人电话号码
	private String addPhoneNumber;
	//联系人邮箱
	private String addEmail;
	
	
	public Integer getAddId() {
		return addId;
	}
	public void setAddId(Integer addId) {
		this.addId = addId;
	}
	public String getAddName() {
		return addName;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public String getAddContent() {
		return addContent;
	}
	public void setAddContent(String addContent) {
		this.addContent = addContent;
	}
	public String getAddPhoneOwner() {
		return addPhoneOwner;
	}
	public void setAddPhoneOwner(String addPhoneOwner) {
		this.addPhoneOwner = addPhoneOwner;
	}
	public String getAddPhoneNumber() {
		return addPhoneNumber;
	}
	public void setAddPhoneNumber(String addPhoneNumber) {
		this.addPhoneNumber = addPhoneNumber;
	}
	public String getAddEmail() {
		return addEmail;
	}
	public void setAddEmail(String addEmail) {
		this.addEmail = addEmail;
	}
	
	
}
