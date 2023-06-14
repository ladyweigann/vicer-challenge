package com.challenge.viceri.entities;

import java.util.Arrays;

public enum Priority {
    LOW(1, "Baixa"),
    MEDIUM(2, "Média"),
    HIGH(3, "Alta");

    private final Integer value;
    private final String description;

    Priority(Integer value, String priority) {
        this.value = value;
        this.description = priority;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority(String priority){
        return Arrays.stream(Priority.values())
                .filter(p -> p.getDescription().equalsIgnoreCase(priority))
                .map(Priority::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid priority"));
    }
}
