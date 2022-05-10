package io.nology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.nology.Library.simulateLibrary;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int instructionsLength = Integer.parseInt(scanner.nextLine());
        List<String> instructions = new ArrayList<>();
        for (int i = 0; i < instructionsLength; i++) {
            instructions.add(scanner.nextLine());
        }
        scanner.close();
        List<String> res = simulateLibrary(instructions);
        for (String line : res) {
            System.out.println(line);
        }
    }

    }

