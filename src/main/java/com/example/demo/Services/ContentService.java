package com.example.demo.Services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.demo.Model.Content;
import com.example.demo.Model.ContentBackup;
import com.example.demo.Repository.ContentBackupRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Repository.ContentRepository;

@Service
public class ContentService {
	ContentRepository contentRepository;
	ContentBackupRepository contentBackupRepository;

	public ContentService(ContentRepository contentRepository, ContentBackupRepository contentBackupRepository) {
		this.contentRepository = contentRepository;
		this.contentBackupRepository = contentBackupRepository;
	}

	public void write(@ModelAttribute Content content) {
		contentRepository.save(content);
	};
	
	public void remove(int contentId) {
		contentRepository.deleteById(contentId);
	}

	public Content findById(int id) { return contentRepository.findByContentId(id); }

	public List<Content> getAllContents() { return contentRepository.findAll(); }
	public List<ContentBackup> getAllContentBackups() { return contentBackupRepository.findAll(); }
}
