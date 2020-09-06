import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class RedisError {

    public static void main(String[] args) {
        String json = "[{\"app\":\"api.gateway.kr\",\"id\":12,\"ip\":\"192.168.0.167\",\"port\":3001},{\"app\":\"api.gateway.kr\",\"id\":13,\"ip\":\"192.168.0.167\",\"port\":3001},{\"app\":\"api.gateway.kr\",\"id\":14,\"ip\":\"192.168.0.167\",\"port\":3001},{\"app\":\"api.gateway.kr\",\"id\":15,\"ip\":\"192.168.0.167\",\"port\":3001},{\"app\":\"api.gateway.kr\",\"id\":16,\"ip\":\"192.168.0.167\",\"port\":3001},{\"app\":\"api.gateway.kr\",\"avgRt\":-1,\"gmtCreate\":1599361661966,\"gmtModified\":1599361661966,\"highestCpuUsage\":-1.0,\"highestSystemLoad\":-1.0,\"id\":17,\"ip\":\"192.168.0.167\",\"maxThread\":-1,\"port\":3001,\"qps\":10000.0}]";
        List<DegradeRuleEntity> entities = JSONObject.parseArray(json, DegradeRuleEntity.class);
        System.out.println("==================1==================");
        System.out.println(JSON.toJSONString(json));
        String data = JSON.toJSONString(
                entities.stream().map(r -> r.toRule()).collect(Collectors.toList()));
        System.out.println("==================12==================");
        System.out.println(data);
    }

}
