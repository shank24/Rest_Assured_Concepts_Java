package com.rest.Concept;

public class MethodChaining {
    public static void main(String[] args) {

                 a1()
                .a2()
                .a3();

        //RestAssured - Implementation
        //given().when().then()
    }

    public static MethodChaining a1(){
        System.out.println("a1");
        return new MethodChaining();
    }

    public static MethodChaining a2(){
        System.out.println("a2");
        return new MethodChaining();
    }

    public static MethodChaining a3(){
        System.out.println("a3");
        return new MethodChaining();
    }
}
