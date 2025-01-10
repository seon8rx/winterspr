package com.thc.winterspr.controller;

import com.thc.winterspr.domain.User;
import com.thc.winterspr.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/user")
@RestController
public class UserRestController {

    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public Long create(@RequestBody Map<String, Object> params) {
        return userService.create(params);
    }
    @PutMapping("")
    public int update(@RequestBody Map<String, Object> params) {
        userService.update(params);
        return 200;
    }
    @DeleteMapping("")
    public int delete(@RequestBody Long id) {
        userService.delete(id);
        return 200;
    }
    @GetMapping("")
    public User detail(@RequestParam Long id) {
        return userService.detail(id);
    }
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }
}
