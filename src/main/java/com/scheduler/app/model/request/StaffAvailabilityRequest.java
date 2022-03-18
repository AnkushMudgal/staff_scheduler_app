package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class StaffAvailabilityRequest {

    private String employeeNumber;
    private Integer startTime;
    private Integer endTime;
    private Date availableDate;
    private String availableDay;
}
