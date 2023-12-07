package com.example.decoder.theme.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.decoder.theme.domain.Theme;
import com.example.decoder.theme.service.ThemeService;

@Controller
@RequestMapping("/admin")
public class ThemeController {
	@Autowired
	ThemeService themeService;
	@Value("${file.upload-dir}")
	private String uploadDir;
	@GetMapping("/")
	public String adminPage() {
		return "redirect:/";
	}
	
	@GetMapping("/addTheme")
	public String addThemePage() {
		return "redirect:/addtheme";
	}
    @PostMapping("/addtheme")
    @ResponseBody
    public ResponseEntity<String> addTheme(
            @RequestParam("theme_title") String title,
            @RequestParam("theme_intro") String intro,
            @RequestParam("theme_info") String info,
            @RequestParam("theme_main_img") MultipartFile mainImage,
            @RequestParam("theme_icon_img") MultipartFile iconImage
    ) {

        try {
        	String mainPath=uploadImg(mainImage, "main");
        	String iconPath=uploadImg(iconImage, "icon");
        	Theme theme=new Theme();
        	theme.setTitle(title);
        	theme.setIntro(intro);
        	theme.setInfo(info);
        	theme.setTitle(title);
        	theme.setMain_img(mainPath);
        	theme.setIcon_img(iconPath);
        	themeService.add(theme);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("failed: " + e.getMessage());
        }

        return ResponseEntity.ok("theme added");
    }
	private String uploadImg(MultipartFile file,String type) throws IOException{
		if(!file.isEmpty()) {
		Path uploadPath=Paths.get(uploadDir);
		if(!uploadPath.toFile().exists()) {
			uploadPath.toFile().mkdirs();
		}
		String fileName=type + "_" + System.currentTimeMillis() + "_"+file.getOriginalFilename();
		Path filePath=uploadPath.resolve(fileName);
		file.transferTo(filePath.toFile());
		return filePath.toString();
		}
		return null;
	}

}
