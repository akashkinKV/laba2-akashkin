package hello.api.statistic.web;

import hello.api.statistic.model.StatisticInfo;
import hello.api.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api-statistic")
public class StatisticController {

    @Autowired
    private StatisticService statRepos;


    @GetMapping("/getAll{uuid}")
    public ResponseEntity<List<StatisticInfo>> findAllStatsByUUID(@RequestParam String uuid) {

        List<StatisticInfo> stats = statRepos.findAllStatsByUUID(uuid);

        if (stats.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        } else {
            return new ResponseEntity<List<StatisticInfo>>(stats, HttpStatus.OK);
        }

    }

    @GetMapping("/getWeek{uuid}")
    public ResponseEntity<List<StatisticInfo>> findWeekStatsByUUID(@RequestParam String uuid) {

        List<StatisticInfo> stats = statRepos.findWeekStatsByUUID(uuid);

        if (stats.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        } else {
            return new ResponseEntity<List<StatisticInfo>>(stats, HttpStatus.OK);
        }

    }

    @GetMapping("/getMonth{uuid}")
    public ResponseEntity<List<StatisticInfo>> findMonthStatsByUUID(@RequestParam String uuid) {

        List<StatisticInfo> stats = statRepos.findMonthStatsByUUID(uuid);

        if (stats.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        } else {
            return new ResponseEntity<List<StatisticInfo>>(stats, HttpStatus.OK);
        }

    }

    @GetMapping("/create{vk}{uuid}")
    public ResponseEntity<String> createStat(@RequestParam String vk, @RequestParam String uuid) {
        try {
            statRepos.createStatistic(uuid, vk);
            return new ResponseEntity("Succsec", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/refresh{vk}{uuid}")
    public ResponseEntity<StatisticInfo> refreshStat(@RequestParam String vk, @RequestParam String uuid) {
        try {

            return new ResponseEntity(statRepos.refreshStatistic(uuid, vk), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }




}
