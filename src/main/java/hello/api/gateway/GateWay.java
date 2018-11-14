package hello.api.gateway;

import hello.api.statistic.model.StatisticInfo;
import hello.api.users.model.UserInfo;
import hello.api.users.web.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
    private static final Logger logger = LoggerFactory.getLogger(GateWay.class);
    //----------------------------------USERS API----------------------------------------
    static final String URL_API_USERS = "http://localhost:8080/api-users/";

    static final String URL_API_USERS_LOGIN = URL_API_USERS.concat("login");
    static final String URL_API_USERS_REGISTR = URL_API_USERS.concat("create");
    static final String URL_API_USERS_UPDATE = URL_API_USERS.concat("update");
    static final String URL_API_USERS_GET = URL_API_USERS.concat("get");
    static final String URL_API_USERS_DELETE = URL_API_USERS.concat("delete");
    static final String URL_API_USERS_ALL = URL_API_USERS.concat("getAll");

    //----------------------------------STATONLINE  API----------------------------------------
    static final String URL_API_STATISTIC = "http://localhost:8080/api-statistic/";

    static final String URL_API_STATISTIC_CREATE_STAT = URL_API_STATISTIC.concat("create");
    static final String URL_API_STATISTIC_GET_STAT = URL_API_STATISTIC.concat("get");
    static final String URL_API_STATISTIC_FIND_ALL_STATS = URL_API_STATISTIC.concat("getAll");
    static final String URL_API_STATISTIC_FIND_WEEK_STATS = URL_API_STATISTIC.concat("getWeek");
    static final String URL_API_STATISTIC_FIND_MONTH_STATS = URL_API_STATISTIC.concat("getMonth");
    static final String URL_API_STATISTIC_DELETE = URL_API_STATISTIC.concat("delete");

    //----------------------------------STATONLINE  API----------------------------------------
    static final String URL_API_STATONLINE = "http://localhost:8080/api-statOnline/";

    static final String URL_API_STATONLINE_CREATE_STAT = URL_API_STATONLINE.concat("create");
    static final String URL_API_STATONLINE_GET_STAT = URL_API_STATONLINE.concat("get");
    static final String URL_API_STATONLINE_FIND_ALL_STATS = URL_API_STATONLINE.concat("getAll");
    static final String URL_API_STATONLINE_FIND_DAY_STATS = URL_API_STATONLINE.concat("getDay");
    static final String URL_API_STATONLINE_FIND_WEEK_STATS = URL_API_STATONLINE.concat("getWeek");
    static final String URL_API_STATONLINE_FIND_MONTH_STATS = URL_API_STATONLINE.concat("getMonth");
    static final String URL_API_STATONLINE_DELETE = URL_API_STATONLINE.concat("delete");


    //USER API
    @PostMapping("/user.create")
    public ResponseEntity<String> registrationUser(@RequestBody UserInfo requestUserDetails) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
System.out.println(requestUserDetails.toString());
            HttpEntity <String> httpEntity = new HttpEntity<String>(requestUserDetails.toString(), httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.postForObject(URL_API_USERS_REGISTR, httpEntity, String.class);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("user.createError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user.get{uuid}")
    public ResponseEntity<String> updateVK(@RequestParam UUID uuid) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USERS_GET)
                    .queryParam("uuid", uuid);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(), String.class);

            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("user.getError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

////Statistic API
//
//    @GetMapping("/refreshStat{uuid}")
//    public ResponseEntity<StatisticInfo> refreshStat(@RequestParam UUID uuid) {
//        try {
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USER_BY_UUID)
//                    .queryParam("uuid", uuid);
//
//            RestTemplate restTemplate = new RestTemplate();
//            UserInfo user = restTemplate.getForObject(builder.toUriString(), UserInfo.class);
//
//            UriComponentsBuilder builder2 = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_REFRESH_STAT)
//                    .queryParam("vk", user.getVk()).queryParam("uuid", uuid);
//
//            RestTemplate restTemplate2 = new RestTemplate();
//            StatisticInfo stat = restTemplate2.getForObject(builder2.toUriString(), StatisticInfo.class);
//
//
//            return new ResponseEntity(stat, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @GetMapping("/createStat{vk}{uuid}")
//    public ResponseEntity<String> createStat(@RequestParam String vk, @RequestParam String uuid) {
//        try {
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_CREATE_STAT)
//                    .queryParam("vk", vk).queryParam("uuid", uuid);
//
//            RestTemplate restTemplate = new RestTemplate();
//            String result = restTemplate.getForObject(builder.toUriString(), String.class);
//
//            return new ResponseEntity(result, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @GetMapping("/getAllStatsList{uuid}")
//    public ResponseEntity<List<StatisticInfo>> findAllStatsList(@RequestParam String uuid) {
//        try {
//            List<StatisticInfo> allStats = new ArrayList<>();
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_FIND_ALL_STATS)
//                    .queryParam("uuid", uuid);
//
//            RestTemplate restTemplate = new RestTemplate();
//            StatisticInfo[] allStatsInfo = restTemplate.getForObject(builder.toUriString(), StatisticInfo[].class);
//
//            return new ResponseEntity(allStatsInfo, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @GetMapping("/getWeekStatsList{uuid}")
//    public ResponseEntity<List<StatisticInfo>> findWeekStatsList(@RequestParam String uuid) {
//        try {
//            List<StatisticInfo> allStats = new ArrayList<>();
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_FIND_WEEK_STATS)
//                    .queryParam("uuid", uuid);
//
//            RestTemplate restTemplate = new RestTemplate();
//            StatisticInfo[] allStatsInfo = restTemplate.getForObject(builder.toUriString(), StatisticInfo[].class);
//
//            return new ResponseEntity(allStatsInfo, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }
//
//    @GetMapping("/getMonthStatsList{uuid}")
//    public ResponseEntity<List<StatisticInfo>> findMonthStatsList(@RequestParam String uuid) {
//        try {
//            List<StatisticInfo> allStats = new ArrayList<>();
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_FIND_MONTH_STATS)
//                    .queryParam("uuid", uuid);
//
//            RestTemplate restTemplate = new RestTemplate();
//            StatisticInfo[] allStatsInfo = restTemplate.getForObject(builder.toUriString(), StatisticInfo[].class);
//
//            return new ResponseEntity(allStatsInfo, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }

    public void createStatOneday(String vk, String uuid) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_STATISTIC_CREATE_STAT)
                    .queryParam("vk", vk).queryParam("uuid", uuid);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(), String.class);


        } catch (Exception e) {

        }
    }

    public List<UserInfo> findAllUsers() {
        List<UserInfo> allUsers = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        UserInfo[] allUsersGet = restTemplate.getForObject(URL_API_USERS_ALL, UserInfo[].class);
        allUsers = Arrays.asList(allUsersGet);

        return allUsers;

    }

}
