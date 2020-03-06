package com.example.messagingstompwebsocket;

import com.google.gson.Gson;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
@EnableScheduling
public class AutoPublish {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    Gson gson = new Gson();
    @Scheduled(cron = "0/5 * * * * ?")
    public void autoGreeting() {
        System.out.println("当前时间：" + new Date());
        simpMessagingTemplate.convertAndSend("/topic/greetings",
                gson.toJson(new Greeting("hello everyone,current time is :" + new Date())));
    }
}
