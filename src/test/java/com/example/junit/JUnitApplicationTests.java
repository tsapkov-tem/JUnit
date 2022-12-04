package com.example.junit;

import com.example.junit.Controllers.UsersController;
import com.example.junit.Repositories.UsersRepository;
import com.example.junit.Services.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class JUnitApplicationTests {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UsersService usersService;
    @Autowired
    UsersController usersController;

    @Test
    void contextLoads() {
        assertThat(usersRepository).isNotNull();
        assertThat(usersService).isNotNull();
        assertThat(usersController).isNotNull();
    }

}
