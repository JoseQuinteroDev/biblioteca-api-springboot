package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.LoginRequest;
import com.josequintero.biblioteca.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
