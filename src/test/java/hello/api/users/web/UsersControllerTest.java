package hello.api.users.web;


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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    static final String URL_API_USERS="http://localhost:8080/api-users/";

    static final String URL_API_USERS_LOGIN = URL_API_USERS.concat("login");
    static final String URL_API_USERS_REGISTR = URL_API_USERS.concat("create");
    static final String URL_API_USERS_UPDATE = URL_API_USERS.concat("update");
    static final String URL_API_USERS_GET = URL_API_USERS.concat("get");
    static final String URL_API_USERS_DELETE = URL_API_USERS.concat("delete");
    static final String URL_API_USERS_ALL = URL_API_USERS.concat("getAll");



    @Before
    public void beforeUser()  throws Exception {

        mvc.perform(post(URL_API_USERS_REGISTR).contentType(MediaType.APPLICATION_JSON).
                content("{\n" +
                        "\t\"email\" : \"123\",\n" +
                        "\t\"vk\" : \"vkd\",\n" +
                        "\t\"password\" : \"1234\",\n" +
                        "\t\"uid\" : \"1c7156bc-5cab-4458-9571-b102ca03fc50\"\n"+
                        "}")).andDo(print())
                .andExpect(status().isCreated());
    }

    @After
    public void AfterUser() throws Exception {
        mvc.perform(delete(URL_API_USERS_DELETE).
                param("uuid","1c7156bc-5cab-4458-9571-b102ca03fc50")).andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    public void getUser() throws Exception {


       mvc.perform(get(URL_API_USERS_GET).param("uuid","1c7156bc-5cab-4458-9571-b102ca03fc50")).andDo(print())
               .andExpect(status().isOk());


    }

    @Test
    public void updateUser() throws Exception{
        mvc.perform(put(URL_API_USERS_UPDATE).contentType(MediaType.APPLICATION_JSON).
                content("{\n" +
                        "\t\"email\" : \"12773\",\n" +
                        "\t\"vk\" : \"vkd\",\n" +
                        "\t\"password\" : \"1234\",\n" +
                        "\t\"uid\" : \"1c7156bc-5cab-4458-9571-b102ca03fc50\"\n"+
                        "}")).andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void loginUser()throws Exception {
        mvc.perform(post(URL_API_USERS_LOGIN).contentType(MediaType.APPLICATION_JSON).
                content("{\n" +
                        "\t\"email\" : \"123\",\n" +
                        "\t\"password\" : \"1234\"\n" +
                        "}")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers()throws Exception {

        mvc.perform(get(URL_API_USERS_ALL)).andDo(print())
                .andExpect(status().isOk());


    }
}