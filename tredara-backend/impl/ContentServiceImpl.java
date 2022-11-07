package com.novare.natflix.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novare.natflix.models.Content;
import com.novare.natflix.repositories.ContentRepository;
import com.novare.natflix.services.IContentService;
@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	private ContentRepository contentRepository;
	@Override
	public Content findByContentType(String type) {
		List<Content> findAll = contentRepository.findAll();
		for (Content content : findAll) {
			if(content.getContentType().getType().equalsIgnoreCase(type)) {
				return content;
			}
		}
		return null;
	}
}
