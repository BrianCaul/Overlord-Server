package com.overlord.service;

import java.util.Date;
import java.util.List;

import com.overlord.model.Stat;

public interface StatService {
	Stat save(Stat stat);
	List<Stat> findStats(Date startDate, Date endDate, int companyId, int eventId);
	List<Stat> findAllStats(int companyId, int eventId);
}
