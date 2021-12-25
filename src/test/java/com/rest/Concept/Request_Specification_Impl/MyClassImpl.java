package com.rest.Concept.Request_Specification_Impl;

public class MyClassImpl implements MyInterface {

    public MyInterface MyInterface;

    @Override
    public MyInterface printMe() {
        System.out.println("I am in Print Me Of MyClassImpl");
        return MyInterface;
    }
}
