package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Model.Content;
import com.example.demo.Services.ContentService;

@Controller
public class MainController {
	private final ContentService contentService;
	
	public MainController(ContentService contentService) {
		this.contentService = contentService;
	}
	
	@GetMapping(value = {"/", "/index"})
	public String main(HttpSession session, Model model) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("user", session.getAttribute("user"));
		}
		
		List<Content> contents = contentService.getAllContents();
		
		if(contents.size() != 0) {
			model.addAttribute("contents", contents);
		}
		
		return "index";
	}
}
