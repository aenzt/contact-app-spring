package com.aenzt.restfulapi.service;

import com.aenzt.restfulapi.entity.User;
import com.aenzt.restfulapi.model.RegisterUserRequest;
import com.aenzt.restfulapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ArgonService argonService;

    @Transactional
    public void register(RegisterUserRequest request) {

        validationService.validate(request);

        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(argonService.encoder.encode(request.getPassword()));
        user.setName(request.getName());

        userRepository.save(user);
    }
}
