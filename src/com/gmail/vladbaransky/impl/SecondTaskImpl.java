package com.gmail.vladbaransky.impl;

import com.gmail.vladbaransky.SecondTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondTaskImpl implements SecondTask {
    private final static String FREE_SPACE = ".";
    private final static String COLUMN = "o";
    private final static Integer DEADLOCK = 1;
    private final static Integer NEXT_STEP = 5;
    private final static String PRINCE_POSITION = "1";
    private final static String PRINCES_POSITION = "2";
    private final static String INPUT_FILE = "input.txt";
    private final static String OUTPUT_FILE = "output.txt";

    private String[][][] readArrayFromFile() {
        String[][][] arr = new String[0][][];
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(INPUT_FILE), StandardCharsets.UTF_8))) {
            String line = br.readLine();
            String[] split = line.split(" ");
            int h = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int n = Integer.parseInt(split[2]);
            arr = new String[h][m][n];
            for (int k = 0; k < h; k++) {
                br.readLine();
                for (int i = 0; i < m; i++) {
                    line = br.readLine();
                    for (int j = 0; j < n; j++) {
                        String[] split1 = line.split("");
                        arr[k][i][j] = split1[j];
                        System.out.print(arr[k][i][j] + " ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    private void writeToFleShortestPath(String text) {
        try (FileWriter fw = new FileWriter(OUTPUT_FILE);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getShortestPathToPrincess() {
        String[][][] inputArray = readArrayFromFile();
        int numberK = inputArray.length;
        int numberI = inputArray[0].length;
        int numberJ = inputArray[0][0].length;
        int valueJ = 0;
        int valueI = 0;
        int valueK = 0;
        int indexValueJ = 0;
        int indexValueI = 0;
        int indexValueK = 0;
        int princesIndex = 0;
        int princesIndexValue = 0;

        int numberElementsArray = numberI * numberJ * numberK;

        List<List<Integer>> arrArr = new ArrayList<>();
        for (int k = 0; k < numberK; k++) {
            for (int i = 0; i < numberI; i++) {
                for (int j = 0; j < numberJ; j++) {

                    if (i == numberI - 1 && j == numberJ - 1) {
                        valueK = (inputArray[k][i][j].equals(COLUMN)) ? DEADLOCK : NEXT_STEP;
                        indexValueK = (k + 1) * numberI * numberJ - 1;
                    } else {

                        if (i < numberI - 1) {
                            valueI = (inputArray[k][i + 1][j].equals(COLUMN)) ? DEADLOCK : NEXT_STEP;
                            indexValueI = (i + 1) * numberI + j + 1 + k * numberI * numberJ - 1;
                        } else {
                            valueI = (inputArray[k][i][j + 1].equals(COLUMN)) ? DEADLOCK : NEXT_STEP;
                            indexValueI = (i) * numberI + j + 1 + k * numberI * numberJ;
                        }

                        if (j < numberJ - 1) {
                            valueJ = (inputArray[k][i][j + 1].equals(COLUMN)) ? DEADLOCK : NEXT_STEP;
                            indexValueJ = (i) * numberJ + j + 1 + 1 + k * numberI * numberJ - 1;

                        } else {
                            valueJ = (inputArray[k][i + 1][j].equals(COLUMN)) ? DEADLOCK : NEXT_STEP;
                            indexValueJ = (i + 1) * numberJ + j + 1 + 1 + k * numberI * numberJ;
                        }

                        if (k < numberK - 1) {
                            valueK = (inputArray[k + 1][i][j].equals(COLUMN)) ? DEADLOCK : NEXT_STEP;
                            indexValueK = (k + 1) * numberI * numberJ + (i * numberI + j + 1) - 1;
                        } else {
                            indexValueK = (k + 1) * numberI * numberJ + (i * numberI + j + 1) - 1;
                        }

                    }

                    if (inputArray[k][i][j].equals(PRINCES_POSITION)) {
                        princesIndexValue = 5;
                        princesIndex = i * numberI + j + k * numberI * numberJ;
                    }

                    List<Integer> arr = new ArrayList<>();
                    int index = i * numberJ + j + k * numberI * numberJ;
                    for (int z = index; z < numberElementsArray; z++) {
                        if (z == indexValueJ - 1) {
                            arr.add(valueJ);
                        } else if (z == indexValueI - 1) {
                            arr.add(valueI);
                        } else if (z == indexValueK - 1) {
                            arr.add(valueK);
                        } else if (z == princesIndex - 1) {
                            arr.add(princesIndexValue);
                        } else {
                            arr.add(0);
                        }
                    }
                    arrArr.add(arr);
                }
            }
        }

        int[][] adjacencyMatrix = new int[numberElementsArray][numberElementsArray];

        for (
                int i = 0;
                i < numberElementsArray; i++) {
            adjacencyMatrix[i][i] = 0;
            for (int j = i + 1; j < numberElementsArray; j++) {
                adjacencyMatrix[i][j] = arrArr.get(i).get(j - i - 1);
                adjacencyMatrix[j][i] = arrArr.get(i).get(j - i - 1);

            }
        }
        System.out.println("matArr:");
        for (int i = 0; i < numberElementsArray; i++) {
            for (int j = 0; j < numberElementsArray; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }

        int[] d = new int[numberElementsArray];
        int[] v = new int[numberElementsArray];
        int temp;
        int minIndex;
        int min;
        int beginIndex = 0;

        for (
                int i = 0;
                i < numberElementsArray; i++) {
            d[i] = 10000;
            v[i] = 1;
        }

        d[beginIndex] = 0;

        do {
            minIndex = 10000;
            min = 10000;
            for (int i = 0; i < numberElementsArray; i++) {
                if ((v[i] == 1) && (d[i] < min)) {
                    min = d[i];
                    minIndex = i;
                }
            }

            if (minIndex != 10000) {
                for (int i = 0; i < numberElementsArray; i++) {
                    if (adjacencyMatrix[minIndex][i] == 5) {
                        temp = min + adjacencyMatrix[minIndex][i];
                        if (temp <= d[i]) {
                            d[i] = temp;
                        }
                    }
                }
                v[minIndex] = 0;
            }
        } while (minIndex < numberElementsArray);
        int seconds = d[princesIndex];
        System.out.println("seconds:" + seconds);

        writeToFleShortestPath("Shortest path to princess: " + seconds + " seconds");
    }
}

