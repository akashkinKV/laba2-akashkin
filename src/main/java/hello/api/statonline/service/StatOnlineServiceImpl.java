package hello.api.statonline.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.api.statonline.entity.StatOnline;
import hello.api.statonline.model.StatOnlineInfo;
import hello.api.statonline.repository.StatOnlineRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatOnlineServiceImpl implements StatOnlineService {
    @Autowired
    private StatOnlineRepos statOnlineRepos;

    static final String URL_API_VK = "https://api.vk.com/method/users.get";
    static final String VK_TOKEN = "5c5e70cfcf443268b00e8914fc9b752980d7c428691f9458d510eaa8f9ec1b7d16695aa764b516fc27a4f";


    @Nullable
    @Override
    public void createStatistic(@Nonnull UUID uuid, @Nonnull String vk) {
        statOnlineRepos.saveAndFlush(createStat(uuid, vk));

    }

    @Override
    public List<StatOnlineInfo> findAllStatsByUUID(@Nonnull UUID uuid) {
        return statOnlineRepos.findAllByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatOnlineInfo> findDayStatsByUUID(@Nonnull UUID uuid) {
        return statOnlineRepos.findFirst720ByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatOnlineInfo> findWeekStatsByUUID(@Nonnull UUID uuid) {
        return statOnlineRepos.findFirst5040ByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatOnlineInfo> findMonthStatsByUUID(@Nonnull UUID uuid) {
        return statOnlineRepos.findFirst21600ByUid(uuid)
                .stream()
                .map(this::createStatInfo)
                .collect(Collectors.toList());
    }

    @Override
    public StatOnlineInfo refreshStatistic(@Nonnull UUID uuid, @Nonnull String vk) {
        return createStatInfo(createStat(uuid, vk));
    }

    @Nullable
    @Override
    public void deleteStats(@Nonnull UUID uuid) {
        statOnlineRepos.deleteByUid(uuid);
    }

    @Nonnull
    private StatOnline createStat(@Nonnull UUID uuid, @Nonnull String vk) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_VK)
                    .queryParam("user_ids", vk).queryParam("fields", "online")
                    .queryParam("access_token", VK_TOKEN);
            RestTemplate restTemplate = new RestTemplate();

            StatOnline stat = new StatOnline();
            stat.setDate(Calendar.getInstance().getTime());
            stat.setUid(uuid);
            // Send request with GET method and default Headers.
            String jsonString = restTemplate.getForObject(builder.toUriString(), String.class);

            JsonFactory factory = new JsonFactory();

            ObjectMapper mapper = new ObjectMapper(factory);
            JsonNode rootNode = mapper.readTree(removeLastChar(parse(jsonString)));

            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();

            while (fieldsIterator.hasNext()) {

                Map.Entry<String, JsonNode> field = fieldsIterator.next();


                if (field.getKey() == "online") {
                    stat.setOnline(field.getValue().asBoolean());
                }
                if (field.getKey() == "online_mobile") {
                    stat.setMobile(true);
                } else {
                    stat.setMobile(false);
                }
            }


            return stat;
        } catch (Exception e) {

            return null;
        }

    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1).substring(1);
    }

    public String parse(String json) throws IOException {

        JsonFactory factory = new JsonFactory();

        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        String ret = null;
        while (fieldsIterator.hasNext()) {

            Map.Entry<String, JsonNode> field = fieldsIterator.next();


            ret = field.getValue().toString();
        }

        return ret;
    }

    @Nonnull
    private StatOnlineInfo createStatInfo(@Nonnull StatOnline stat) {

        StatOnlineInfo statInfo = new StatOnlineInfo();

        statInfo.setDate(stat.getDate());
        statInfo.setOnline(stat.isOnline());
        statInfo.setMobile(stat.isMobile());
        statInfo.setUid(stat.getUid());
        return statInfo;


    }
}
