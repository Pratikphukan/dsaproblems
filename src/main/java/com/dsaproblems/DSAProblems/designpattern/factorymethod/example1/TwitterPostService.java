package com.dsaproblems.DSAProblems.designpattern.factorymethod.example1;

public class TwitterPostService implements PostService {

    @Override
    public AuthorService getAuthorService() {
        return new TwitterAuthorService();
    }
}
