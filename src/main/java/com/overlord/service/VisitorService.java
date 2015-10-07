package com.overlord.service;

import java.util.List;

import com.overlord.model.Visitor;

public interface VisitorService {
	Visitor save(Visitor visitor);
	List<Visitor> getAllVisitors();
	void deleteVisitor(String id);
	Visitor createVisitor(Visitor visitor);
	Visitor findByVisitorId(String id);
	Visitor updateVisitor(Visitor visitor);
}
