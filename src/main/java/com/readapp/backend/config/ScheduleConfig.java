package com.readapp.backend.config;

import com.readapp.backend.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
        import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
//public class ScheduleConfig {
//
//    //3.添加定时任务
//    @Scheduled(cron = "0 0 16 1/1 * ? ")
//    //@Scheduled(fixedRate=5000)
//    private void configureTasks() {
//        SMSUtils.sendMilteaMessage();
//    }
//}