package io.wisoft.foodie.project.web;


import io.wisoft.foodie.project.service.AuthService;
import io.wisoft.foodie.project.web.dto.req.SignInRequest;
import io.wisoft.foodie.project.web.dto.req.SignUpRequest;
import io.wisoft.foodie.project.web.dto.res.SignInResponse;
import io.wisoft.foodie.project.web.dto.res.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody final SignUpRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest request) {
        return ResponseEntity
                .ok(authService.signIn(request));
    }
}
