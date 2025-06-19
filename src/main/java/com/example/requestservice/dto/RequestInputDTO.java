package com.example.requestservice.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RequestInputDTO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("path")
    private String path;
    @JsonProperty("headers")
    private Map<String, String> headers;
    @JsonProperty("variable_params")
    private Map<String, String> variable_params;
    @JsonProperty("body")
    private String body;

    public RequestInputDTO() {}

    // Геттеры и сеттеры
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public Map<String, String> getHeaders() { return headers; }
    public void setHeaders(Map<String, String> headers) { this.headers = headers; }

    public Map<String, String> getVariable_params() { return variable_params; }
    public void setVariable_params(Map<String, String> variable_params) { this.variable_params = variable_params; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
