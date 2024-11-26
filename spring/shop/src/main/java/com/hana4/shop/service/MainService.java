package com.hana4.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.shop.dao.CustDAO;
import com.hana4.shop.dto.CustDTO;

@Service
public class MainService {

	private List<CustDTO> custs = new ArrayList<>();
	private CustDAO dao;

	public MainService(CustDAO dao) {
		this.dao = dao;
		// CustDTO cust = new CustDTO();
		// cust.setId(1);
		// cust.setName("hong");
		// cust.setTel("010-1234-5678");
		// custs.add(cust);
		// CustDTO cust2 = new CustDTO();
		// cust2.setId(2);
		// cust2.setName("gildong");
		// cust2.setTel("010-4321-8765");
		// custs.add(cust2);
	}

	public List<CustDTO> getCusts() {
		return dao.getCusts();
	}

	public void addCust(CustDTO cust) {
		// int maxId = custs.size() + 1;
		// cust.setId(maxId);
		// custs.add(cust);
		// return maxId;
		dao.insert(cust);
	}

	public CustDTO find(int id) {
		// return custs.get(id - 1);
		return dao.getCust(id);
	}

	public void modify(CustDTO cust) {
		dao.update(cust);
	}

	public void remove(Integer id) {
		dao.delete(id);
	}
}
