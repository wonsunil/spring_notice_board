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

	public void write(Content content, String writer) {
		if(!content.getContentWriter().equals(writer)) return;

		contentRepository.save(content);
	}

	public void remove(int contentId) throws InvocationTargetException, IllegalAccessException {
		Content content = contentRepository.findByContentId(contentId);

		if(!content.getContentWriter().equals(writer)) return;

		ContentBackup contentBackup = new ContentBackup();

		contentBackup.setDeletedDate(new Date().toString());

		Method[] methods = content.getClass().getMethods();
		List<Method> getters = new ArrayList<>();
		List<String> getterNames = new ArrayList<>();

		Arrays.stream(methods).filter(method -> method.getName().startsWith("get")).forEach(getters::add);
		getters.stream().map(Method::getName).forEach(getterNames::add);

		List<Method> setters = new ArrayList<>();
		List<String> setterNames = new ArrayList<>();

		Arrays.stream(contentBackup.getClass().getMethods()).filter(method -> method.getName().startsWith("set") && !method.getName().equals("setDeletedDate") && !method.getName().equals("setDeletedId")).forEach(setters::add);
		setters.stream().map(Method::getName).forEach(setterNames::add);

		for(String setter: setterNames) {
			setters.get(setterNames.indexOf(setter)).invoke(contentBackup, getters.get(getterNames.indexOf(setter.replace("set", "get"))).invoke(content));
		}

		contentBackupRepository.save(contentBackup);

		contentRepository.deleteById(contentId);
	}

	public Content findById(int id) { return contentRepository.findByContentId(id); }

	public List<Content> getAllContents() { return contentRepository.findAll(); }
	public List<ContentBackup> getAllContentBackups() { return contentBackupRepository.findAll(); }
}
