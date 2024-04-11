package com.yupi.onlineJudge.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制(ms)
     */
    private Long timeLimit;
    /**
     * 内存限制(kb)
     */
    private String memoryLimit;
    /**
     * 栈限制(kb)
     */
    private String stackLimit;
}
