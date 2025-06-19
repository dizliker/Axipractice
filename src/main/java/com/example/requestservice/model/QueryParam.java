package com.example.requestservice.model;

import jakarta.persistence.*;

@Entity
public class QueryParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "query_key")
    private String key;
    @Column(name = "query_value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    // Конструкторы
    public QueryParam() {}

    public QueryParam(String key, String value, Request request) {
        this.key = key;
        this.value = value;
        this.request = request;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }
}
