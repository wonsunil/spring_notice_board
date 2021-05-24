package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Model.Content;
import com.example.demo.Repository.ContentRepository;

@Service
public class ContentService {
	@Autowired
	ContentRepository contentRepository;
	
	public void write(@ModelAttribute Content content) {
		contentRepository.save(content);
	};
	
	public void remove(int contentId) {
		contentRepository.deleteById(contentId);
	};
	
	public Content findById(int id) {
		return contentRepository.findByContentId(id);
	};
	
	public List<Content> getAllContents() {
		return contentRepository.findAll();
	};
}
