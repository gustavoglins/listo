package com.listo.model.task;

public enum TaskStatus {
    PENDING("pending"),
    IN_PROGRESS("in_progress"),
    CONCLUDED("concluded");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
