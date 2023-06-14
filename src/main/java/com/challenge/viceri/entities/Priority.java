package com.challenge.viceri.entities;

import java.util.Arrays;
import java.util.Objects;

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

    public static Integer getPriority(String priority){
        return Arrays.stream(Priority.values())
                .filter(p -> p.getDescription().equalsIgnoreCase(priority))
                .map(Priority::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid priority"));
    }

    public static String getPriority(Integer priority) {
        return Arrays.stream(Priority.values())
                .filter(p -> Objects.equals(p.getValue(), priority))
                .map(Priority::getDescription)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid priority"));
    }

    public static Priority fromValue(Integer value) {
        for (Priority priority : values()) {
            if (Objects.equals(priority.getValue(), value)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid priority value: " + value);
    }
}
