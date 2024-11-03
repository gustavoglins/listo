package com.listo.model.task;

public enum TaskPriority {
    HIGH("high"),
    MID("mid"),
    LOW("low");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
