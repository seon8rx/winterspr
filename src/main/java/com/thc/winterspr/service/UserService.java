package com.thc.winterspr.service;

import com.thc.winterspr.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Long create(Map<String, Object> params);
    void update(Map<String, Object> params);
    void delete(Long id);
    User detail(Long id);
    List<User> list();
}
