package com.example.decoder.theme.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.decoder.theme.domain.Theme;
import com.example.decoder.theme.service.ThemeService;

@Controller
public class ThemeController {
	@Autowired
	ThemeService themeService;
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@GetMapping("/admin")
	public String adminPage() {
		return "admin/index";
	}
	
	@GetMapping("/admin/addtheme")
	public String addThemePage() {
		return "admin/addtheme";
	}
    @PostMapping("/admin/addtheme")
    @ResponseBody
    public ResponseEntity<String> addTheme(
            @RequestParam("theme_title") String title,
            @RequestParam("theme_intro") String intro,
            @RequestParam("theme_info") String info,
            @RequestParam("theme_main_img") MultipartFile mainImage,
            @RequestParam("theme_icon_img") MultipartFile iconImage
    ) {
        String mainPath=uploadImg(mainImage);
		String iconPath=uploadImg(iconImage);
		Theme theme=new Theme();
		theme.setTitle(title);
		theme.setIntro(intro);
		theme.setInfo(info);
		theme.setMain_img(mainPath);
		theme.setIcon_img(iconPath);
		themeService.add(theme);
        return ResponseEntity.ok("theme added");
    }
//	private String uploadImg(MultipartFile file,String type) throws IOException{
//		if(!file.isEmpty()) {
//		Path uploadPath=Paths.get(uploadDir);
//		if(!uploadPath.toFile().exists()) {
//			uploadPath.toFile().mkdirs();
//		}
//		String fileName=type + "_" + System.currentTimeMillis() + "_"+file.getOriginalFilename();
//		Path filePath=uploadPath.resolve(fileName);
//		file.transferTo(filePath.toFile());
//		return filePath.toString();
//		}
//		return null;
//	}
	public String uploadImg(MultipartFile request) {
		try {
			String originName = request.getOriginalFilename();
			String[] tempNames=originName.split("[.]");
			String ext="." + tempNames[tempNames.length-1];
			String randomize = UUID.randomUUID()+ext;
			String savePath="C:\\Users\\KGA\\git\\projectDecoder\\decoder\\src\\main\\resources\\static\\img\\"+randomize;
			
			String uploadUrl = "/imgs/" + randomize;
			File file =new File(savePath);
			request.transferTo(file);
			System.out.println(uploadUrl);
			return uploadUrl;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
