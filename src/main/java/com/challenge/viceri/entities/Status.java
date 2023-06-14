package com.challenge.viceri.entities;

import java.util.Arrays;
import java.util.Objects;

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

    public static Integer getStatus(String status){
        return Arrays.stream(Status.values())
                .filter(p -> p.getDescription().equalsIgnoreCase(status))
                .map(Status::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status"));
    }

    public static String getStatus(Integer status) {
        return Arrays.stream(Status.values())
                .filter(p -> Objects.equals(p.getValue(), status))
                .map(Status::getDescription)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status"));
    }

    public static Status fromValue(Integer value) {
        for (Status status : values()) {
            if (Objects.equals(status.getValue(), value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
