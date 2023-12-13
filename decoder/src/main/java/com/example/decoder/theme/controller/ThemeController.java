package com.example.decoder.theme.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.decoder.theme.domain.Theme;
import com.example.decoder.theme.service.ThemeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ThemeController {
	@Autowired
	ThemeService themeService;
	@GetMapping("/")
	public String toMainPage(Model model) {
		model.addAttribute("title","Decoder / 디코더");
		model.addAttribute("content","mainFragment");
		model.addAttribute("path","/basic/main");
		return "basic/layout";
	}
	@GetMapping("/about")
	public String toAboutPage(Model model) {
		model.addAttribute("title","About Decoder");
		model.addAttribute("content","aboutFragment");
		model.addAttribute("path","/basic/about");
		
		return "basic/layout";
	}
//	@Value("${file.upload-dir}")
//	private String uploadDir;
//	
	@GetMapping("/admin")
	public String adminPage() {
		return "admin/index";
	}
	@GetMapping("/basic/header")
	public String getHeader(){
		return "basic/header.html";
		
	}
	@GetMapping("/basic/footer")
	public String getFooter(){
		return "basic/footer.html";
	}
	@GetMapping("/admin/addtheme")
	public String addThemePage() {
		return "admin/addtheme";
	}
	@GetMapping("/codeplay")
	public String toHobbyPage(Model model) {
		model.addAttribute("title","codePlay - Decoder/ 디코더");
		model.addAttribute("content","hobbyFragment");
		model.addAttribute("path","/basic/hobby");
		return "basic/layout";
	}
	@GetMapping("/contact")
	public String toContactPage() {
		return "basic/contact";
	}
	@PostMapping("/basic/header")
	@ResponseBody
	public List<Theme> getHeaderList() {
		List<Theme> list=themeService.getAll();
		return list;
	}
	@PostMapping("/basic/sector")
	@ResponseBody
	public List<Theme> getSectorList() {
		List<Theme> list=themeService.getAll();
		System.out.println(list);
		return list;
	}
    @PostMapping("/admin/addtheme")
    public String addTheme(
            @RequestParam("theme_title") String title,
            @RequestParam("theme_intro") String intro,
            @RequestParam("theme_info") String info,
            @RequestParam("theme_main_img") String mainImage,
            @RequestParam("theme_icon_img") String iconImage
    ) {
		Theme theme=new Theme();
		theme.setTitle(title);
		theme.setIntro(intro);
		theme.setInfo(info);
		theme.setMain_img(mainImage);
		theme.setIcon_img(iconImage);
		themeService.add(theme);
        return "redirect:/admin";
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
//    @PostMapping("/admin/addtheme/img")
//    @ResponseBody
//	public String uploadImg(MultipartFile request) {
//		try {
//			String originName = request.getOriginalFilename();
//			String[] tempNames=originName.split("[.]");
//			String ext="." + tempNames[tempNames.length-1];
//			String randomize = UUID.randomUUID()+ext;
//			
//			String savePath="C:\\Users\\KGA\\git\\projectDecoder\\decoder\\src\\main\\resources\\static\\img\\"+randomize;
//			String uploadUrl = "/imgs/" + randomize;
//			File file =new File(savePath);
//			request.transferTo(file);
//			System.out.println(uploadUrl);
//			return uploadUrl;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
//	}

    @PostMapping("/admin/addtheme/img")
    @ResponseBody
    public Map<String,String> uploadImg(@RequestParam("upload") MultipartFile file) {
        Map<String,String> map= new HashMap<String,String>();
    	try {
        	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
             String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        	System.out.println("file: " + file.getOriginalFilename());
            if (file.isEmpty()) {
                return null;
            }

            String uploadDir = "C:\\Users\\KGA\\git\\projectDecoder\\decoder\\src\\main\\resources\\static\\img\\";
            
            String originalFileName = file.getOriginalFilename();
            String[] fileNameArray = originalFileName.split("\\.");
            String extension = fileNameArray[fileNameArray.length - 1];

            String randomFileName = UUID.randomUUID() + "." + extension;

            File saveFile = new File(uploadDir + randomFileName);
            
            file.transferTo(saveFile);

            String imageUrl ="/img/" + randomFileName;
            
            map.put("uploaded",imageUrl);
            map.put("url", imageUrl);
            System.out.println(map);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
