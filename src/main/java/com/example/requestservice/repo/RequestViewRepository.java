package com.example.requestservice.repo;

import com.example.requestservice.model.RequestView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RequestViewRepository extends Repository<RequestView, Long> {

    @Query("""
        SELECT rv FROM RequestView rv
        WHERE (:host       IS NULL OR rv.host       = :host)
          AND (:path       IS NULL OR rv.path       = :path)
          AND (:from       IS NULL OR rv.timestamp >= :from)
          AND (:to         IS NULL OR rv.timestamp <= :to)
          AND (:minHeaders IS NULL OR rv.avgHeaders >= :minHeaders)
          AND (:minParams  IS NULL OR rv.avgParams  >= :minParams)
    """
    )
    Page<RequestView> findFiltered(
            @Param("host")       String host,
            @Param("path")       String path,
            @Param("from")       LocalDateTime from,
            @Param("to")         LocalDateTime to,
            @Param("minHeaders") Integer minHeaders,
            @Param("minParams")  Integer minParams,
            Pageable pageable
    );
}