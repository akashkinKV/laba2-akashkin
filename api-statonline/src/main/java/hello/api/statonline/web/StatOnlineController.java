package hello.api.statonline.web;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.api.statonline.model.StatOnlineInfo;
import hello.api.statonline.service.StatOnlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api-statOnline")
public class StatOnlineController {
    private static final Logger logger = LoggerFactory.getLogger(StatOnlineController.class);
    @Autowired
    private StatOnlineService StatOnlineInfo;
    static final String ACCESS_TOKEN_VALIDATE_URI="https://api-my-gateway.herokuapp.com/api-gateway/oauth20/tokens/validate";
    static final String CLIENT_ID="9aa3ae11759ae257e2f29484a32820845ae59763";
    static final String CLIENT_SECRET="3997673d7814bbbcde139fe181e7fba723beb70a4e6e49363230ff78051f40d1";

    private boolean OauthCheckToken(String token)
    {

        if(token==null)
            return false;
        else {
            try {
                String token2 = token.replace("Bearer ", "");
                System.out.println(token2);
                logger.info("oauth"+ token2);
                logger.debug("oauth"+ token2);
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ACCESS_TOKEN_VALIDATE_URI)
                        .queryParam("token", token2);
                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(builder.toUriString(), String.class);
                logger.info("result"+ result);
                logger.debug("result"+ result);
                JsonFactory factory = new JsonFactory();

                ObjectMapper mapper = new ObjectMapper(factory);
                JsonNode rootNode = mapper.readTree(result);

                Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();

                while (fieldsIterator.hasNext()) {

                    Map.Entry<String, JsonNode> field = fieldsIterator.next();

                    System.out.println(field.getKey());
                    if (field.getKey() == "error") {
                        return false;
                    }

                }
                return  true;
            } catch (Exception e) {
                logger.error("oauth", e);
                return  false;
            }
        }
    }


    @GetMapping("/getAll{uuid}")
    public ResponseEntity<List<StatOnlineInfo>> findAll(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {
        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);

            List<StatOnlineInfo> stats = StatOnlineInfo.findAllStatsByUUID(uuid);

            if (stats.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            } else {
                return new ResponseEntity<List<StatOnlineInfo>>(stats, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("getAllError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/getDay{uuid}")
    public ResponseEntity<List<StatOnlineInfo>> findDay(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {
        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);
            List<StatOnlineInfo> stats = StatOnlineInfo.findDayStatsByUUID(uuid);

            if (stats.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            } else {
                return new ResponseEntity<List<StatOnlineInfo>>(stats, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("getDayError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getWeek{uuid}")
    public ResponseEntity<List<StatOnlineInfo>> findWeek(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {
        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);
            List<StatOnlineInfo> stats = StatOnlineInfo.findWeekStatsByUUID(uuid);

            if (stats.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            } else {
                return new ResponseEntity<List<StatOnlineInfo>>(stats, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("getWeekError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getMonth{uuid}")
    public ResponseEntity<List<StatOnlineInfo>> findMonth(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {
        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);
            List<StatOnlineInfo> stats = StatOnlineInfo.findMonthStatsByUUID(uuid);

            if (stats.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
                // You many decide to return HttpStatus.NOT_FOUND
            } else {
                return new ResponseEntity<List<StatOnlineInfo>>(stats, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("getMonthError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping("/create")
    public ResponseEntity createStat(@RequestHeader(value="Authorization",required = false) String token,@RequestBody Map<String, String> info) {
        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);
            StatOnlineInfo.createStatistic(UUID.fromString(info.get("uid")), info.get("vk"));

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("create", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get{vk}{uuid}")
    public ResponseEntity<StatOnlineInfo> getStat(@RequestHeader(value="Authorization",required = false) String token,@RequestParam String vk, @RequestParam UUID uuid) {
        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);

            return new ResponseEntity(StatOnlineInfo.refreshStatistic(uuid, vk), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete{uuid}")
    public ResponseEntity deleteStats(@RequestHeader(value="Authorization",required = false) String token,@RequestParam UUID uuid) {

        try {
            if(!OauthCheckToken(token))
                return   new ResponseEntity(HttpStatus.UNAUTHORIZED);

            StatOnlineInfo.deleteStats(uuid);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("deleteError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
