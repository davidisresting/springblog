package com.example.springblog.repository;

import com.example.springblog.entity.Post;
import com.example.springblog.exception.SpringBlogException;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Repository
public class PostRepository {
    private final Set<Post> posts = new CopyOnWriteArraySet<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public Set<Post> findAllPosts() {
        return posts;
    }

    public Post findPostById(Integer postId) {
        return posts.stream().filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(() -> new SpringBlogException("Post with id " + postId + " not found"));
    }
}
