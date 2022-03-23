package com.scheduler.app.model.repo;

import com.scheduler.app.model.entity.EmpavailablitynewPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpavailablitynewRepository extends JpaRepository<EmpavailablitynewPOJO, Integer> {
    List<EmpavailablitynewPOJO> findAllBy();
}
