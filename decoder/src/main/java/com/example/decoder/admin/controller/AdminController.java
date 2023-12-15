package com.example.decoder.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.decoder.admin.domain.Admin;
import com.example.decoder.admin.service.AdminService;
import com.example.decoder.theme.domain.Theme;
import com.example.decoder.theme.service.ThemeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	ThemeService themeService;
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
	@GetMapping("/admin/bookManagement")
	public String tobookManage(Model model) {
		model.addAttribute("path","/admin/bookManage");
		model.addAttribute("content","bookManageFragment");
		return "admin/adminLayout";
	}
	@GetMapping("/admin/setTimeLines")
	public String toSetTimeLines(Model model) {
		model.addAttribute("path","/admin/setTimeLines");
		model.addAttribute("content","timeLinesFragment");
		return "admin/adminLayout";
	}
	@GetMapping("/admin/login")
	public String login() {
		return "admin/login";
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
	@PostMapping("/admin/card")
	@ResponseBody
	public List<Theme> getcard(){
		List<Theme> list =themeService.getAll();
		System.out.println(list);
		return list;
	}
	@PostMapping("/admin/login")
	public String managerLogin(@RequestParam Map<String,String> map, HttpSession session) {
		String userId=map.get("user_id");
		String userPassword=map.get("user_password");
		if(userId!=null&&userPassword!=null) {
		Admin temp=new Admin();
		temp.setUser_id(userId);
		temp.setUser_password(userPassword);
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
		else {
			return "{login:failed}";
		}
	}
	@PostMapping("/admin/logout")
	public String logOut(HttpSession session) {
		adminService.logOut(session);
		return "redirect:/admin";
	}

}
