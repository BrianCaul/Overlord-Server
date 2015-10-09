package com.overlord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		
		return area;		
	}


	public List<Area> getAllAreas() {
		Sort sort =new Sort(Sort.Direction.ASC, "id");
		return areaRepository.findAll(sort);
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
