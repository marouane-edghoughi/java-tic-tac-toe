package com.edghoughi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static Scanner scanner = new Scanner(System.in);
    private static char[][] gameBoard = {
            {' ', '|', ' ', '|', ' ' },
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' ' },
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' ' }
    };

    private static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    private static ArrayList<Integer> humanPositions = new ArrayList<Integer>();

    private static boolean quit = false;

    public static void main(String[] args) {

        Player computer = new Player("CPU", 'X');
        Player human = new Player();

        startGame(human);

        int playerPos = 0;

        while (!quit) {
            placeSymbol(gameBoard, playerPos, computer);
            printGameBoard();
            checkWinner(computer);

            System.out.println("Enter your position (1 - 9):");
            playerPos = scanner.nextInt();
            // used to clean the input buffer
            scanner.nextLine();

            placeSymbol(gameBoard, playerPos, human);
            checkWinner(human);
        }
    }

    private static void startGame(Player human) {
        System.out.println("\nHi, welcome to Tic Tac Toe Game!\n");

        System.out.println("Please enter your nickname:");
        String playerName = scanner.nextLine();
        human.setName(playerName);
        human.setSymbol('O');
    }

    private static void printGameBoard() {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void placeSymbol(char[][] gameBoard, int playerPos, Player player) {
        if (player.getName().equals("CPU")) {
            Random random = new Random();
            playerPos = random.nextInt(9) + 1;
            while (cpuPositions.contains(playerPos) || humanPositions.contains(playerPos)) {
                playerPos = random.nextInt(9) + 1;
            }
            System.out.println("\nThe CPU has chosen " + playerPos);
            cpuPositions.add(playerPos);
        } else {
            while (cpuPositions.contains(playerPos) || humanPositions.contains(playerPos)) {
                System.out.println("Position already taken, please enter a new position:");
                playerPos = scanner.nextInt();
            }
            humanPositions.add(playerPos);
        }
        switch (playerPos) {
            case 1:
                gameBoard[0][0] = player.getSymbol();
                break;
            case 2:
                gameBoard[0][2] = player.getSymbol();
                break;
            case 3:
                gameBoard[0][4] = player.getSymbol();
                break;
            case 4:
                gameBoard[2][0] = player.getSymbol();
                break;
            case 5:
                gameBoard[2][2] = player.getSymbol();
                break;
            case 6:
                gameBoard[2][4] = player.getSymbol();
                break;
            case 7:
                gameBoard[4][0] = player.getSymbol();
                break;
            case 8:
                gameBoard[4][2] = player.getSymbol();
                break;
            case 9:
                gameBoard[4][4] = player.getSymbol();
                break;
            default:
                break;
        }
    }

    private static void checkWinner(Player player) {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cr1 = Arrays.asList(1, 5, 9);
        List cr2 = Arrays.asList(3, 5, 7);

        // Creating a list to hold the possible winning combinations
        List<List> combs = new ArrayList<List>();

        // Adding the conditions to the list
        combs.add(topRow);
        combs.add(midRow);
        combs.add(botRow);
        combs.add(leftCol);
        combs.add(midCol);
        combs.add(rightCol);
        combs.add(cr1);
        combs.add(cr2);

        for (List comb : combs) {
            if (player.getName().equals("CPU") && cpuPositions.containsAll(comb)) {
                System.out.println("CPU wins!");
                quit = true;
            } else if (humanPositions.containsAll(comb)) {
                System.out.println(player.getName() + " wins. Congrats!");
                quit = true;
            } else if (cpuPositions.size() + humanPositions.size() == 9) {
                System.out.println("Draw!");
                quit = true;
            }
        }
    }
}
