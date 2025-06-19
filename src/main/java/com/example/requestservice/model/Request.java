package com.example.requestservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String path;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Header> headers = new ArrayList<>();

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QueryParam> queryParams = new ArrayList<>();

    // Конструкторы
    public Request() {}

    public Request(String url, String path, String body, LocalDateTime timestamp) {
        this.url = url;
        this.path = path;
        this.body = body;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public List<Header> getHeaders() { return headers; }
    public void setHeaders(List<Header> headers) { this.headers = headers; }

    public List<QueryParam> getQueryParams() { return queryParams; }
    public void setQueryParams(List<QueryParam> queryParams) { this.queryParams = queryParams; }
}
