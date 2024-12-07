package com.swpu.logging.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomePageDTO {
    private Long pageNumber;
    private Long pageSize;
    private Integer userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
