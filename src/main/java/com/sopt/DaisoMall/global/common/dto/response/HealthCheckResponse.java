package com.sopt.DaisoMall.global.common.dto.response;

public record HealthCheckResponse (
        String status,
        String port
) {
}