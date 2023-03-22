package com.example.springblog.controller;

import com.example.springblog.entity.Post;
import com.example.springblog.exception.SpringBlogException;
import com.example.springblog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String postList(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "post_list";
    }

    @GetMapping("/{id}")
    public String postDetail(Model model, @PathVariable Integer id) {
        model.addAttribute("posts", postService.findPostById(id));
        return "post_detail";
    }

    @GetMapping("/add")
    public String postForm(Model model) {
        model.addAttribute("post", new Post());
        return "post_form";
    }

    @PostMapping
    public String submitPostForm(@ModelAttribute("post") @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return "post_form";
        }
        postService.addPost(post);
        return "redirect:/posts";
    }

    @ExceptionHandler(SpringBlogException.class)
    public ModelAndView handleSpringBlogException(SpringBlogException exception) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", exception);
        return model;
    }
}
