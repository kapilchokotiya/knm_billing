package com.knm.TestExample;

public class Testifelse {
    public static void main(String[] args) {
        int n=15;
        if(n>20){
            System.out.println("grade a pass");
        }else if(n>19 && n<18){
            System.out.println("grade b pass");
        }else if(n>120){
            System.out.println("grade c pass");
        }else{
            System.out.println("fail");
        }
    }
}
