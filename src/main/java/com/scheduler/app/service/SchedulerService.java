package com.scheduler.app.service;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;

import java.sql.Date;

import java.util.List;

public interface SchedulerService {
    public List<EmpAvailabilityPOJO> getEmployees(Date date);
    public List<DailyShiftPOJO> getShifts(Date date);
    public List<EmpHistoryPOJO> getEmpHistory();


}
