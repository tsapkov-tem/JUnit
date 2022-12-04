package com.example.junit;

import com.example.junit.Models.User;
import com.example.junit.Services.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"userCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UsersServiceTest {
    @Autowired
    UsersService usersService;

    @Test
    public void getByIdTest(){
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("newuser1");
        user1.setPassword("newpass1");
        assertThat(usersService.getById(1)).isNotNull();
        assertThat(usersService.getById(1)).isEqualTo(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("newuser2");
        user2.setPassword("newpass2");
        assertThat(usersService.getById(2)).isNotNull();
        assertThat(usersService.getById(2)).isEqualTo(user2);
        assertThat(usersService.getById(3)).isNull();
    }

    @Test
    public void getAllTest() {
        List<User> users = usersService.getAll();
        assertThat(users.size()).isGreaterThan(0);
        assertThat(users.get(0).getId()).isEqualTo(1);
        assertThat(users.get(0).getUsername()).isEqualTo("newuser1");
        assertThat(users.get(0).getPassword()).isEqualTo("newpass1");
        assertThat(users.get(1).getId()).isEqualTo(2);
        assertThat(users.get(1).getUsername()).isEqualTo("newuser2");
        assertThat(users.get(1).getPassword()).isEqualTo("newpass2");
    }

    @Test
    public void saveUserTest() {
        User savedUser = new User();
        savedUser.setId(3);
        savedUser.setUsername("newuser3");
        savedUser.setPassword("newpass3");
        User newUser = usersService.saveUser(savedUser);
        assertThat(newUser).isNotNull();
        assertThat(newUser.getId()).isEqualTo(3);
        assertThat(newUser.getUsername()).isEqualTo("newuser3");
        assertThat(newUser.getPassword()).isEqualTo("newpass3");
    }

    @Test
    public void putUserTest(){
        User puttingUser = new User();
        puttingUser.setUsername("puttingUser1");
        puttingUser.setPassword("puttingPass1");
        User newUser = usersService.putUser(1, puttingUser);
        assertThat(newUser).isNotNull();
        assertThat(newUser.getId()).isEqualTo(1);
        assertThat(newUser.getUsername()).isEqualTo("puttingUser1");
        assertThat(newUser.getPassword()).isEqualTo("puttingPass1");
    }

    @Test
    public void deleteUserTest() {
        usersService.deleteUser(1);
        assertThat(usersService.getById(1)).isNull();
        assertThat(usersService.getById(2)).isNotNull();
    }
}
