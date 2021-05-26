package com.example.demo.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.Model.ContentBackup;
import com.example.demo.Model.User;
import com.example.demo.Services.AccountService;
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
	private final AccountService accountService;
	private final ContentService contentService;
	private final Function function = new Function();

	public ContentController(AccountService accountService, ContentService contentService) {
		this.accountService = accountService;
		this.contentService = contentService;
	}

	@GetMapping("/contents")
	@ResponseBody
	public List<Content> getAllContents() {
		return contentService.getAllContents();
	}

	@GetMapping("/backup/contents")
	@ResponseBody
	public List<ContentBackup> getAllContentBackups() {
		return contentService.getAllContentBackups();
	}

	@GetMapping("/write")
	public String writePage(HttpSession session, HttpServletResponse response, Model model) throws IOException {
		if(session.getAttribute("user") == null) {
			function.alert("로그인 이후 이용가능한 기능입니다.", "/login", response);
		}

		model.addAttribute("user", session.getAttribute("user"));

		return "pages/content/write";
	}

	@PostMapping("/write")
	public String write(@ModelAttribute Content content, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			contentService.write(content, user.getId());
		}

		return "redirect:/";
	}

	@GetMapping("/{contentId}")
	public String contentDetail(Model model, @PathVariable(name = "contentId") int contentId) {
		Content findContent = contentService.findById(contentId);

		if(!Objects.isNull(findContent)) model.addAttribute("content", findContent);

		return "pages/content/detail";
	}

	@GetMapping("/{id}/update")
	public String contentUpdatePage(@PathVariable(name = "id") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Content content = contentService.findById(id);

		if(session.getAttribute("user") == null) {
			function.alert("로그인한 유저만 이용가능한 기능입니다", "/login", response);
		}

		User user = (User) session.getAttribute("user");

		if(!user.getId().equals(content.getContentWriter())) {
			function.alert("본인의 게시글만 수정가능합니다", "/content/" + content.getContentId(), response);
		}

		model.addAttribute("content", content);

		return "pages/content/update";
	}

	@PostMapping("/{contentId}/update")
	public String contentUpdate(@PathVariable(name = "contentId") int contentId, @RequestParam Map<String, String> params, HttpSession session) throws InvocationTargetException, IllegalAccessException {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			contentService.update(contentId, params, user.getId());
		}

		return "redirect:/";
	}

	@GetMapping("/{contentId}/delete")
	public String contentDelete(@PathVariable(name = "contentId") int contentId, HttpSession session) throws InvocationTargetException, IllegalAccessException {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			contentService.remove(contentId, user.getId());
		}

		return "redirect:/";
	}
}
