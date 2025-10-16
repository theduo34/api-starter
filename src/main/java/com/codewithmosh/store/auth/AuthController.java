package com.codewithmosh.store.auth;

import com.codewithmosh.store.users.UserDto;
import com.codewithmosh.store.users.UserMapper;
import com.codewithmosh.store.users.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getEmail(),
                       request.getPassword()
               )
       );

       var user =  userRepository.findUserByEmail(request.getEmail()).orElseThrow();

       var accessToken = jwtService.generateAccessToken(user);
       var refreshToken = jwtService.generateRefreshToken(user);

       var cookie = new Cookie("refreshToken", refreshToken.toString());
       cookie.setHttpOnly(true);
       cookie.setPath("/auth/refresh");
       cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
       cookie.setSecure(true);
       response.addCookie(cookie);

       return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

//    @PostMapping("/validate")
//    public boolean validateToken(@RequestHeader("Authorization") String authHeader) {
//        System.out.println("Validating Token get called");
//        var token = authHeader.replace("Bearer ", "");
//        return jwtService.validateToken(token);
//    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(
            @CookieValue(name = "refreshToken") String refreshToken
    ) {
        var jwt = jwtService.parseToken(refreshToken);
        if(jwt == null || jwt.isExpired()) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var user = userRepository.findById(jwt.getUserId()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);

        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();
        System.out.println("User Id: " + userId);

       var user =  userRepository.findById(userId).orElse(null);
       if(user == null) {
           return ResponseEntity.notFound().build();
       }

       var userDto = userMapper.userToUserDto(user);
       return ResponseEntity.ok(userDto); 
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorized(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", "Invalid email or password")
        );
    }
}
