package org.example;

import java.util.ArrayList;
import java.util.List;


class Library {
    protected static List<Books> books = new ArrayList<>();
    protected static List<Member> members=new ArrayList<>();

    protected static int count =0 ;
    // public Library() {
    //  books = new ArrayList<>();
    //  members = new ArrayList<>();
    // }
    public Member loginMember(String memberName, String phoneNumber) {
        for (Member member : members) {
            //System.out.println(member.getPhoneNumber());
            //System.out.println(member.getName());
            if (member.getName().equals(memberName) && member.getPhoneNumber().equals(phoneNumber)) {
                // System.out.println("hello");
                return member;
            }
        }
        return null;
    }
}

