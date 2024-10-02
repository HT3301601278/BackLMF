package org.example.backlmf.controller;

import org.example.backlmf.entity.User;
import org.example.backlmf.exception.UsernameAlreadyExistsException;
import org.example.backlmf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            logger.info("Received registration request for user: {}", user.getUsername());
            User registeredUser = userService.registerUser(user);
            logger.info("User registered successfully: {}", registeredUser.getUsername());
            return ResponseEntity.ok(registeredUser);
        } catch (UsernameAlreadyExistsException e) {
            logger.warn("Registration failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("注册过程中发生错误");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            logger.warn("获取当前用户失败：未认证");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        logger.info("尝试获取用户信息：{}", username);
        User user = userService.findByUsername(username);
        if (user == null) {
            logger.warn("获取当前用户失败：用户不存在 {}", username);
            return ResponseEntity.notFound().build();
        }
        logger.info("成功获取用户信息：{}", username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        try {
            logger.info("登录尝试，用户名: {}", loginUser.getUsername());
            if (loginUser.getUsername() == null || loginUser.getPassword() == null) {
                logger.warn("登录失败：用户名或密码为空");
                return ResponseEntity.badRequest().body("用户名和密码不能为空");
            }
            User user = userService.findByUsername(loginUser.getUsername());
            if (user != null && user.getPassword().equals(loginUser.getPassword())) {
                logger.info("用户登录成功: {}", user.getUsername());
                return ResponseEntity.ok(user);
            } else {
                logger.warn("无效的登录尝试，用户名: {}", loginUser.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码无效");
            }
        } catch (Exception e) {
            logger.error("登录过程中发生错误", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("登录过程中发生错误");
        }
    }
}
