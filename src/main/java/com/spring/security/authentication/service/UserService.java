package com.spring.security.authentication.service;

import com.spring.security.authentication.dtos.*;
import com.spring.security.authentication.entity.*;
import com.spring.security.authentication.repository.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        User existingUser = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (existingUser != null) {
            return UserRegisterResponse.builder()
                    .message("User " + request.getUsername() + " already exist!!")
                    .build();
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername())
                .setFullName(request.getFullName())
                .setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
        return UserRegisterResponse.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .message("User Registered Successfully!!")
                .build();

    }

    public UserRegisterResponse loginUser(UserRegisterRequest loginRequest){
        User user=userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        if(user==null){
            return UserRegisterResponse.builder()
                    .message("Invalid username or User Not found, Please signup/register first")
                    .build();
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            return UserRegisterResponse.builder()
                    .message("Invalid Password")
                    .build();
        }
        return UserRegisterResponse.builder()
                .id(user.getId())
                .message(loginRequest.getUsername()+" login successfully")
                .build();
    }
}
