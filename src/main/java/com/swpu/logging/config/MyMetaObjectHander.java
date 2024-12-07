package com.swpu.logging.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class MyMetaObjectHander implements MetaObjectHandler {
    /*字段自动填充*/
//新增时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("create_time", metaObject);

        if (createTime == null) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }

    }
    //更新时自动填充

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("update_time", metaObject);

        if (updateTime == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

    }
}
