package hello.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hello.Application;

import hello.scheduler.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



    @Component
    public class ScheduledTasks {

        private static final Logger logger = LoggerFactory.getLogger(Application.class);


        static final String URL_API_GATEWAY = "http://localhost:8080/api-gateway/";
        static final String URL_API_GATEWAY_USER_ALL = URL_API_GATEWAY.concat("user.getAll");
        static final String URL_API_GATEWAY_STAT_CREATE = URL_API_GATEWAY.concat("statistic.create");
        static final String URL_API_GATEWAY_STATONLINE_CREATE = URL_API_GATEWAY.concat("statOnline.create");

        @Scheduled(cron = "*/30 * * * * *")
        public void createStatsOnline() {
            try{
                List<UserInfo> allUsers=findAllUsers();


                for (int i = 0; i < allUsers.size(); i++) {
                    System.out.println(allUsers.get(i).getUid().toString());

                  //  createStatOnlineOneMinute(allUsers.get(i));
//                if(!allUsers.get(i).getVk().equals("No"))
//                gateWay.createStatOneday(allUsers.get(i).getVk(),allUsers.get(i).getUid().toString());
                }

            }
            catch (Exception e) {
               // logger.error("getError", e);
            }
        }

        @Scheduled(cron = "*/60 * * * * *")
        public void createStatistic() {
            try{
                List<UserInfo> allUsers=findAllUsers();


                for (int i = 0; i < allUsers.size(); i++) {
                    System.out.println(allUsers.get(i).getUid().toString());
                    createStatOneday(allUsers.get(i));
//
//                if(!allUsers.get(i).getVk().equals("No"))
//                gateWay.createStatOneday(allUsers.get(i).getVk(),allUsers.get(i).getUid().toString());
                }

            }
            catch (Exception e) {
          //      logger.error("getError", e);
            }
        }


    public List<UserInfo> findAllUsers() {

     try {
         List<UserInfo> allUsers = new ArrayList<>();

         RestTemplate restTemplate = new RestTemplate();
         UserInfo[] allUsersGet = restTemplate.getForObject(URL_API_GATEWAY_USER_ALL, UserInfo[].class);
         allUsers = Arrays.asList(allUsersGet);

         return allUsers;
     }
     catch (Exception e) {
         // logger.error("getError", e);
         return null;
     }

    }
    public void createStatOneday(UserInfo user) {
        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = URL_API_GATEWAY_STAT_CREATE;
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(user);
            System.out.println("jsoon"+json);
            String requestJson = json;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            restTemplate.postForObject(url, entity, String.class);


        } catch (Exception e) {
          //  logger.error("getError", e);
        }
    }
    public void createStatOnlineOneMinute(UserInfo user) {
        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = URL_API_GATEWAY_STATONLINE_CREATE;
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(user);
            String requestJson = json;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            restTemplate.postForObject(url, entity, String.class);

        } catch (Exception e) {
           // logger.error("getError", e);
        }
    }

}
