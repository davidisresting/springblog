package com.example.springblog.dto;

import com.example.springblog.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public User toUser(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setRole("USER");
        return user;
    }
}
