package com.swpu.logging.logging.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("result")
public class Outcome {
    @TableId("id")
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    @TableField("file_url")
    private String fileUrl;
    @TableField("image_url")
    private String imageUrl;
    @TableField("date")
    private LocalDateTime date;
}
