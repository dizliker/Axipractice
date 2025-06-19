package com.example.requestservice.repo;

import com.example.requestservice.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeaderRepository extends JpaRepository<Header, Long> {
}
