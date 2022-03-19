package com.scheduler.app.controller;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;
import com.scheduler.app.model.repo.EmployeeHistoryRepository;
import com.scheduler.app.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


@RestController
@RequestMapping("/schedule")
public class SupervisorController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    @GetMapping("/get-schedule")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<EmpAvailabilityPOJO> getEmployees(@RequestParam Date startDate, @RequestParam Date endDate) {
        return  schedulerService.getEmployees(startDate);
    }

    @GetMapping("/dailyshifts")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<DailyShiftPOJO> getShifts(@RequestParam Date shiftDate){
        return schedulerService.getShifts(shiftDate);
    }

    @GetMapping("/emphistory")
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<EmpHistoryPOJO> getEmpHistory(){

        return schedulerService.getEmpHistory();
    }

}
