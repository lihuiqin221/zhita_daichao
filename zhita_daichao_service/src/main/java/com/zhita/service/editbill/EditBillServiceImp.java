package com.zhita.service.editbill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.EditBillMapper;

@Service
public class EditBillServiceImp implements EditBillService{

	@Autowired
	EditBillMapper editBillMapper;

	@Override
	public int setTUamount(String operationDate, String account, int registrationNumber, int price, int realPay,
			String note, String note2, String accountType, String firmType, String modifyTime, String registrationTime,
			String company) {
		int number = editBillMapper.setTUamount(operationDate, account, registrationNumber, price, realPay,
				note, note, accountType, firmType, modifyTime, registrationTime, company);
		return number;
	}
}