package com.overlord.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Stat;
import com.overlord.repository.StatRepository;

@Service("statService")
public class StatServiceImpl implements StatService {

	@Autowired
	private StatRepository statRepository;
	
	@Transactional
	public Stat save(Stat stat) {
		return statRepository.save(stat);
	}

	public List<Stat> findStats(Date startDate, Date endDate, int companyId, int eventId) {	
		List<Stat> stats = statRepository.findStats(startDate, endDate, companyId, eventId);
		
		return stats;		
	}
	
	public List<Stat> findAllStats(int companyId, int eventId) {	
		List<Stat> stats = statRepository.findAllStats(companyId, eventId);
		
		return stats;		
	}

}
