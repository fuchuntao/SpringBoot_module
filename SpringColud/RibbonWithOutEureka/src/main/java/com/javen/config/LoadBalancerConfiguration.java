package com.javen.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {
    /**
     * 默认有 7 种自带负载均衡策略 默认是轮询
     *
     * RandomRule 随机
     * RoundRobinRule 轮询
     * RetryRule 重试策略
     * BestAvailableRule 最低并发策略
     * AvailabilityFilteringRule 可用过滤策略
     * ZoneAvoidanceRule 区域权衡策略
     * ResponseTimeWeightedRule 响应时间加权策略 过期
     *
     * 这里设置的是全局规则 <br/>
     * 如果启动类中使用了 RibbonClient 配置就另当别论 <br/>
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
