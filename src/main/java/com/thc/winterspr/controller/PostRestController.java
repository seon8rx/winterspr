package com.thc.winterspr.controller;

import com.thc.winterspr.domain.Post;
import com.thc.winterspr.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/post")
@RestController
public class PostRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;
    public PostRestController(
            PostService postService
    ) {
        this.postService = postService;
    }

    //create
    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params) {
        logger.info("create : " + params);

        int[] arrayResultCode = postService.create(params);

        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.put("resultCode", arrayResultCode[0]);
        returnValue.put("id", arrayResultCode[1]);

        return returnValue;
    }

    //update
    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params) {
        logger.info("update : " + params);

        int resultCode = postService.update(params);

        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.put("resultCode", resultCode);

        return returnValue;
    }
    //delete
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Map<String, Object> params) {
        logger.info("delete : " + params);

        Long id = Long.parseLong(params.get("id").toString());
        int resultCode = postService.delete(id);

        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.put("resultCode", resultCode);

        return returnValue;
    }
    //detail
    /*@GetMapping("/detail")
    public Map<String, Object> detail(@RequestParam int id) {
        logger.info("detail id : " + id);
        int resultCode = 200;
        Map<String, Object> board = postService.detail(id);
        if(board == null){
            resultCode = 0;
        }

        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.put("resultCode", resultCode);
        returnValue.put("data", board);

        return returnValue;
    }*/
    @GetMapping("/detail")
    public Post detail(@RequestParam Long id) {
        logger.info("detail id : " + id);
        return postService.detail(id);
    }
    //list

    /*@GetMapping("/list")
    public Map<String, Object> list() {
        logger.info("list");
        int resultCode = 200;
        List<Map<String, Object>> boards = postService.list();

        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.put("resultCode", resultCode);
        returnValue.put("data", boards);

        return returnValue;
    }*/
    @GetMapping("/list")
    public List<Post> list() {
        logger.info("list");
        return postService.list();
    }
}
