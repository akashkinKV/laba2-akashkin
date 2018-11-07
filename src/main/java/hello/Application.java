package hello;

import java.util.Arrays;
import java.util.List;

import hello.api.users.entity.User;
import hello.api.users.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import hello.api.gateway.GateWay;

@EnableScheduling
@SpringBootApplication
public class Application {
    @Autowired
    private GateWay gateWay;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public class ScheduledTasks {


//        @Scheduled(cron = "*/120 * * * * *")
//        public void reportCurrentTime() {
//            List<UserInfo> allUsers=gateWay.findAllUsers();
//
//
//            for (int i = 0; i < allUsers.size(); i++) {
//
//                if(!allUsers.get(i).getVk().equals("No"))
//                gateWay.createStatOneday(allUsers.get(i).getVk(),allUsers.get(i).getUid().toString());
//            }
//
//        }
    }
}
