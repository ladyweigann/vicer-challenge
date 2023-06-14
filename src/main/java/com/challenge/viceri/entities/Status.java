package com.challenge.viceri.entities;

import java.util.Arrays;

public enum Status {
    PENDING(1, "Pendente"),
    COMPLETED(2, "Concluída");

    private final Integer value;
    private final String description;

    Status(Integer value, String status) {
        this.value = value;
        this.description = status;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority(String status){
        return Arrays.stream(Status.values())
                .filter(p -> p.getDescription().equalsIgnoreCase(status))
                .map(Status::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status"));
    }
}
