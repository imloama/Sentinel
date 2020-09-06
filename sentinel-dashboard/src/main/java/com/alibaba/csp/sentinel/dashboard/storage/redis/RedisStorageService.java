package com.alibaba.csp.sentinel.dashboard.storage.redis;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisStorageService {

    public static final String PREFIX = "sentinel:gateway:";
    //api规则
    public static final String KEY_API = "api";
    //流控规则
    public static final String KEY_FLOW = "flow";
    //降级规则
    public static final String KEY_DEGRADE = "degrade";
    //系统规则
    public static final String KEY_SYSTEM = "system";

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(RedisStorageService.class);

    public void putAPI(String app,List<ApiDefinitionEntity> list){
        String key = PREFIX + app + ":" + KEY_API;
        if(list == null){
            list = new ArrayList<>();
        }
        String value = JSON.toJSONString(list);
        logger.debug("save api: {}", value);
        this.redisTemplate.opsForValue().set(key, value);
    }

    public List<ApiDefinitionEntity> getAPI(String app){
        String key = PREFIX + app + ":" + KEY_API;
        String value = this.redisTemplate.opsForValue().get(key);
        if(StringUtil.isBlank(value)) {
            return new ArrayList<>();
        }
        logger.debug(" get api, key:{}, value:{}", key, value);
        return JSONObject.parseArray(value, ApiDefinitionEntity.class);
    }

    public void putFlow(String app,List<GatewayFlowRuleEntity> list){
        String key = PREFIX + app + ":" + KEY_FLOW;
        if(list == null){
            list = new ArrayList<>();
        }
        String value = JSON.toJSONString(list);
        logger.debug("save flow: {}", value);
        this.redisTemplate.opsForValue().set(key, value);
    }

    public List<GatewayFlowRuleEntity> getFlow(String app){
        String key = PREFIX + app + ":" + KEY_FLOW;
        String value = this.redisTemplate.opsForValue().get(key);
        if(StringUtil.isBlank(value)) {
            return new ArrayList<>();
        }
        logger.debug(" get flow, key:{}, value:{}", key, value);
        return JSONObject.parseArray(value, GatewayFlowRuleEntity.class);
    }

    public void putDegrade(String app,List<DegradeRuleEntity> list){
        String key = PREFIX + app + ":" + KEY_DEGRADE;
        if(list == null){
            list = new ArrayList<>();
        }
        String value = JSON.toJSONString(list);
        logger.debug("save degrade: {}", value);
        this.redisTemplate.opsForValue().set(key, value);
    }

    public List<DegradeRuleEntity> getDegrade(String app){
        String key = PREFIX + app + ":" + KEY_DEGRADE;
        String value = this.redisTemplate.opsForValue().get(key);
        if(StringUtil.isBlank(value)) {
            return new ArrayList<>();
        }
        logger.debug(" get degrade, key:{}, value:{}", key, value);
        return JSONObject.parseArray(value, DegradeRuleEntity.class);
    }


    public void putSystem(String app,List<SystemRuleEntity> list){
        String key = PREFIX + app + ":" + KEY_SYSTEM;
        if(list == null){
            list = new ArrayList<>();
        }
        String value = JSON.toJSONString(list);
        logger.debug("save system: {}", value);
        this.redisTemplate.opsForValue().set(key, value);
    }

    public List<SystemRuleEntity> getSystem(String app){
        String key = PREFIX + app + ":" + KEY_SYSTEM;
        String value = this.redisTemplate.opsForValue().get(key);
        if(StringUtil.isBlank(value)) {
            return new ArrayList<>();
        }
        logger.debug(" get system, key:{}, value:{}", key, value);
        return JSONObject.parseArray(value, SystemRuleEntity.class);
    }




}
