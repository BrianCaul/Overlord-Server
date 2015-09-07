package com.overlord.service;

import java.util.List;

import com.overlord.model.Area;

public interface AreaService {
	Area save(Area area);
	Area findByName(String name);
	List<Area> getAllAreas();
	void deleteArea(String id);
	Area createArea(Area area);
	Area findByAreaId(String id);
	Area updateArea(Area area);
}
