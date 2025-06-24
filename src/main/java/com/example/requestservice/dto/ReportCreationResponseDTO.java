package com.example.requestservice.dto;

public class ReportCreationResponseDTO {
    private Long reportId;
    private String message;

    public ReportCreationResponseDTO(Long reportId, String message) {
        this.reportId = reportId;
        this.message = message;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
