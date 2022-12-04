package com.example.junit;

import com.example.junit.Controllers.UsersController;
import com.example.junit.Models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"userCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsersController usersController;

    @Test
    public void getByIdTest() throws Exception{
        assertThat(usersController).isNotNull();
        this.mockMvc.perform(get("/user").param("id","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo("newuser1")))
                .andExpect(jsonPath("$.password", equalTo("newpass1")));

        this.mockMvc.perform(get("/user").param("id","2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(2)))
                .andExpect(jsonPath("$.username", equalTo("newuser2")))
                .andExpect(jsonPath("$.password", equalTo("newpass2")));

    }

    @Test
    public void getAllTest() throws Exception {
        assertThat(usersController).isNotNull();
        this.mockMvc.perform(get("/user/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[*].username", containsInAnyOrder("newuser1", "newuser2")))
                .andExpect(jsonPath("$[*].password", containsInAnyOrder("newpass1", "newpass2")));

    }

    @Test
    public void putUserTest() throws Exception {
        User putUser = new User();
        putUser.setId(1);
        putUser.setUsername("puttingUsername");
        putUser.setPassword("puttingPassword");
        assertThat(usersController).isNotNull();
        this.mockMvc.perform(put("/user").param("id", "1").flashAttr("user", putUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo("puttingUsername")))
                .andExpect(jsonPath("$.password", equalTo("puttingPassword")));
    }

    @Test
    public void saveUserTest() throws Exception {
        User newUser = new User();
        newUser.setId(3);
        newUser.setUsername("savingUsername");
        newUser.setPassword("savingPassword");
        assertThat(usersController).isNotNull();
        this.mockMvc.perform(post("/user").param("id", "3").flashAttr("user", newUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(3)))
                .andExpect(jsonPath("$.username", equalTo("savingUsername")))
                .andExpect(jsonPath("$.password", equalTo("savingPassword")));
    }

    @Test
    public void deleteUserTest() throws Exception{
        assertThat(usersController).isNotNull();
        this.mockMvc.perform(delete("/user").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
