package com.example.requestservice.dto;

public class ReportStatusDTO {
    private Long id;
    private String status;
    private String errorMessage;

    public ReportStatusDTO(Long id, boolean done, String errorMessage) {
        this.id = id;
        if (errorMessage != null && !errorMessage.isEmpty()) {
            this.status = "ERROR";
        } else if (done) {
            this.status = "SUCCESS";
        } else {
            this.status = "IN_PROGRESS";
        }
        this.errorMessage = errorMessage;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
