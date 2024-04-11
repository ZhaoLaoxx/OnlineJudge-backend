package com.yupi.onlineJudge.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息消息枚举
 */
public enum JudgeInfoMessageEnum {
    AC("成功", "AC"),
    WA("答案错误", "WA"),
    CE("编译错误", "CE"),
    MLE("超出内存限制", "MLE"),
    TLE("超出时间限制", "TLE"),
    PE("输出格式错误", "PE"),
    WAITING("等待中", "WAITING"),
    OLE("输出超出限制", "OLE"),
    DO("危险行为", "DO"),
    RE("运行错误","RE"),
    SE("系统错误", "SE");

    private String text;

    private String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static UserRoleEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.getValue().equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
