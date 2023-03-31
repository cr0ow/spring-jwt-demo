package com.quicktodo.http.enpoint;

import com.quicktodo.handling.AuthenticationRequestHandler;
import com.quicktodo.http.HttpConstants;
import com.quicktodo.http.request.AuthenticationRequest;
import com.quicktodo.http.request.RegisterRequest;
import com.quicktodo.http.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = HttpConstants.AUTH_API_BASE_PATH)
public class AuthenticationHttpEndpoint {

    private final AuthenticationRequestHandler authenticationRequestHandler;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationRequestHandler.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationRequestHandler.authenticate(request));
    }

}
