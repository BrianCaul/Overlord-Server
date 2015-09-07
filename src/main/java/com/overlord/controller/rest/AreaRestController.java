package com.overlord.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.overlord.model.Area;
import com.overlord.service.AreaService;
import com.overlord.service.CompanyService;
import com.overlord.service.EventService;
import com.overlord.service.VenueService;
@Controller
@SessionAttributes("area")
public class AreaRestController {

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private VenueService venueService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/areas", method = RequestMethod.GET)
	public @ResponseBody List<Area> getAllAreas() {
		List<Area> areas = null;
		
		try {
			areas = areaService.getAllAreas();
		} catch (Exception e) {
			String sMessage = "Error invoking areas";
			return areas;
		}


		return areas;
	}

	@RequestMapping(value = "/rest/areas/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteArea(@PathVariable String id) {

		try {
			areaService.deleteArea(id);
		} catch (Exception e) {
			String sMessage = "Error invoking delete areas";
			return sMessage;
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/areas/{id}", method = RequestMethod.GET)
	public @ResponseBody Area getArea(@PathVariable String id) {
		Area area = new Area();
		try {
			area = areaService.findByAreaId(id);
		} catch (Exception e) {
			String sMessage = "Error invoking find area by id";
			return area;
		}

		return area;
	}

	@RequestMapping(value = "/rest/areas", method = RequestMethod.POST)
	public @ResponseBody Area createArea(
			@RequestParam("areaName") String areaName,
			@RequestParam("capacity") String capacity,
			@RequestParam("venueId") String venueId) {

		Area area = new Area();
			try {
				area.setAreaName(areaName);
				area.setCapacity(Integer.parseInt(capacity));
				area.setVenue(venueService.findByVenueId(venueId));
			
				area = areaService.createArea(area);
			} catch (Exception e) {
				String sMessage = "Error creating Area";
				return area;
			}

		return area;
	}
	
		
	@RequestMapping(value = "/rest/areas/{id}", method = RequestMethod.POST)
	public @ResponseBody Area updateArea(@PathVariable String id,
			@RequestParam("areaName") String areaName,
			@RequestParam("capacity") String capacity) {

		Area area = new Area();
		try {
			
			area = areaService.findByAreaId(id);
			area.setAreaName(areaName);
			area.setCapacity(Integer.parseInt(capacity));
			area = areaService.updateArea(area);
		} catch (Exception e) {
			String sMessage = "Error updating area";
			return area;
		}

		return area;
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
