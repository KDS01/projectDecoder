package com.example.decoder.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.decoder.book.domain.Book;
import com.example.decoder.book.service.BookService;
import com.example.decoder.theme.domain.Theme;
import com.example.decoder.theme.service.ThemeService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	ThemeService themeService;
	@PostMapping("/book/sendDate")
	@ResponseBody
	public Map<String, Object> getDateData(@RequestParam Map<String,String> data) {
		Map<String, Object> model=new HashMap<String, Object>();
		model.put("path","/book/user");
		model.put("userArea", "userFragment");
		return model;
	}
	@GetMapping("/book/{theme_id}")
	public String BookPage(@PathVariable("theme_id")int theme_id,Model model) {
		Theme theme=themeService.get(theme_id);
		model.addAttribute("title",theme.getTitle());
		model.addAttribute("path","/book/book");
		model.addAttribute("content","bookFragment");
		model.addAttribute("bookArea","dateFragment");
		model.addAttribute("userArea", "userFragment");
		model.addAttribute("contentHead","bookFragmentHead");
		model.addAttribute("theme",theme);
		return "/basic/layout";
	}
	@PostMapping("/book/userdata")
	@ResponseBody
	public String bookTheme(@RequestParam Map<String,String> data) {
		Book book=new Book();
		System.out.println(data.get("booked_date"));
		book.setBooked_time(data.get("booked_hour"));
		book.setBooked_date(data.get("booked_date"));
		book.setBooked_name(data.get("booked_name"));
		book.setPhone(data.get("booked_phone"));
		book.setEmail(data.get("booked_email"));
		book.setPeople_count(Integer.parseInt(data.get("booked_count")));
		book.setBooked_theme(Integer.parseInt(data.get("booked_theme_id")));
		book.setTimeLine_id(Integer.parseInt(data.get("booked_timeline")));
		bookService.add(book);
		return "{result : book successed}";
	}
	@GetMapping("/admin/{theme_id}")
	public String adminBookPage(@PathVariable("theme_id")int theme_id,Model model) {
		Theme theme=themeService.get(theme_id);
		model.addAttribute("title",theme.getTitle()+"예약 정보");
		model.addAttribute("path","/admin/bookManage");
		model.addAttribute("content","bookManageFragment");
		model.addAttribute("theme",theme);
		return "/admin/adminLayout";
	}
	@PostMapping("/admin/bookList")
	@ResponseBody
	public List<Book> setBookList(@RequestParam Map<String,String> map){
		int themeId=Integer.parseInt(map.get("booked_theme"));
		String bookedDate=map.get("booked_time").replace("/", "");
		List<Book> list=bookService.searchBook(themeId, bookedDate);
		return list;
	}
	@PostMapping("/admin/bookCancel")
	public String bookCancel(@RequestParam Map<String,String> map){
		String name=map.get("name");
		String date=map.get("date").replace("-", "");
		String booked_time=map.get("booked_time");
		bookService.bookCancel(name, date, booked_time);
		return "redirect:/admin";
	}
	

}
