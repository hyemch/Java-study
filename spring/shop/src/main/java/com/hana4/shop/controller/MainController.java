package com.hana4.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hana4.shop.dto.CustDTO;
import com.hana4.shop.service.MainService;

@Controller
public class MainController {

	private MainService service;

	public MainController(MainService service) {
		this.service = service; //좋지않은 방법- 생성자에 적는 것이 좋다.
	}

	@RequestMapping("/")
	public String mainPage(Model model) {
		model.addAttribute("version", "0.1.2");

		List<CustDTO> custs = service.getCusts();
		model.addAttribute("custs", custs);

		List<Integer> list = new ArrayList<>(custs.size());
		for (int i = 0; i < custs.size(); i++) {
			list.add(i + 100);
		}
		model.addAttribute("list", list);

		model.addAttribute("createdate", new Date());

		return "main";
	}

	@GetMapping("/add")
	public String adding(Model model) {
		return "add";
	}

	@PostMapping("/add")
	public String add(CustDTO cust) {
		System.out.println("cust: " + cust);
		if (cust.getEmail().isBlank()) {
			cust.setEmail(null);
		}
		service.addCust(cust);
		return "redirect:/?insertId=" + cust.getId();
	}

	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		System.out.println("id: " + id);
		CustDTO cust = service.find(id);
		model.addAttribute("cust", cust);
		return "modify";
	}

	@PostMapping("/modify/{id}")
	public String update(CustDTO cust) {
		service.modify(cust);
		return "redirect:/";
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer id, Model model) {
		CustDTO cust = service.find(id);
		if (cust == null) {
			model.addAttribute("data", "Cust(#" + id + ") not found");
			model.addAttribute("message", "해당 고객이없습니다.");
			return "not-found";
		}
		service.remove(id);
		return "redirect:/";
	}
}
