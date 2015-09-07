package com.overlord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Position;
import com.overlord.repository.PositionRepository;

@Service("positionService")
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionRepository positionRepository;
	
	@Transactional
	public Position save(Position position) {
		return positionRepository.save(position);
	}

	public Position findByName(String name) {	
		Position position = positionRepository.findByName(name);
		
		if(position != null) {
			return position;
		} 
		
		return new Position();		
	}


	public List<Position> getAllPositions() {
		return positionRepository.findAll();
	}
	
	@Transactional
	public void deletePosition(String id) {
		positionRepository.delete(Integer.parseInt(id));
	}
	

	public Position findByPositionId(String id) {
		return positionRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Position createPosition(Position position) {
		return positionRepository.save(position);
	}
	
	@Transactional
	public Position updatePosition(Position position) {
		return positionRepository.save(position);
	}


}
