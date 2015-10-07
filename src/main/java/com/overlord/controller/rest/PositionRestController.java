package com.overlord.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.overlord.model.Position;
import com.overlord.model.Stat;
import com.overlord.model.Visitor;
import com.overlord.service.AreaService;
import com.overlord.service.CompanyService;
import com.overlord.service.EventService;
import com.overlord.service.PositionService;
import com.overlord.service.StatService;
import com.overlord.service.VisitorService;
@Controller
@SessionAttributes("position")
public class PositionRestController {

	@Autowired
	private PositionService positionService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private EventService eventService;

	@Autowired
	private AreaService areaService;
	
	@Autowired StatService statService;
	
	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/positions", method = RequestMethod.GET)
	public @ResponseBody List<Position> getAllPositions() {
		List<Position> positions = null;
			try {
			positions = positionService.getAllPositions();
		} catch (Exception e) {
			String sMessage = "Error invoking positions";
			return positions;
		}


		return positions;
	}

	@RequestMapping(value = "/rest/positions/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletePosition(@PathVariable String id) {

		try {
			positionService.deletePosition(id);
		} catch (Exception e) {
			String sMessage = "Error invoking delete positions";
			return sMessage;
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/positions/{id}", method = RequestMethod.GET)
	public @ResponseBody Position getPosition(@PathVariable String id) {
		Position position = new Position();
		try {
			position = positionService.findByPositionId(id);
		} catch (Exception e) {
			String sMessage = "Error invoking find position by id";
			return position;
		}

		return position;
	}

	
	@RequestMapping(value = "/rest/positions", method = RequestMethod.POST)
	public @ResponseBody Position createPosition(
			@RequestParam("areaId") String areaId,
			@RequestParam("positionName") String positionName,
			@RequestParam("positionType") String positionType,
			@RequestParam("positionFunction") String positionFunction) {

		Position position = new Position();

			try {
				position.setPositionName(positionName);
				position.setPositionType(positionType);
				position.setPositionFunction(positionFunction);
				position.setNumVisitors(0);
				position.setArea(areaService.findByAreaId(areaId));
			
				position = positionService.createPosition(position);
			} catch (Exception e) {
				String sMessage = "Error creating Position";
				return position;
			}

		return position;
	}
	
		
	@RequestMapping(value = "/rest/positions/{id}", method = RequestMethod.POST)
	public @ResponseBody Position updatePosition(@PathVariable String id,
			@RequestParam("positionName") String positionName,
			@RequestParam("positionType") String positionType,
			@RequestParam("positionFunction") String positionFunction) {

		Position position = new Position();
		try {
			
			position = positionService.findByPositionId(id);
			position.setPositionName(positionName);
			position.setPositionType(positionType);
			position.setPositionFunction(positionFunction);
			position = positionService.updatePosition(position);
		} catch (Exception e) {
			String sMessage = "Error updating position";
			return position;
		}

		return position;
	}
	
	@RequestMapping(value = "/rest/positions/{id}/enter", method = RequestMethod.POST)
	public @ResponseBody String enterPosition(@PathVariable String id) {

		Position position = new Position();
		try {
			
			position = positionService.findByPositionId(id);
			if(position.getPositionFunction().contentEquals("Exit Only")){
				return "Exit Only";
			}
			
			//Get Count of areas visitors
			if(position.getArea().getVisitors().size() >= position.getArea().getCapacity()){
				return "Area is full";
			}
			
			//Stats
			int eventId = position.getArea().getEvent().getId();
			int companyId = position.getArea().getEvent().getCompany().getId();
			Visitor visitor = new Visitor();
			visitor.setExited("false");
			visitor.setPosition(position);
			visitorService.createVisitor(visitor);
			
			Stat stat = new Stat("Entry", eventId, companyId);
			statService.save(stat);
			
		} catch (Exception e) {
			String sMessage = "Error registering an entry";
			return sMessage;
		}

		return "Entry Successful";
	}
	
	@RequestMapping(value = "/rest/positions/{id}/exit", method = RequestMethod.POST)
	public @ResponseBody String exitPosition(@PathVariable String id) {

		Position position = new Position();
		try {
			
			position = positionService.findByPositionId(id);
			if(position.getPositionFunction().contentEquals("Entry Only")){
				return "Entrance Only";
			}
			
			//Delete a visitor from the area if there are any left
			if(position.getArea().getVisitors().size() == 0){
				return "No visitors left in area";
			}
			Visitor visitor = position.getArea().getVisitors().remove(0);
			visitorService.deleteVisitor(String.valueOf(visitor.getId()));	
			position = positionService.updatePosition(position);
			
			//Stats
			int eventId = position.getArea().getEvent().getId();
			int companyId = position.getArea().getEvent().getCompany().getId();
			Stat stat = new Stat("Exit", eventId, companyId);
			statService.save(stat);
			
		} catch (Exception e) {
			String sMessage = "Error registering an exit";
			return sMessage;
		}

		return "Exit Successful";
	}

	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 * @return
	 */
	private ModelAndView getErrorJSON(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}

}
