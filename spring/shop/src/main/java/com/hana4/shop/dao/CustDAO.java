package com.hana4.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hana4.shop.dto.CustDTO;

@Repository
@Mapper //mapper of mybatis
public interface CustDAO {
	Integer addCust(String name, String tel, String email);

	List<CustDTO> getCusts();

	CustDTO getCust(int id);

	void update(CustDTO cust);

	void insert(CustDTO cust);

	void delete(Integer id);
}
