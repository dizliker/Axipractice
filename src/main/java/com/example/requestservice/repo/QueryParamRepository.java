package com.example.requestservice.repo;

import com.example.requestservice.model.QueryParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryParamRepository extends JpaRepository<QueryParam, Long> {
}
