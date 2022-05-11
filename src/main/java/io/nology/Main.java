package io.nology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.nology.Library.simulateLibrary;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int instructionsLength = Integer.parseInt(scanner.nextLine()); // the number of instructions
        List<String> instructions = new ArrayList<>(); // the list of instructions
        for (int i = 0; i < instructionsLength; i++) {
            instructions.add(scanner.nextLine()); // fill the list of instructions
        }
        scanner.close();
        List<String> res = simulateLibrary(instructions); // the list of results
        for (String line : res) {
            System.out.println(line); // print the results
        }
    }

    }

