package com.rest;

public class MethodChaining {
    public static void main(String[] args) {

        MethodChaining mc = new MethodChaining();
        mc.a1().a2().a3();
    }

    public MethodChaining a1(){
        System.out.println("a1");
        return this;
    }

    public MethodChaining a2(){
        System.out.println("a2");
        return this;
    }

    public MethodChaining a3(){
        System.out.println("a3");
        return this;
    }
}
