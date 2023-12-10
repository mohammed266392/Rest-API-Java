package com.example.restfulwebservices.user;

import com.example.restfulwebservices.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
