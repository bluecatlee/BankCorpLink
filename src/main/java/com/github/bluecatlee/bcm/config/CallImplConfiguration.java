package com.github.bluecatlee.bcm.config;

import com.github.bluecatlee.bcm.invoke.Call;
import com.github.bluecatlee.bcm.invoke.HttpCall;
import com.github.bluecatlee.bcm.invoke.SocketCall;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调用实现方式配置类
 */
@Configuration
public class CallImplConfiguration {

    @Value("${bcm.http.host:#{null}}")
    private String httpHost;
    @Value("${bcm.http.port:#{null}}")
    private Integer httpPort;
    @Value("${bcm.socket.enabled:#{null}}")
    private Boolean socketEnabled;
    @Value("${bcm.socket.host:#{null}}")
    private String socketHost;
    @Value("${bcm.socket.port:#{null}}")
    private Integer socketPort;
    @Value("${bcm.socket.timeout:#{null}}")
    private Integer socketTimeout;

    private static final String DEFAULT_HTTP_HOST = "127.0.0.1";
    private static final Integer DEFAULT_HTTP_PORT = 8899;
    private static final String DEFAULT_SOCKET_HOST = "127.0.0.1";
    private static final Integer DEFAULT_SOCKET_PORT = 30010;
    private static final Integer DEFAULT_SOCKET_TIMEOUT = 5000; //5s

    @Bean
    @ConditionalOnProperty(name = "bcm.socket.enabled", havingValue = "true")
    public Call socketCall() {
        if (StringUtils.isBlank(socketHost)) {
            socketHost = DEFAULT_SOCKET_HOST;
        }
        if (socketPort == null) {
            socketPort = DEFAULT_SOCKET_PORT;
        }
        if (socketTimeout == null) {
            socketTimeout = DEFAULT_SOCKET_TIMEOUT;
        }
        return new SocketCall(socketHost, socketPort, socketTimeout);
    }

    @Bean
    @ConditionalOnMissingBean(Call.class)
    public Call httpCall() {
        if (StringUtils.isBlank(httpHost)) {
            httpHost = DEFAULT_HTTP_HOST;
        }
        if (httpPort == null) {
            httpPort = DEFAULT_HTTP_PORT;
        }
        return new HttpCall(httpHost, httpPort);
    }

}
