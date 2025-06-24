package com.example.requestservice.repo;

import com.example.requestservice.model.RequestView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RequestViewRepository extends JpaRepository<RequestView, Long>, JpaSpecificationExecutor<RequestView> {
}

