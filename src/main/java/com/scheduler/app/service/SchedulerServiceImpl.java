package com.scheduler.app.service;

import com.scheduler.app.model.entity.DailyShiftPOJO;
import com.scheduler.app.model.entity.DepartmentPOJO;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.EmpAvailabilityPOJO;
import com.scheduler.app.model.entity.EmpHistoryPOJO;
import com.scheduler.app.model.repo.*;
import com.scheduler.app.model.entity.ScheduleCompositeId;
import com.scheduler.app.model.entity.SchedulePOJO;
import com.scheduler.app.util.DateUtil;
import com.scheduler.app.model.request.ScheduleRequest;
import com.scheduler.app.model.response.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class SchedulerServiceImpl implements SchedulerService {

    public String department = "Dept01";
    public int roleId = 2;
    public Map<String, Map> algoMap = new HashMap<>();

    @Autowired
    EmpavailablitynewRepository empavailablitynewRepository;

    @Autowired
    EmpAvailabilityRepository empAvailabilityRepository;

    @Autowired
    DailyShiftRepository dailyShiftRepository;

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public Map<String, Map> getEmployees(Date startDate) {
        Date date = DateUtil.addDays(startDate, -1);

        // for each day
        for(int day = 0; day < 7; day++) {
            date = DateUtil.addDays(date, 1);
            List<DailyShiftPOJO> dayShifts = getShifts(date);
            Map<String, Set<Integer>> departmentRoleMap = getDepartMentRolesMap(dayShifts);

            Date finalDate = date;
            Map<String, Map> departmentRoleDetailsMap = new HashMap<>();
            // for each department
            departmentRoleMap.forEach((k, v) -> {
                Map<Integer, List> roleMap = new HashMap<>();
                Set<Integer> roles = departmentRoleMap.get(k);

                // for each role
                for (Integer roleId : roles) {
                    List<EmpAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(finalDate, k, roleId);
                    roleMap.put(roleId, availableEmployees);
                }
                departmentRoleDetailsMap.put(k, roleMap);

            });
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            algoMap.put(dateString, departmentRoleDetailsMap);
        }

        return algoMap;
    }

    public Map<String, Set<Integer>> getDepartMentRolesMap(List<DailyShiftPOJO> dayShifts) {
        Map<String, Set<Integer>> departmentRoles = new HashMap();
        List<String> departments = new ArrayList<>();
        for(DailyShiftPOJO shift: dayShifts) {
            DepartmentPOJO departmentInfo = shift.getDepartment();
            String departmentId = departmentInfo.getId();
            if(departments.contains(departmentId)) {
                Set<Integer> roles = departmentRoles.get(departmentId);
                roles.add(shift.getRoleId());
                departmentRoles.put(departmentId, roles);
            }else {
                Set<Integer> roles = new HashSet<>();
                departments.add(departmentId);
                roles.add(shift.getRoleId());
                departmentRoles.put(departmentId, roles);
            }
        }
        return departmentRoles;
    }

    public List<EmpAvailabilityPOJO> getShiftRoleEmployees(Date date, String department, Integer roleId) {
        List<EmpAvailabilityPOJO> availableEmployees = empAvailabilityRepository.findEmployeeByDateAndRoleAndDeparment(date, department, roleId);
        for(EmpAvailabilityPOJO employee: availableEmployees) {
            System.out.println(employee.getEmployeeId() +" " +  employee.getStartTime() + " " + employee.getEndTime());
        }
        return availableEmployees;
    }




    public List<DailyShiftPOJO> getShifts(Date date) {
        List<DailyShiftPOJO> dailyShiftList = dailyShiftRepository.findByShiftDate(date);
        return dailyShiftList;
    }

    @Override
    public List<EmpHistoryPOJO> getEmpHistory() {
        return null;
    }

    public List<EmpHistoryPOJO> getEmpHistory(int employeeId) {
        List<EmpHistoryPOJO> empHistoryList = employeeHistoryRepository.findAll();
        return empHistoryList;
    }

    public void addEmpHistory(int employeeId) {

    }

    @Override
    public ScheduleResponse getScheduleByDateTime(ScheduleRequest scheduleRequest) {

        if(scheduleRequest.getShiftDate() == null && scheduleRequest.getShiftTime() == null){
            return new ScheduleResponse(REQUEST_STATUS.INVALID_REQUEST, false, Collections.emptyMap());

        } else {

            ScheduleCompositeId compositeId = new ScheduleCompositeId(scheduleRequest.getShiftDate(), scheduleRequest.getShiftTime());
            Map<String, SchedulePOJO> schedule = new HashMap<>();
            Optional<SchedulePOJO> scheduleOutput = scheduleRepository.findById(compositeId);

            if (scheduleOutput.isPresent()) {

                schedule.put(scheduleOutput.get().getDepartment().getId(), scheduleOutput.get());
                return new ScheduleResponse(REQUEST_STATUS.SUCCESS, true, schedule);
            } else {

                return new ScheduleResponse(REQUEST_STATUS.SUCCESS, false,  Collections.emptyMap());
            }

        }
    }

    @Override
    public List<DailyShiftPOJO> getAllShiftDetails() {

        return null;
    }


}
