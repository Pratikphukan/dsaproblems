package com.dsaproblems.DSAProblems.designpattern.factorymethod.example1;

public class PostHandler {

    private PostService postService;

    public PostHandler(PostService postService) {
        this.postService = postService;
    }

}
