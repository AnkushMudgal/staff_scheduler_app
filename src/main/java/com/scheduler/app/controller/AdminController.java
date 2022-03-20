package com.scheduler.app.controller;

import com.scheduler.app.model.request.EmployeeCreationRequest;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.EmployeeCreationResponse;
import com.scheduler.app.model.response.HolidayCreationResponse;
import com.scheduler.app.service.EmployeeCreationService;
import com.scheduler.app.service.HolidayCreationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/admin")
public class AdminController {



    @Autowired
    EmployeeCreationService employeeCreationService;
    @Autowired
    HolidayCreationService holidayCreationService;

    @Operation(summary = "Register the employees and store the data to DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee is successfully created and added to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeCreationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/create/employee")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    EmployeeCreationResponse createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest){

        return employeeCreationService.createNewEmployee(employeeCreationRequest);
    }

    @Operation(summary = "Store the holidays declared by admin to holiday table in DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Holiday is successfully created and added to DB",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HolidayCreationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content)})
    @PostMapping("/create/holiday")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public @ResponseBody
    HolidayCreationResponse createHoliday(@RequestBody HolidayCreationRequest holiday){

        return holidayCreationService.addNewHoliday(holiday);
    }
}
