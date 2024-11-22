package com.hana4.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hana4.demo.domain.User;
import com.hana4.demo.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService service;
	private HttpServletResponse res;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("")
	@ResponseBody
	public List<User> getUsers() {
		return service.getList();
	}

	@PostMapping("")
	@ResponseBody
	public User register(@RequestBody User user) throws BadRequestException {
		System.out.println("user = " + user);
		Long newerId = service.register(user);
		Optional<User> newer = service.getUser(newerId);
		if (newer.isPresent())
			return newer.get();
			// return "reditect:/users/list"
		else
			throw new BadRequestException("Register failed");
	}

	@PostMapping("/add")
	public String registerAdd(@ModelAttribute User user) throws BadRequestException {
		System.out.println("user = " + user);
		Long newerId = service.register(user);
		Optional<User> newer = service.getUser(newerId);
		if (newer.isPresent())
			return "redirect:/users/list";
		else
			throw new BadRequestException("Register failed");
	}

	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", service.getList());
		return "users/list";
	}

	@GetMapping("/{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") Long id) throws IOException {
		Optional<User> user = service.getUser(id);
		System.out.println("user = " + user);
		if (user.isPresent()) {
			return user.get();
		} else {
			res.sendError(404, "User not found!");
			// throw new BadRequestException("User not found!"); BadRequest
			return null;
		}
	}

	private void checkExists(Long id, HttpServletResponse response) throws IOException {
		if (service.getUser(id).isEmpty()) {
			response.sendError(404, "User not found!");
		}
	}

	// @PatchMapping("/users/{id}")
	@RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
	@ResponseBody
	public User updateUser(@RequestBody User user, @PathVariable("id") Long id) throws IOException {
		user.setId(id);
		checkExists(user.getId(), res);
		System.out.println("user = " + user);
		return service.updateUser(user);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public User deleteUser(@PathVariable("id") Long id, HttpServletResponse res) throws IOException {
		checkExists(id, this.res);
		return service.deleteUser(id);
	}

}
