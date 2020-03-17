package com.caeser.gxttcc.report.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.dao.AddressDao;
import com.caeser.gxttcc.entity.Address;

public class AddressModel {
	
	
	public List<Address> findAll(){
		List<Address> listAddress=new ArrayList<Address>();
		Address ad1=new Address();
		ad1.setAddName("总部");
		ad1.setAddContent("黄泉路111号");
		ad1.setAddPhoneOwner("大眼萌");
		ad1.setAddPhoneNumber("14438273424");
		listAddress.add(ad1);
		
		return listAddress;
	}
}
