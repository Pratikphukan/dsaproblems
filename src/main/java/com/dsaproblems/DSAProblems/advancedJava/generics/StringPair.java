package com.dsaproblems.DSAProblems.advancedJava.generics;

import lombok.Getter;
import org.apache.logging.log4j.message.StringFormattedMessage;

@Getter
public class StringPair {

    private String first;

    private String second;

    public StringPair(String first, String second) {
        this.first = first;
        this.second = second;
    }
}
