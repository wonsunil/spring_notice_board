package com.example.demo.Services;

import java.util.List;

import com.example.demo.Model.Content;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Repository.ContentRepository;

@Service
public class ContentService {
	ContentRepository contentRepository;

	public ContentService(ContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	};
	
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
