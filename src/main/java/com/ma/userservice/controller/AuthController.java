package com.ma.userservice.controller;

import com.ma.userservice.dto.AuthenticationStatusDto;
import com.ma.userservice.dto.JwtReqDto;
import com.ma.userservice.dto.JwtResDto;
import com.ma.userservice.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReqDto jwtReqDto){
        AuthenticationStatusDto statusDto = authenticate(jwtReqDto.getUserName(), jwtReqDto.getPassWord());
        if(!statusDto.getIsAuthenticated()){
            List<String> details = new ArrayList<>();
            details.add(statusDto.getMessage());
            ErrorResDto error = new ErrorResDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        final String token  = jwtTokenUtil.generateToken(jwtReqDto.getUserName());
        return ResponseEntity.ok(new JwtResDto(token));
    }

    private AuthenticationStatusDto authenticate(String userName, String passWord){
        AuthenticationStatusDto status;

        if(userService.validateUserPassWord(userName, passWord)){
            status = new AuthenticationStatusDto(true, "Authentication Successful");
        }else{
            status = new AuthenticationStatusDto(false, "Invalid UserName/PassWord");
        }

        return status;
    }


}
