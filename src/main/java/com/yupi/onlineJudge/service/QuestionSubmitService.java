package com.yupi.onlineJudge.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.onlineJudge.model.dto.question.QuestionQueryRequest;
import com.yupi.onlineJudge.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.onlineJudge.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.onlineJudge.model.entity.Question;
import com.yupi.onlineJudge.model.entity.QuestionSubmit;
import com.yupi.onlineJudge.model.entity.User;
import com.yupi.onlineJudge.model.vo.QuestionSubmitVO;
import com.yupi.onlineJudge.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionId 题目提交信息
     * @param loginUser
     * @return 提交记录的id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取题目封装
     *
     * @param question
     * @param request
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
