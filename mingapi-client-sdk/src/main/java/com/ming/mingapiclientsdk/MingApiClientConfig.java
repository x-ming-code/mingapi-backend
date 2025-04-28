package com.ming.mingapiclientsdk;

import com.ming.mingapiclientsdk.client.MingapiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ming
 * @description 调用第三方接口的客户端
 * @date 2025/4/17 14:31
 */
@Configuration
@ConfigurationProperties(prefix = "ming.api")
@Data
@ComponentScan
public class MingApiClientConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public MingapiClient mingapiClient() {
        return new MingapiClient(accessKey, secretKey);
    }
}
