package com.javen;

import com.javen.config.AvoidScan;
import com.javen.config.LoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
// 也可以不指定 RibbonClient，不指定的情况下 LoadBalancerConfiguration 就是全局配置
// 也可以使用配置文件来配置负载均衡策略 见 application.yml
@RibbonClient(name = "email-provider", configuration = LoadBalancerConfiguration.class)
//@RibbonClients(value = {
//		@RibbonClient(name = "email-provider-1", configuration = LoadBalancerConfiguration.class),
//		@RibbonClient(name = "email-provider-2", configuration = LoadBalancerConfiguration.class)
//})
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {AvoidScan.class})})
public class RibbonWithOutEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonWithOutEurekaApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplateTemplate() {
        return new RestTemplate();
    }
}
