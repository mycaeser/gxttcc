package com.caeser.gxttcc.report.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.caeser.gxttcc.entity.Address;

public class AddressReport {
	public List<Map<String,String>> findAll(){
		List<Map<String,String>> listAddress=new ArrayList<Map<String,String>>();
		AddressModel addressModel=new AddressModel();
		for(Address a:addressModel.findAll()) {
			Map<String,String> m=new HashMap<String,String>();
			m.put("add_name", a.getAddName());
			m.put("add_content", a.getAddContent());
			m.put("add_phone_owner", a.getAddPhoneOwner());
			m.put("add_phone_number", a.getAddPhoneNumber());
		}
		return listAddress;
	}
}
