package com.codingshuttle.abhi.prod_ready_features.prod_ready_features.services;

import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.codingshuttle.abhi.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    public final PostRepository postRepository;
    public final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        PostEntity postEntity= modelMapper.map(postDTO,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity=postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost=postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost,olderPost);
        return modelMapper.map(postRepository.save(olderPost),PostDTO.class);
    }
}
