package com.example.demo.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Model.Content;
import com.example.demo.Services.ContentService;
import com.example.demo.Function;

@Controller
@RequestMapping("/content")
public class ContentController {
	private final ContentService contentService;
	private final Function function = new Function();
	
	public ContentController(ContentService contentService) {
		this.contentService = contentService;
	};
	
	@GetMapping("/contents")
	@ResponseBody
	public List<Content> getAllContents() {
		return contentService.getAllContents();
	};
	
	@GetMapping("/write")
	public String writePage(HttpSession session, HttpServletResponse response, Model model) throws IOException {
		if(session.getAttribute("user") == null) {
			function.alert("로그인 이후 이용가능한 기능입니다.", "/login", response);
		};
		
		model.addAttribute("user", session.getAttribute("user"));
		
		return "pages/content/content-write";
	};
	
	@PostMapping("/write")
	public String write(@ModelAttribute Content content) {
		contentService.write(content);
		
		return "index";
	}
	
	@GetMapping("/{contentId}")
	public String contentDetail(Model model, @PathVariable(name = "contentId") int contentId) {
		Content findContent = contentService.findById(contentId);
		
		if(!Objects.isNull(findContent)) model.addAttribute("content", findContent);
		
		return "pages/content/content-detail";
	};
	
	@PostMapping("/{contentId}/update")
	public String contentUpdate(@PathVariable(name = "contentId") int contentId, @RequestParam Map<String, String> params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IndexOutOfBoundsException {
		Content content = contentService.findById(contentId);
		
		Method[] methods = content.getClass().getMethods();
		List<Method> setters = new ArrayList<>();
		List<String> setterNames = new ArrayList<>();
		
		Arrays.stream(methods).filter(method -> method.getName().startsWith("set")).forEach(method -> setters.add(method));
		setters.forEach(setter -> setterNames.add(setter.getName()));
		
		List<String> keys = new ArrayList<String>(params.keySet());

		for(String key : keys) {
			String value = params.get(key);
			char[] nameArray = key.toCharArray();
			nameArray[0] = Character.toUpperCase(nameArray[0]);
			setters.get(setterNames.indexOf("set" + new String(nameArray))).invoke(content, value);
		}

		contentService.write(content);
		
		return "redirect:/";
	};
	
	@PostMapping("/{contentId}/delete")
	public String contentDelete(@PathVariable(name = "contentId") int contentId) {
		contentService.remove(contentId);
		
		return "redirect:/";
	};
}
