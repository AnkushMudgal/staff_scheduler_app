package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "empavailablitynew")
public class EmpavailablitynewPOJO {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "shiftdate")
    @Getter
    @Setter
    private Date shiftdate;


    @Column(name = "shiftday", length = 50)
    @Getter
    @Setter
    private String shiftday;

    @Column(name = "department_id", length = 50)
    @Getter
    @Setter
    private String departmentId;

    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer roleId;

    @Column(name = "employee_id")
    @Getter
    @Setter
    private Integer employeeId;

    @Column(name = "starttime")
    @Getter
    @Setter
    private Timestamp starttime;

    @Column(name = "endtime")
    @Getter
    @Setter
    private Timestamp endtime;



}