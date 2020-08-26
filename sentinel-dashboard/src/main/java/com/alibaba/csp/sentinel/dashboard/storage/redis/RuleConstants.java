package com.alibaba.csp.sentinel.dashboard.storage.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @project: xxxx
 * @description: Redis常量
 * @version 1.0.0
 * @errorcode
 *            错误码: 错误描述
 * @author
 *         <li>2020-07-17 guopengfei@bobfintech.com.cn Create 1.0
 * @copyright ©2019-2020 xxxx，版权所有。
 */
@Component
public class RuleConstants {

    /**
     * 流控规则key前缀
     */
    @Value("${rule.flow}")
    public String ruleFlow;

}