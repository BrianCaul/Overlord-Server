package com.overlord.service;

import java.util.List;

import com.overlord.model.Position;

public interface PositionService {
	Position save(Position position);
	Position findByName(String name);
	List<Position> getAllPositions();
	void deletePosition(String id);
	Position createPosition(Position position);
	Position findByPositionId(String id);
	Position updatePosition(Position position);
}
