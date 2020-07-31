package com.example.demo.configuration;

import com.sun.javafx.binding.StringFormatter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private static Logger log = LoggerFactory.getLogger(ServerConfig.class);
    private int serverPort;


    //@SneakyThrows
    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //log.info("" + this.serverPort);
        return String.format("http://%s:%s", address.getHostAddress(), this.serverPort);
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();

    }
}
