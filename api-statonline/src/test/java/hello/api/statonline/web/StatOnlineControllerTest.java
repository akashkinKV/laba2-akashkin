package hello.api.statonline.web;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatOnlineControllerTest {

    @Autowired
    private MockMvc mvc;

    private  final Gson gson=new Gson();

    static final String URL_API_STATONLINE = "http://localhost:8083/api-statOnline/";


    static final String URL_API_STATONLINE_CREATE_STAT = URL_API_STATONLINE.concat("create");
    static final String URL_API_STATONLINE_GET_STAT = URL_API_STATONLINE.concat("get");
    static final String URL_API_STATONLINE_FIND_ALL_STATS = URL_API_STATONLINE.concat("getAll");
    static final String URL_API_STATONLINE_FIND_DAY_STATS = URL_API_STATONLINE.concat("getDay");
    static final String URL_API_STATONLINE_FIND_WEEK_STATS = URL_API_STATONLINE.concat("getWeek");
    static final String URL_API_STATONLINE_FIND_MONTH_STATS = URL_API_STATONLINE.concat("getMonth");
    static final String URL_API_STATONLINE_DELETE = URL_API_STATONLINE.concat("delete");

    final String uuid="1c7156bc-5cab-4458-9571-b102ca03fc50";
    final String vk="basta";

    @Before
    public void setUp() throws Exception {
        Map<String, String> info = new HashMap<String, String>();
        info.put("uid", uuid);
        info.put("vk", vk);

        mvc.perform(post(URL_API_STATONLINE_CREATE_STAT).contentType(MediaType.APPLICATION_JSON).
                content(gson.toJson(info))).andDo(print())
                .andExpect(status().isCreated());


    }

    @After
    public void tearDown() throws Exception {
        mvc.perform(delete(URL_API_STATONLINE_DELETE).
                param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStat() throws Exception {


        mvc.perform(get(URL_API_STATONLINE_GET_STAT).param("vk", vk).
                param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAll() throws Exception {

        mvc.perform(get(URL_API_STATONLINE_FIND_ALL_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void findDay() throws Exception {
        mvc.perform(get(URL_API_STATONLINE_FIND_DAY_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findWeek() throws Exception {
        mvc.perform(get(URL_API_STATONLINE_FIND_WEEK_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findMonth() throws Exception {
        mvc.perform(get(URL_API_STATONLINE_FIND_MONTH_STATS)
                .param("uuid", uuid)).andDo(print())
                .andExpect(status().isOk());
    }


}