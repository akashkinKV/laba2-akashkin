package hello.api.statonline.web;


import hello.api.statonline.model.StatOnlineInfo;
import hello.api.statonline.service.StatOnlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api-statOnline")
public class StatOnlineController {
    private static final Logger logger = LoggerFactory.getLogger(StatOnlineController.class);
    @Autowired
    private StatOnlineService StatOnlineInfo;


    @GetMapping("/getAll{uuid}")
    public ResponseEntity<List<StatOnlineInfo>> findAll(@RequestParam UUID uuid) {
        try {
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
    public ResponseEntity<List<StatOnlineInfo>> findDay(@RequestParam UUID uuid) {
        try {
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
    public ResponseEntity<List<StatOnlineInfo>> findWeek(@RequestParam UUID uuid) {
        try {
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
    public ResponseEntity<List<StatOnlineInfo>> findMonth(@RequestParam UUID uuid) {
        try {
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
    public ResponseEntity createStat(@RequestBody Map<String, String> info) {
        try {
            StatOnlineInfo.createStatistic(UUID.fromString(info.get("uid")), info.get("vk"));

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("create", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get{vk}{uuid}")
    public ResponseEntity<StatOnlineInfo> getStat(@RequestParam String vk, @RequestParam UUID uuid) {
        try {

            return new ResponseEntity(StatOnlineInfo.refreshStatistic(uuid, vk), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete{uuid}")
    public ResponseEntity deleteStats(@RequestParam UUID uuid) {

        try {
            StatOnlineInfo.deleteStats(uuid);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("deleteError", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
