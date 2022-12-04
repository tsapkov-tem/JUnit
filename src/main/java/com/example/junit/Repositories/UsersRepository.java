package com.example.junit.Repositories;

import com.example.junit.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
}
