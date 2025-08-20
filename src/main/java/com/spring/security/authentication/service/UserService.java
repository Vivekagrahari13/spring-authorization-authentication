package com.spring.security.authentication.service;

import com.spring.security.authentication.dtos.*;
import com.spring.security.authentication.entity.*;
import com.spring.security.authentication.repository.*;
import com.spring.security.exceptionHandling.*;
import com.spring.security.generic.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.spring.security.generic.Constants.*;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public GenericSuccessResponse registerUser(UserRegisterRequest request) {
        log.info("Signup/registration request received for username '{}'",request.getUsername());
        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            log.info("User '{}' already exists", request.getUsername());
            throw new GenericException(HttpStatus.BAD_REQUEST.value(), request.getUsername() + SINGLE_SPACE+ALREADY_EXIST);
        });
        User newUser = new User();
        newUser.setUsername(request.getUsername())
                .setFullName(request.getFullName())
                .setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
        String accessToken= jwtService.generateToken(newUser);
        UserRegisterResponse response= UserRegisterResponse.builder()
                .id(newUser.getId())
                .accessToken(accessToken)
                .build();
        log.info("User '{}' registered successfully",newUser.getUsername());
        return GenericSuccessResponse.builder()
                .success(true)
                .message(REGISTRATION_SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .data(response)
                .build();
    }

    public GenericSuccessResponse loginUser(UserRegisterRequest loginRequest){
        log.info("Login attempt for username: {}", loginRequest.getUsername());
        User user=userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        if(user==null){
            log.warn("Login failed - User not found for username: {}", loginRequest.getUsername());
            throw new GenericException(HttpStatus.NOT_FOUND.value(),"Invalid username or User Not found, Please signup/register first");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            log.warn("Login failed - Invalid password for username: {}", loginRequest.getUsername());
            throw new GenericException(HttpStatus.UNAUTHORIZED.value(), INVALID_PASSWORD);
        }
        String accessToken=jwtService.generateToken(user);
        log.info("Login successful for username: {} | User ID: {}", loginRequest.getUsername(), user.getId());
        UserRegisterResponse response= UserRegisterResponse.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .build();
        return GenericSuccessResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(loginRequest.getUsername()+" login successfully")
                .success(true)
                .data(List.of(response))
                .build();
    }

    public GenericSuccessResponse verifyToken(String accessToken) {
        String userName;
        if(!jwtService.validateToken(accessToken)){
            log.info("Token is invalid");
            throw new GenericException(HttpStatus.UNAUTHORIZED.value(),"Invalid access token");
        }
        if(jwtService.isTokenExpired(accessToken)){
            log.info("Token is expired");
            throw new GenericException(HttpStatus.UNAUTHORIZED.value(),"Token is expired");
        }
        userName= jwtService.extractUsername(accessToken);
        log.info("Token is valid and username is '{}' ",userName);
        return GenericSuccessResponse.builder()
                .data(Collections.emptyMap())
                .message("Token validation successful and user is: "+userName)
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }
}
