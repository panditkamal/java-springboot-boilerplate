package org.example.controller;
import jakarta.persistence.EntityListeners;
import jakarta.validation.Valid;
import org.example.dto.LoginRequest;
import org.example.dto.LoginResponse;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.entity.Users;
import org.example.repository.UserRepository;
import org.example.util.JwtUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
@EntityListeners(AuditingEntityListener.class)
public class UserController {
    private final   UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    public UserController(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public UserResponse CreateUesr(@Valid @RequestBody UserRequest userRequest){
        Users user = new Users(
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getName()
        );
        user.hashPassword();
        Users savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getCreatedAt()
        );
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Optional<Users> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(userOpt.get().getEmail());
        return new LoginResponse(token, userOpt.get().getEmail());
    }
}
