package com.example.BankApplication.Storage;

import lombok.*;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@Component
public class UserStorage {
    private String user;
}
