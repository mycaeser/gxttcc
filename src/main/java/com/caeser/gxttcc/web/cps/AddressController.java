package com.caeser.gxttcc.web.cps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caeser.gxttcc.dao.AddressDao;
import com.caeser.gxttcc.entity.Address;
import com.caeser.gxttcc.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/addresscc")
public class AddressController {
	@Autowired
	private AddressDao addressDao;

	/** 各个公司地址更新 **/
	@RequestMapping(value = "/updateadress", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateAddress(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<String> inputArr=new ArrayList<String>();
		for(int i=1;i<5;i++) {
			inputArr.add( HttpServletRequestUtil.getString(request, "inputC"+i));
		}
		int inputId=HttpServletRequestUtil.getInt(request, "id");
		Address addItem=new Address();
		if(inputId==1) {//如是1，更新的是页面底部网址、备案号、邮箱
			addItem.setAddEmail(inputArr.get(2));
		}else {//如果是2，更新的是地址、地址内容、联系人姓名、联系电话
			addItem.setAddPhoneOwner(inputArr.get(2));
			addItem.setAddPhoneNumber(inputArr.get(3));
		}
		addItem.setAddId(inputId);
		addItem.setAddName(inputArr.get(0));
		addItem.setAddContent(inputArr.get(1));
		int effectedNum=addressDao.updateAddress(addItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "更新地址一项失败");
		}
		return modelMap;
	}
}
