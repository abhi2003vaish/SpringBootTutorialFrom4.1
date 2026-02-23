package com.codingshuttle.abhi.prod_ready_features.prod_ready_features.services;

import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service is not used bec not used for interfaces
public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long postId);
}
