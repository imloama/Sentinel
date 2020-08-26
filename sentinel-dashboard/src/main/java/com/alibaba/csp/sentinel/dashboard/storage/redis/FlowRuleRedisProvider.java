package com.alibaba.csp.sentinel.dashboard.storage.redis;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.fastjson.JSONObject;

/**
 * @project: xxx
 * @description: 自定义实现基于redis的拉取规则
 * @version 1.0.0
 * @errorcode
 *            错误码: 错误描述
 * @author
 *         <li>2020-07-17 guopengfei@bobfintech.com.cn Create 1.0
 * @copyright ©2019-2020 xxxx，版权所有。
 */
@Component("flowRuleRedisProvider")
public class FlowRuleRedisProvider implements DynamicRuleProvider<List<GatewayFlowRuleEntity>> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RuleConstants ruleConstants;

    @Override
    public List<GatewayFlowRuleEntity> getRules(String appName) throws Exception {
        System.out.println("Sentinel 从Redis拉取规则 begin >>>>>>>>>>>>>>>>>>>>");
        String value = (String) redisTemplate.opsForValue().get(ruleConstants.ruleFlow + appName);
        if (StringUtils.isEmpty(value)){
            return new ArrayList<>();
        }
        System.out.println("Sentinel 从Redis拉取规则 end >>>>>>>>>>>>>>>>>>>>");
        return JSONObject.parseArray(value,GatewayFlowRuleEntity.class);
    }
}