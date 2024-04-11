package com.yupi.onlineJudge.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.onlineJudge.common.BaseResponse;
import com.yupi.onlineJudge.common.ErrorCode;
import com.yupi.onlineJudge.common.ResultUtils;
import com.yupi.onlineJudge.exception.BusinessException;
import com.yupi.onlineJudge.exception.ThrowUtils;
import com.yupi.onlineJudge.model.dto.question.QuestionQueryRequest;
import com.yupi.onlineJudge.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.onlineJudge.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.onlineJudge.model.entity.Question;
import com.yupi.onlineJudge.model.entity.QuestionSubmit;
import com.yupi.onlineJudge.model.entity.User;
import com.yupi.onlineJudge.model.vo.QuestionSubmitVO;
import com.yupi.onlineJudge.model.vo.QuestionVO;
import com.yupi.onlineJudge.service.QuestionSubmitService;
import com.yupi.onlineJudge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_thumb")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionId = questionSubmitAddRequest.getQuestionId();
        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案，提交代码等公开信息）
     *
     * @param
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }
}
