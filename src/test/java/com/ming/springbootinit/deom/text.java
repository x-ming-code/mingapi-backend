package com.ming.springbootinit.deom;

public class Text {
    public static void main(String[] args) {
        Anlmal c = new Cat("1","小花");
        Anlmal c1 = new Cat("1","小花");
        Anlmal c2 = new Cat("2","小");
        System.out.println(c.equals(c1));
        System.out.println(c.equals(c2));
    }
}
