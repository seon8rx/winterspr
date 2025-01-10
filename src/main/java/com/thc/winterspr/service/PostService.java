package com.thc.winterspr.service;

import com.thc.winterspr.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*@Service*/
public interface PostService {
    int[] create(Map<String, Object> params);
    int update(Map<String, Object> params);
    int delete(Long id);
    Post detail(Long id);
    List<Post> list();
}
