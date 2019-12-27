package com.javen.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.support.logging.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        Properties properties = new Properties();
        properties.setProperty("config.decrypt", "true");


        properties.setProperty("config.decrypt.key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOWJJnpeXrWC3sn4rSnQf++qzMwd0Tm7FqqM677Img+/enPLNm7oCCYV8ASy0uTksuNhey/V1YT2TFeQl3UghMMCAwEAAQ==");
        dataSource.setConnectProperties(properties);
        return dataSource;
    }
}