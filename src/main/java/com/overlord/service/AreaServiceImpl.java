package com.overlord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Area;
import com.overlord.repository.AreaRepository;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	@Transactional
	public Area save(Area area) {
		return areaRepository.save(area);
	}

	public Area findByName(String name) {	
		Area area = areaRepository.findByName(name);
		
		if(area != null) {
			return area;
		} 
		
		return new Area();		
	}


	public List<Area> getAllAreas() {
		return areaRepository.findAll();
	}
	
	@Transactional
	public void deleteArea(String id) {
		areaRepository.delete(Integer.parseInt(id));
	}
	

	public Area findByAreaId(String id) {
		return areaRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Area createArea(Area area) {
		return areaRepository.save(area);
	}
	
	@Transactional
	public Area updateArea(Area area) {
		return areaRepository.save(area);
	}


}
