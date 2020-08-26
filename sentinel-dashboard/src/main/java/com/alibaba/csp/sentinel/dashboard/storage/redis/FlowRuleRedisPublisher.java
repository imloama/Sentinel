package com.alibaba.csp.sentinel.dashboard.storage.redis;


import java.util.List;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;

/**
 * @project: xxxx
 * @description: 自定义实现限流配置推送规则
 * @version 1.0.0
 * @errorcode
 *            错误码: 错误描述
 * @author
 *         <li>2020-07-17 guopengfei@bobfintech.com.cn Create 1.0
 * @copyright ©2019-2020 xxxx，版权所有。
 */
@Component("flowRuleRedisPublisher")
public class FlowRuleRedisPublisher implements DynamicRulePublisher<List<GatewayFlowRuleEntity>> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RuleConstants ruleConstants;

    @Override
    public void publish(String app, List<GatewayFlowRuleEntity> rules) throws Exception {
        System.out.println("Sentinel 向Redis推送规则 begin >>>>>>>>>>>>>>>>>>>>");
        if (rules == null){
            return;
        }
        redisTemplate.multi();
        redisTemplate.opsForValue().set(ruleConstants.ruleFlow+app, JSONObject.toJSONString(rules));
        redisTemplate.convertAndSend(app,rules);
        redisTemplate.exec();
        System.out.println("Sentinel 向Redis推送规则 end >>>>>>>>>>>>>>>>>>>>");
    }
}