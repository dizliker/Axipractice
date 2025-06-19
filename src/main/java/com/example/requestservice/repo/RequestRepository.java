package com.example.requestservice.repo;

import com.example.requestservice.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
