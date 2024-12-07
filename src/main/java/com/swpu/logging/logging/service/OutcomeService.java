package com.swpu.logging.logging.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swpu.logging.logging.dto.OutcomePageDTO;
import com.swpu.logging.logging.entity.Outcome;

public interface OutcomeService {
    Page<Outcome> getOutcomePage(OutcomePageDTO outcomePageDTO);


    void saveOutcome(Outcome outcome);
}
