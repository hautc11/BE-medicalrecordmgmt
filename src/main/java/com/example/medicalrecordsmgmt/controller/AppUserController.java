package com.example.medicalrecordsmgmt.controller;

import com.example.medicalrecordsmgmt.domain.request.LoginRequest;
import com.example.medicalrecordsmgmt.domain.request.RegisterRequest;
import com.example.medicalrecordsmgmt.domain.response.LoginResponse;
import com.example.medicalrecordsmgmt.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        appUserService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) { return appUserService.login(request);
    }
}
