package com.example.demo.Services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.Model.Content;
import com.example.demo.Model.ContentBackup;
import com.example.demo.Repository.ContentBackupRepository;
import org.springframework.stereotype.Service;

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

	public void update(int contentId, Map<String, String> params, String writer) throws InvocationTargetException, IllegalAccessException {
		Content content = contentRepository.findByContentId(contentId);

		if(!content.getContentWriter().equals(writer)) return;

		Method[] methods = content.getClass().getMethods();
		List<Method> setters = Arrays.stream(methods).filter(method -> method.getName().startsWith("set")).collect(Collectors.toList());
		List<String> setterNames = setters.stream().map(Method::getName).collect(Collectors.toList());

		List<String> keys = new ArrayList<>(params.keySet());

		for(String key : keys) {
			String value = params.get(key);
			char[] nameArray = key.toCharArray();
			nameArray[0] = Character.toUpperCase(nameArray[0]);
			setters.get(setterNames.indexOf("set" + new String(nameArray))).invoke(content, value);
		}

		contentRepository.save(content);
	}

	public void remove(int contentId, String writer) throws InvocationTargetException, IllegalAccessException {
		Content content = contentRepository.findByContentId(contentId);

		if(!content.getContentWriter().equals(writer)) return;

		ContentBackup contentBackup = new ContentBackup();
		contentBackup.setDeletedDate(new Date().toString());

		Method[] methods = content.getClass().getMethods();
		List<Method> getters = Arrays.stream(methods).filter(method -> method.getName().startsWith("get")).collect(Collectors.toList());
		List<String> getterNames = getters.stream().map(Method::getName).collect(Collectors.toList());

		List<Method> setters = Arrays.stream(contentBackup.getClass().getMethods()).filter(method -> method.getName().startsWith("set") && !method.getName().equals("setDeletedDate") && !method.getName().equals("setDeletedId")).collect(Collectors.toList());
		List<String> setterNames = setters.stream().map(Method::getName).collect(Collectors.toList());

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
