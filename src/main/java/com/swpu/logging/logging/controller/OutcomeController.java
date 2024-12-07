package com.swpu.logging.logging.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swpu.logging.common.result.Result;
import com.swpu.logging.logging.dto.OutcomePageDTO;
import com.swpu.logging.logging.entity.Outcome;
import com.swpu.logging.logging.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {
    @Autowired
    private OutcomeService outcomeService;
    @GetMapping("/outcomePage")
    public Result<?> page(@RequestBody OutcomePageDTO outcomePageDTO) {
        Page<Outcome> page = outcomeService.getOutcomePage(outcomePageDTO);
        JSONObject obj = new JSONObject();
        obj.put("total", page.getTotal());
        obj.put("rows", page.getRecords());
        return new Result<>().success().put(obj);
    }
    //todo
    @GetMapping("/handleFile")
    public Result<?> handleFile(@RequestParam("file") MultipartFile file, Integer userId) throws IOException, InterruptedException {
        // 保存上传的文件到服务器上的某个位置
        String filePath = "/path/to/save/" + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);

        // 创建ProcessBuilder实例，指定要运行的Python脚本路径和文件路径参数
        ProcessBuilder pb = new ProcessBuilder("python", "path/to/your/script.py", filePath);
        pb.redirectErrorStream(true); // 可选：将错误流合并到标准输出流中

        // 启动进程
        Process p = pb.start();

        // 读取Python脚本的输出（如果需要）
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        List<String> list = new ArrayList<>();
        while ((line = in.readLine()) != null) {
            list.add(line);
        }
        in.close();

        // 等待进程完成并获取退出码
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            return new Result<>().error("Python script exited with code " + exitCode);
        }

        // 处理数据库保存逻辑
        Outcome outcome = new Outcome();
        outcome.setUserId(userId);
        // ... 设置其他必要的字段
        outcome.setFileUrl(list.get(0));
        outcome.setImageUrl(list.get(1));
        outcome.setDate(LocalDateTime.now());
        outcomeService.saveOutcome(outcome);

        return new Result<>().success();
    }
}
