package com.mialeds.services;

import com.mialeds.controllers.models.AuthResponse;
import com.mialeds.controllers.models.AuthenticationRequest;
import com.mialeds.controllers.models.RegisterRequest;

public interface AuthoService {

    AuthResponse registrer(RegisterRequest request);

    AuthResponse authenticate(AuthenticationRequest request);
}
