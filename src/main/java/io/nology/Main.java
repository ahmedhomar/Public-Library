package io.nology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.nology.Library.simulateLibrary;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            int instructionsLength = Integer.parseInt(scanner.nextLine()); // the number of instructions
            List<String> instructions = new ArrayList<>(); // the list of instructions
            for (int i = 0; i < instructionsLength; i++) {
                instructions.add(scanner.nextLine()); // add to the list of instructions
            }
            scanner.close();
            List<String> res = simulateLibrary(instructions); // the list of results
            for (String line : res) {
                System.out.println(line); // print the results
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
/*
instructions = [
        'register book 13 "Birth of a Theorem" by Villani, Cedric',
        'borrow 20 Heisenberg, Werner',
        'lookup number 11',
        'lookup title Machine Learning for Hackers',
        'lookup author Camus, Albert',
        'return Feynman, Richard',
        ]

 */