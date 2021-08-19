package com.gmail.vladbaransky.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void calculateRoverPath(int[][] map) {
        String result = "path-plan.txt";
        int numberRow = map.length;
        int numberColumn = map[0].length;
        int numberElementsArray = numberRow * numberColumn;

        List<List<Integer>> arrArr = new ArrayList<>();

        for (int i = 0; i < numberRow; i++) {
            for (int j = 0; j < numberColumn; j++) {
                int valueJ;
                int valueI;
                int indexValueJ;
                int indexValueI;
                if (i == numberRow - 1 && j == numberColumn - 1) {
                    break;
                }
                if (j == numberColumn - 1) {
                    valueJ = Math.abs(map[i][j] - map[i + 1][j]) + 1;
                    indexValueJ = i * numberColumn + j;
                } else {
                    valueJ = Math.abs(map[i][j] - map[i][j + 1]) + 1;
                    indexValueJ = (i) * numberColumn + j + 1;
                }
                if (i == numberRow - 1) {
                    valueI = Math.abs(map[i][j] - map[i][j + 1]) + 1;
                    indexValueI = (i) * numberColumn + j;
                } else {
                    valueI = Math.abs(map[i][j] - map[i + 1][j]) + 1;
                    indexValueI = (i + 1) * numberColumn + j;
                }

                List<Integer> arr = new ArrayList<>();
                int index = i * numberColumn + j;
                for (int k = index; k < numberElementsArray - 1; k++) {
                    if (k == indexValueJ - 1) {
                        arr.add(valueJ);
                    } else if (k == indexValueI - 1) {
                        arr.add(valueI);
                    } else {
                        arr.add(0);
                    }
                }
                arrArr.add(arr);
            }
        }

        int[][] adjacencyMatrix = new int[numberElementsArray][numberElementsArray];
        for (int i = 0; i < numberElementsArray; i++) {
            adjacencyMatrix[i][i] = 0;
            for (int j = i + 1; j < numberElementsArray; j++) {
                adjacencyMatrix[i][j] = arrArr.get(i).get(j - i - 1);
                adjacencyMatrix[j][i] = arrArr.get(i).get(j - i - 1);
            }
        }





   //-------------------------------------------------------------------
        int[] d = new int[numberElementsArray];
        int[] v = new int[numberElementsArray];
        int temp;
        int minindex;
        int min;
        int begin_index = 0;

        for (int i = 0; i < numberElementsArray; i++) {
            d[i] = 10000;
            v[i] = 1;
        }
        d[begin_index] = 0;

        do {
            minindex = 10000;
            min = 10000;
            for (int i = 0; i < numberElementsArray; i++) {
                if ((v[i] == 1) && (d[i] < min)) {
                    min = d[i];
                    minindex = i;
                }
            }

            if (minindex != 10000) {
                for (int i = 0; i < numberElementsArray; i++) {
                    if (adjacencyMatrix[minindex][i] > 0) {
                        temp = min + adjacencyMatrix[minindex][i];
                        if (temp < d[i]) {
                            d[i] = temp;
                        }
                    }
                }
                v[minindex] = 0;
            }

        } while (minindex < numberElementsArray);


        int[] ver = new int[numberElementsArray];
        int end = numberElementsArray - 1;
        ver[0] = numberElementsArray;
        int k = 1;
        int weight = d[end];
        while (end != begin_index) {
            for (int i = 0; i < numberElementsArray; i++)

                if (adjacencyMatrix[i][end] != 0) {
                    int temp1 = weight - adjacencyMatrix[i][end];
                    if (temp1 == d[i]) {
                        weight = temp1;
                        end = i;
                        ver[k] = i + 1;
                        k++;
                    }
                }
        }
        int step = k - 1;
        int[] valueI = new int[k];
        int[] valueJ = new int[k];

        for (int i = k - 1; i >= 0; i--) {

            valueI[k - 1 - i] = (ver[i] - 1) / numberColumn;
            valueJ[k - 1 - i] = (ver[i] - 1) % numberColumn;

        }

        int fuel = d[numberElementsArray - 1];
        String string = read(fuel, valueI, valueJ, step);
        write(string, result);
    }

    private static String read(int fuel, int[] valueI, int[] valueJ, int step) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valueI.length; i++) {
            sb.append("[");
            sb.append(valueI[i]);
            sb.append("]");
            sb.append("[");
            sb.append(valueJ[i]);
            sb.append("]");
            if (i != valueI.length - 1) {
                sb.append("->");
            }
        }
        sb.append("\n");
        sb.append("steps:");
        sb.append(step);
        sb.append("\n");
        sb.append("fuel:");
        sb.append(fuel);

        return sb.toString();
    }


    private static void write(String text, String result) {
        try (FileWriter fw = new FileWriter(result);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
