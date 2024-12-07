package com.swpu.logging.logging.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.logging.logging.dto.OutcomePageDTO;
import com.swpu.logging.logging.entity.Outcome;
import com.swpu.logging.logging.mapper.OutcomeMapper;
import com.swpu.logging.logging.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutcomeServiceImpl extends ServiceImpl<OutcomeMapper, Outcome> implements OutcomeService {
    @Autowired
    private OutcomeMapper outcomeMapper;
    @Override
    public Page<Outcome> getOutcomePage(OutcomePageDTO outcomePageDTO) {
        Page<Outcome> page = new Page<>(outcomePageDTO.getPageNumber(), outcomePageDTO.getPageSize());
        QueryWrapper<Outcome> w = new QueryWrapper<>();
        if (outcomePageDTO.getUserId() != null) {
            w.eq("user_id", outcomePageDTO.getUserId());
        }
        // 添加时间范围过滤条件
        if (outcomePageDTO.getStartTime() != null) {
            w.ge("date", outcomePageDTO.getStartTime());
        }

        if (outcomePageDTO.getEndTime() != null) {
            w.lt("date", outcomePageDTO.getEndTime());
        }

        this.page(page, w);
        return page;
    }

    @Override
    public void saveOutcome(Outcome outcome) {
        this.save(outcome);
    }
}
