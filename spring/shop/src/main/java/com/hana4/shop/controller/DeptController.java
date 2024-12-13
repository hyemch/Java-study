package com.hana4.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hana4.shop.dto.Dept;
import com.hana4.shop.dto.Emp;
import com.hana4.shop.service.DeptService;

@Controller
@RequestMapping("/depts")
public class DeptController {

	private final DeptService service;

	public DeptController(DeptService service) {
		this.service = service;
	}

	@GetMapping("")
	public String getList(Model model) {
		List<Dept> depts = service.getList(0);
		model.addAttribute("depts", depts);
		System.out.println("depts: " + depts);
		return "depts/list";
	}

	@GetMapping("/{id}")
	public String getDetail(@PathVariable Integer id,
		@RequestParam(required = false, defaultValue = "0") int pid, Model model) {
		Dept dept = service.find(id, pid);
		List<Dept> depts = service.getList(id);
		List<Emp> emps = service.getEmps();

		model.addAttribute("dept", dept);
		model.addAttribute("depts", depts);
		model.addAttribute("emps", emps);
		return "depts/detail";
	}

	@PostMapping("/{id}/save")
	public String save(Dept dept) {
		service.save(dept);

		return "redirect:/depts";
	}

	@GetMapping("/{id}/remove")
	public String remove(@PathVariable Integer id, Model model) {
		List<Dept> childrenDepts = service.findByPid(id);
		System.out.println("childrenDepts: " + childrenDepts);
		if (!childrenDepts.isEmpty()) {
			model.addAttribute("message", "참조하는 하위 부서가 존재합니다.");
			return "error";
		}
		service.remove(id);
		return "redirect:/depts";
	}
}
