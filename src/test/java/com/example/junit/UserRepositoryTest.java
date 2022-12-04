package com.example.junit;


import com.example.junit.Models.User;
import com.example.junit.Repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
@Sql(value = {"userCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void getByIdTest() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("newuser1");
        user1.setPassword("newpass1");
        assertThat(usersRepository.findById(1).orElse(null)).isNotNull();
        assertThat(usersRepository.findById(1).orElse(null)).isEqualTo(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("newuser2");
        user2.setPassword("newpass2");
        assertThat(usersRepository.findById(2).orElse(null)).isNotNull();
        assertThat(usersRepository.findById(2).orElse(null)).isEqualTo(user2);

    }

    @Test
    public void getAllTest() {
        List<User> users = (List<User>) usersRepository.findAll();
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
        User user = new User();
        user.setId(3);
        user.setUsername("newuser3");
        user.setPassword("newpass3");
        usersRepository.save(user);
        assertThat(usersRepository.findById(3).orElse(null)).isNotNull();
        assertThat(Objects.requireNonNull(usersRepository.findById(3).orElse(null)).getUsername()).isEqualTo("newuser3");
        assertThat(Objects.requireNonNull(usersRepository.findById(3).orElse(null)).getPassword()).isEqualTo("newpass3");
    }

    @Test
    public void deleteUserTest() {
        usersRepository.deleteById(1);
        assertThat(usersRepository.findById(1).orElse(null)).isNull();
    }

}
