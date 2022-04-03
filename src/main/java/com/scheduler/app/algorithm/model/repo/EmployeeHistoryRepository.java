package com.scheduler.app.algorithm.model.repo;

import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeHistoryRepository extends JpaRepository<EmpHistoryPOJO, Integer> {
    List<EmpHistoryPOJO> findEmpHistoryById(int employeeId);
    EmpHistoryPOJO saveAndFlush(EmpHistoryPOJO entity);
    boolean existsByEmployeeId(int employeeId);
}
