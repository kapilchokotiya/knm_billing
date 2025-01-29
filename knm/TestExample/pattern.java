package com.knm.TestExample;

import java.util.Scanner;

public class pattern {
    public static void main(String[] args) {
        Scanner sd=new Scanner(System.in);
        System.out.println("enter the number");
        int n=sd.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
