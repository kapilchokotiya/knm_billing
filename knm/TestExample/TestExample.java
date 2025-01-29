package com.knm.TestExample;

public class TestExample {
    public static void main(String[] args) {
        int num = 65;

        // Check if the number is not equal to 80
        if (num != 80) {
            System.out.println("Working");
        }

        // Check if the number is greater than 100
        if (num > 100) {
            System.out.println("Not working");
        }

        // Check if the number is exactly 200
        if (num == 200) {
            System.out.println("Continue");
        }

        // Additional example of else-if for better logic flow
        if (num == 80) {
            System.out.println("Number is 80");
        } else if (num > 100) {
            System.out.println("Number is greater than 100");
        } else {
            System.out.println("Number does not meet any special conditions");
        }
    }
}
