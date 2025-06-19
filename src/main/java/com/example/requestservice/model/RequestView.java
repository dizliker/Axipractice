package com.example.requestservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "request_view")
public class RequestView {

    @Id
    private Long id;

    private String host;
    private String path;

    private LocalDateTime timestamp;

    @Column(name = "avg_headers")
    private Integer avgHeaders;

    @Column(name = "avg_params")
    private Integer avgParams;

    public Long getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getAvgHeaders() {
        return avgHeaders;
    }

    public Integer getAvgParams() {
        return avgParams;
    }
}
