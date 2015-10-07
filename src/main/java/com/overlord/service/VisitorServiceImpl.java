package com.overlord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Visitor;
import com.overlord.repository.VisitorRepository;

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {

	@Autowired
	private VisitorRepository visitorRepository;
	
	@Transactional
	public Visitor save(Visitor visitor) {
		return visitorRepository.save(visitor);
	}


	public List<Visitor> getAllVisitors() {
		return visitorRepository.findAll();
	}
	
	@Transactional
	public void deleteVisitor(String id) {
		visitorRepository.delete(Integer.parseInt(id));
	}
	

	public Visitor findByVisitorId(String id) {
		return visitorRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Visitor createVisitor(Visitor visitor) {
		return visitorRepository.save(visitor);
	}
	
	@Transactional
	public Visitor updateVisitor(Visitor visitor) {
		return visitorRepository.save(visitor);
	}


}
