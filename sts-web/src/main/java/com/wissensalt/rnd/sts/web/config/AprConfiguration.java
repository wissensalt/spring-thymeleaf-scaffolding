package com.wissensalt.rnd.sts.web.config;

import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AprConfiguration {

//    @Value("${server.tomcat.apr.enabled:false}")
    private boolean enabled;

//    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory container = new TomcatServletWebServerFactory ();
        if (enabled) {
            LifecycleListener arpLifecycle = new AprLifecycleListener();
            container.setProtocol("org.apache.coyote.http11.Http11AprProtocol");
            container.addContextLifecycleListeners(arpLifecycle);
        }

        return container;
    }
}
