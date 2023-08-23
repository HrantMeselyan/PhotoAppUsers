package com.appsdeveloperblog.photoapp.api.users.photappapiusers.controller;

import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.service.EncryptService;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.UserAuthRequestDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.UserAuthResponseDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.Role;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.User;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.repository.UserRepository;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.service.UserService;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil tokenUtil;
    private final EncryptService encryptService;

    @GetMapping("/status/check")
    public String status() {
        return "yep" + env.getProperty("local.server.port");
    }

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponseDto> auth(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        Optional<User> byEmail = userRepository.findByEmail(userAuthRequestDto.getEmail());
        if (byEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = byEmail.get();
        if (!passwordEncoder.matches(userAuthRequestDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = tokenUtil.generateToken(userAuthRequestDto.getEmail());
        return ResponseEntity.ok(new UserAuthResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/encrypt")
    public String encrypt() {
        String encrypted = encryptService.encryptString("Hello, World!");
        return "Encrypted: " + encrypted;
    }

    @GetMapping("/decrypt/{encryptedString}")
    public String decrypt(@PathVariable String encryptedString) {
        String decrypted = encryptService.decryptString(encryptedString);
        return "Decrypted: " + decrypted;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}
