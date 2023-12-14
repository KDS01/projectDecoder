package com.example.decoder.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.decoder.admin.domain.Admin;
import com.example.decoder.admin.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	@GetMapping("/admin")
	public String toAdminPage(Model model){
		model.addAttribute("path","/admin/index");
		model.addAttribute("content","adminFragment");
		return "admin/adminLayout";
	}
	@GetMapping("/admin/addtheme")
	public String addThemePage(Model model) {
		model.addAttribute("path","/admin/addtheme");
		model.addAttribute("content","addthemeFragment");
		return "admin/adminLayout";
	}
	@GetMapping("/admin/regist")
	public String addManagerPage(Model model) {
		model.addAttribute("path","/admin/regist");
		model.addAttribute("content","registFragment");
		return "admin/adminLayout";
	}
	@PostMapping("/admin/regist")
	public String addManager(@RequestParam Map<String,String> map) {
		Admin admin=new Admin();
		admin.setUser_id(map.get("user_id"));
		admin.setUser_password(map.get("user_password"));
		admin.setName(map.get("name"));
		adminService.add(admin);
		return "redirect:/admin";
	}
	@PostMapping("/admin/login")
	public String managerLogin(@RequestParam Map<String,String> map, HttpSession session) {
		Admin temp=new Admin();
		temp.setUser_id(map.get("user_id"));
		temp.setUser_password(map.get("user_password"));
		Admin islogIn=adminService.login(temp);
		
		if(islogIn!=null) {
			session.setAttribute("userId", temp.getUser_id());
			session.setAttribute("name", temp.getUser_id());	
			return "redirect:/admin";
		}
		else {
			return null;
		}		
	}
	@PostMapping("/admin/logout")
	public String logOut(HttpSession session) {
		adminService.logOut(session);
		return "redirect:/admin";
	}

}
