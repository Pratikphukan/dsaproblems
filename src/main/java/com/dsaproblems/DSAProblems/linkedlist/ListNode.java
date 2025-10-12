package com.dsaproblems.DSAProblems.linkedlist;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ListNode {

    @NonNull
    public Integer val;

    public ListNode next;

    public ListNode(@NonNull Integer val) {
        this.val = val;
    }
}
