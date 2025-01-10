package com.thc.winterspr.service.impl;

import com.thc.winterspr.domain.Post;
import com.thc.winterspr.repository.PostRepository;
import com.thc.winterspr.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostRepository postRepository;
    public PostServiceImpl(
            PostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }
/*

    int id = 1;
    List<Map<String, Object>> boards = new ArrayList<Map<String, Object>>(); //10개까지만 저장하는 것으로 합시다!

    public int getBoardsOrder(int id){
        int result = -1;
        for(int i=0;i<boards.size();i++){
            int tempId = Integer.parseInt(boards.get(i).get("id") + "");
            if(id == tempId){
                result = i;
            }
        }
        return result;
    }
*/


    @Override
    public int[] create(Map<String, Object> params) {
        logger.info("create : " + params);

        int[] resultValue = {200,0};

        Post post = new Post();
        post.setTitle(params.get("title").toString());
        post.setContent(params.get("content").toString());

        postRepository.save(post);

        /*String title = (String) params.get("title");
        if(title ==  null || title.isEmpty()){
            resultValue[0] = -100;
            return resultValue; //타이틀이 비어있는 경우
        }

        if(boards.size() == 10){
            resultValue[0] = -110;
            return resultValue; //10개 이상은 저장 안되요!
        }

        resultValue[1] = id;
        params.put("id", id++);
        boards.add(params);

        logger.info("resultValue : " + resultValue.toString());
        return resultValue;*/
        return resultValue;
    }

    @Override
    public int update(Map<String, Object> params) {
        logger.info("update : " + params);
        int resultCode = 0;

        Long id = Long.parseLong(params.get("id").toString());
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        String title = (String) params.get("title");
        if(title !=  null && !title.isEmpty()){
            post.setTitle(title);
        }
        String content = (String) params.get("content");
        if(content !=  null && !content.isEmpty()){
            post.setContent(content);
        }
        postRepository.save(post);


        /*String tempId = (String) params.get("id");
        if(tempId ==  null || tempId.isEmpty()){
            return resultCode; //
        }

        int id = Integer.parseInt(tempId) ;
        int order = getBoardsOrder(id); //실제 리스트에 순번!
        if(order == -1){
            return resultCode;
        }

        Map<String, Object> aBoard = boards.get(order);
        String title = (String) params.get("title");
        if(title !=  null && !title.isEmpty()){
            aBoard.put("title", title);
        }
        String content = (String) params.get("content");
        if(content !=  null && !content.isEmpty()){
            aBoard.put("content", content);
        }*/
        return 200;
    }

    @Override
    public int delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        postRepository.delete(post);


        /*logger.info("delete : " + params);
        int resultCode = 0;

        String tempId = (String) params.get("id");
        if(tempId ==  null || tempId.isEmpty()){
            return resultCode; //
        }

        int id = Integer.parseInt(tempId) ;
        int order = getBoardsOrder(id); //실제 리스트에 순번!
        if(order == -1){
            return resultCode;
        }

        boards.remove(order);*/
        return 200;
    }
    @Override
    public Post detail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return post;
    }
    @Override
    public List<Post> list() {
        /*return boards;*/

        List<Post> listPost = postRepository.findAll();
        return listPost;
    }
}
