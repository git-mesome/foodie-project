package io.wisoft.foodie.project.domain.account.web;


import io.wisoft.foodie.project.domain.account.web.dto.req.FindByOauthIdRequest;
import io.wisoft.foodie.project.domain.account.web.dto.req.SignUpRequest;
import io.wisoft.foodie.project.domain.account.web.dto.res.SignInResponse;
import io.wisoft.foodie.project.domain.account.application.AuthService;
import io.wisoft.foodie.project.domain.account.web.dto.res.SignUpResponse;
import io.wisoft.foodie.project.global.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(final AuthService authService,
                          final JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody final SignUpRequest request) {

        final SignUpResponse response = authService.signUp(request);

        final String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(response.getAccountId()));
        final String refreshToken = jwtTokenProvider.createRefreshToken();

        response.setToken(accessToken, refreshToken);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody final FindByOauthIdRequest oauthId) {

        final SignInResponse response = authService.signIn(oauthId);

        final String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(response.getAccountId()));
        final String refreshToken = jwtTokenProvider.createRefreshToken();

        response.setToken(accessToken, refreshToken);

        return ResponseEntity
                .ok(response);
    }


}
