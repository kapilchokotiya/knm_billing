package com.knm.TestExample;

import java.util.Scanner;

public class Statement {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("enter the day");
        String day=sc.next();

        switch(day) {
            case "MON":
                System.out.print("it is monday");
                break;

            case "tuesday":
                System.out.print("it is truesday");
                break;

            default: // Added default case for unsupported days
                System.out.println("Invalid day");
                break;
        }
    }
}
