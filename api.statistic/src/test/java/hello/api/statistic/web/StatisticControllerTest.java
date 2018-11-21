package hello.api.statistic.web;

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
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTest {

    @Autowired
    private MockMvc mvc;

    static final String URL_API_STATISTIC = "http://localhost:8080/api-statistic/";


    static final String URL_API_STATISTIC_CREATE_STAT = URL_API_STATISTIC.concat("create");
    static final String URL_API_STATISTIC_GET_STAT = URL_API_STATISTIC.concat("get");
    static final String URL_API_STATISTIC_FIND_ALL_STATS = URL_API_STATISTIC.concat("getAll");
    static final String URL_API_STATISTIC_FIND_WEEK_STATS = URL_API_STATISTIC.concat("getWeek");
    static final String URL_API_STATISTIC_FIND_MONTH_STATS = URL_API_STATISTIC.concat("getMonth");
    static final String URL_API_STATISTIC_DELETE = URL_API_STATISTIC.concat("delete");

    @Before
    public void setUp() throws Exception {

        mvc.perform(post(URL_API_STATISTIC_CREATE_STAT).contentType(MediaType.APPLICATION_JSON).
                content("{\n" +
                        "\t\"vk\" : \"basta\",\n" +
                        "\t\"email\" : \"123\",\n" +
                        "\t\"password\" : \"$2a$10$3L.49hT4VQOWwW0svc0FbO0c.XXEoyXYWevxdkzBOwiPNoVzT//Yy\",\n" +
                        "\t\"identify\" : false,\n" +
                        "\t\"uid\" : \"1c7156bc-5cab-4458-9571-b102ca03fc53\"\n" +
                        "}")).andDo(print())
                .andExpect(status().isCreated());


    }

    @After
    public void tearDown() throws Exception {
        mvc.perform(delete(URL_API_STATISTIC_DELETE).
                param("uuid", "1c7156bc-5cab-4458-9571-b102ca03fc53")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getStat() throws Exception {


        mvc.perform(get(URL_API_STATISTIC_GET_STAT).param("vk", "basta").
                param("uuid", "1c7156bc-5cab-4458-9571-b102ca03fc53")).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void findAll() throws Exception {

        mvc.perform(get(URL_API_STATISTIC_FIND_ALL_STATS)
                .param("uuid", "1c7156bc-5cab-4458-9571-b102ca03fc53")).andDo(print())
                .andExpect(status().isOk());


    }


    @Test
    public void findWeek() throws Exception {
        mvc.perform(get(URL_API_STATISTIC_FIND_WEEK_STATS)
                .param("uuid", "1c7156bc-5cab-4458-9571-b102ca03fc53")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findMonth() throws Exception {
        mvc.perform(get(URL_API_STATISTIC_FIND_MONTH_STATS)
                .param("uuid", "1c7156bc-5cab-4458-9571-b102ca03fc53")).andDo(print())
                .andExpect(status().isOk());
    }


}