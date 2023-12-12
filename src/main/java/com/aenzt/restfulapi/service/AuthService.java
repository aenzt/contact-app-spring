package com.aenzt.restfulapi.service;

import com.aenzt.restfulapi.entity.User;
import com.aenzt.restfulapi.model.LoginUserRequest;
import com.aenzt.restfulapi.model.TokenResponse;
import com.aenzt.restfulapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ArgonService argonService;

    private TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user;
        try {
            user = userRepository.findFirstByEmail(request.getEmail());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password is wrong");
        }
        if (argonService.encoder.matches(request.getPassword(), user.getPassword())){

        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password is wrong");
        }

    }
}
