package com.hana4.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.shop.dao.DeptRepository;
import com.hana4.shop.dto.Dept;
import com.hana4.shop.dto.Emp;

@Service //스프링 빈 선언
public class DeptService {
	private DeptRepository repository;

	public DeptService(DeptRepository repository) {
		this.repository = repository;
	}

	public List<Dept> getList(int id) {
		return repository.getList(id);
	}

	public Dept find(Integer id, int pid) {
		if (id == 0) {
			Dept dept = new Dept();
			dept.setPid(pid);
			dept.setDname("신규 부서");
			return dept;
		}
		return repository.find(id);
	}

	public List<Emp> getEmps() {
		return repository.getEmps();
	}

	public void save(Dept dept) {
		if (dept.getId() == 0) {
			repository.insert(dept);
		} else {
			repository.update(dept);
		}
	}

	public List<Dept> findByPid(int id) {
		return repository.findByPid(id);
	}

	public void remove(Integer id) {
		repository.delete(id);
	}
}
