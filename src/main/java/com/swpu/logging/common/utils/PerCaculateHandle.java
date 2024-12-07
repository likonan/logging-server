package com.swpu.logging.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PerCaculateHandle {
    public BigDecimal calculateAdjustedGrowthRate(int currentValue, int pastValue){
        BigDecimal current = new BigDecimal(currentValue);
        BigDecimal past = new BigDecimal(pastValue);

        BigDecimal divisor = past.add(BigDecimal.valueOf(0.00001));

        BigDecimal growthRate = current.subtract(past)
                .divide(divisor, 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        BigDecimal adjustedGrowthRate = growthRate.min(new BigDecimal("999"))
                .setScale(0, RoundingMode.DOWN);

        return adjustedGrowthRate;
    }
}
