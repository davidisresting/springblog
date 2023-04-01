package com.example.springblog;

import com.example.springblog.entity.User;
import com.example.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringblogApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            User user = User.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("password")
                    .role("ADMIN")
                    .build();
            userRepository.save(user);
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringblogApplication.class, args);
    }

}
