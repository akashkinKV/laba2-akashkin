package hello.api.gateway;

import hello.api.statistic.model.StatisticInfo;
import hello.api.users.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import hello.api.users.service.UserService;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api-gateway")
public class GateWay {




    static final String URL_API_USERS_LOGIN = "http://localhost:8080/api-users/login";
    static final String URL_API_USERS_REGISTR = "http://localhost:8080/api-users/registration";
    static final String URL_API_USERS_UPDATEVK = "http://localhost:8080/api-users/updateVK";
    static final String URL_API_USERS_ALL = "http://localhost:8080/api-users/getAllUsers";
    static final String URL_API_USER_BY_UUID = "http://localhost:8080/api-users/findByUID";
   
    static final String URL_API_STATISTIC_CREATE_STAT = "http://localhost:8080/api-statistic/create";
    static final String URL_API_STATISTIC_REFRESH_STAT = "http://localhost:8080/api-statistic/refresh";
    static final String URL_API_STATISTIC_FIND_ALL_STATS = "http://localhost:8080/api-statistic//getAllStatsByUID";
    static final String URL_API_STATISTIC_FIND_WEEK_STATS = "http://localhost:8080/api-statistic//getWeekStatsByUID";
    static final String URL_API_STATISTIC_FIND_MONTH_STATS = "http://localhost:8080/api-statistic//getMonthStatsByUID";



    @Autowired
    private UserService userService;


    @GetMapping("/registration{email}{password}")
    public ResponseEntity<String> registrationUser(@RequestParam String email,@RequestParam String password) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USERS_REGISTR)
                .queryParam("email", email).queryParam("password",password);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(),String.class);

            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/updateVK{vk}{uuid}")
    public ResponseEntity<String> updateVK (@RequestParam String vk,@RequestParam UUID uuid) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USERS_UPDATEVK)
                    .queryParam("vk", vk).queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(),String.class);

            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/refreshStat{uuid}")
    public ResponseEntity<StatisticInfo> refreshStat (@RequestParam UUID uuid) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USER_BY_UUID)
                   .queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            UserInfo user = restTemplate.getForObject(builder.toUriString(),UserInfo.class);

            UriComponentsBuilder builder2 = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_REFRESH_STAT)
                    .queryParam("vk",user.getVk()).queryParam("uuid",uuid);

            RestTemplate restTemplate2 = new RestTemplate();
            StatisticInfo stat = restTemplate2.getForObject(builder2.toUriString(),StatisticInfo.class);
            

            return new ResponseEntity(stat,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/createStat{vk}{uuid}")
    public ResponseEntity<String> createStat (@RequestParam String vk,@RequestParam String uuid) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_CREATE_STAT)
                    .queryParam("vk", vk).queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(),String.class);

            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAllStatsList{uuid}")
    public ResponseEntity<List<StatisticInfo>> findAllStatsList ( @RequestParam String uuid) {
        try {
            List<StatisticInfo> allStats = new ArrayList<>();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_FIND_ALL_STATS)
                    .queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            StatisticInfo[] allStatsInfo = restTemplate.getForObject(builder.toUriString(),StatisticInfo[].class);

            return new ResponseEntity(allStatsInfo,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/getWeekStatsList{uuid}")
    public ResponseEntity<List<StatisticInfo>> findWeekStatsList (@RequestParam String uuid) {
        try {
            List<StatisticInfo> allStats = new ArrayList<>();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_FIND_WEEK_STATS)
                    .queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            StatisticInfo[] allStatsInfo = restTemplate.getForObject(builder.toUriString(),StatisticInfo[].class);

            return new ResponseEntity(allStatsInfo,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/getMonthStatsList{uuid}")
    public ResponseEntity<List<StatisticInfo>> findMonthStatsList (@RequestParam String uuid) {
        try {
            List<StatisticInfo> allStats = new ArrayList<>();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_FIND_MONTH_STATS)
              .queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            StatisticInfo[] allStatsInfo = restTemplate.getForObject(builder.toUriString(),StatisticInfo[].class);

            return new ResponseEntity(allStatsInfo,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    public void createStatOneday ( String vk, String uuid) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_CREATE_STAT)
                    .queryParam("vk", vk).queryParam("uuid",uuid);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(),String.class);


        }catch (Exception e)
        {

        }
    }
    public List<UserInfo> findAllUsers() {
        List<UserInfo> allUsers = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        UserInfo[] allUsersGet = restTemplate.getForObject(URL_API_USERS_ALL, UserInfo[].class);
        allUsers= Arrays.asList(allUsersGet);

        return allUsers;

    }

}
