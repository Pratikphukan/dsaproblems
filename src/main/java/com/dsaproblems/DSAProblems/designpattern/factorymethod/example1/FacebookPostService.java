package com.dsaproblems.DSAProblems.designpattern.factorymethod.example1;

public class FacebookPostService implements PostService {

    @Override
    public AuthorService getAuthorService() {
        return new FacebookAuthorService();
    }
}
