package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
	public Content findByContentId(int contentId);
	public List<Content> findAll();
}