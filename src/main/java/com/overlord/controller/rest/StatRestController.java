package com.overlord.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.overlord.model.Event;
import com.overlord.model.Stat;
import com.overlord.model.StatHolder;
import com.overlord.service.EventService;
import com.overlord.service.StatService;
@Controller
@SessionAttributes("stat")
public class StatRestController {

	@Autowired
	private StatService statService;
	
	@Autowired
	private EventService eventService;

	@Autowired
	private View jsonView;
		
	@RequestMapping(value = "/rest/stats/{eventid}", method = RequestMethod.GET)
	public @ResponseBody StatHolder getStats(@PathVariable String eventid) {

		List<Stat> allStats = new ArrayList<Stat>();
		Map<String, Integer> hours = new HashMap<String, Integer>();
		StatHolder statholder = new StatHolder();
		List<String> hourStrings = new ArrayList<String>();
		List<Integer> valueInts = new ArrayList<Integer>();
		try {
			Date now = new Date();
			
	        Calendar cal = Calendar.getInstance();
	       
			Event event = eventService.findByEventId(eventid);
			Date startDate = event.getStart();
			
			long startlong = startDate.getTime();
			long endlong = now.getTime();
			
			Date date1 = new Date(startlong);
			Date date2 = new Date(endlong);
			
			
			allStats = statService.findStats(date1, date2, event.getCompany().getId(), event.getId());

			
			for(Stat stat : allStats){
				if(stat.getType().contentEquals("Entry")){
					
					//Get all available hours
					Date date = stat.getDate();
					cal.setTime(stat.getDate());
					String hourvalue = String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + ":00";
					if(!hours.containsKey(hourvalue)){
						hours.put(hourvalue, 1);
					}else{
						int value = hours.get(hourvalue);
						if(value>0){
							value = value + 1;
							hours.put(hourvalue, value);
						}else{
							value = 1;
							hours.put(hourvalue, value);
						}
					}
				
				}
			}
			Iterator it = hours.entrySet().iterator();
			 while (it.hasNext()) 
			 {
			     Map.Entry mapEntry = (Map.Entry) it.next();
			     String keyValue = (String) mapEntry.getKey();
			     hourStrings.add(keyValue);
			     int value = (Integer) mapEntry.getValue();
			     valueInts.add(value);
			 }
			
			statholder.setEntries(valueInts);
			statholder.setHours(hourStrings);
			
		} catch (Exception e) {
			e.printStackTrace();
			return statholder;
		}

		return statholder;
	}
	
	
	@RequestMapping(value = "/rest/allstats/{eventid}", method = RequestMethod.GET)
	public @ResponseBody List<Stat> getAllStats(@PathVariable String eventid) {

		List<Stat> allStats = new ArrayList<Stat>();
		
		try {
			
			Event event = eventService.findByEventId(eventid);
			
			allStats = statService.findAllStats(event.getCompany().getId(), event.getId());

			
		} catch (Exception e) {
			e.printStackTrace();
			return allStats;
		}

		return allStats;
	}

}
