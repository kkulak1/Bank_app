package com.example.BankApplication.User;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<User> getUser(){
        return List.of(
                new User(
                        1L,
                        "Kamil Essa"
                ),
                new User(
                        2L,
                        "Adam Kowalski"
                )
        );
    }
}
