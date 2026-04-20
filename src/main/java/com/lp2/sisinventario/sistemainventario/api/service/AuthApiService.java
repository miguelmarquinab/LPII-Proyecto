package com.lp2.sisinventario.sistemainventario.api.service;

import com.lp2.sisinventario.sistemainventario.api.dto.AuthRequest;
import com.lp2.sisinventario.sistemainventario.api.dto.AuthResponse;

public interface AuthApiService {
    AuthResponse login(AuthRequest request);
}
