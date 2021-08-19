package com.gmail.vladbaransky.impl;

import com.gmail.vladbaransky.Task2;

public class Task2Impl implements Task2 {

    private static String FREE_SPACE = ".";
    private static String COLUMNS = "o";
    private static String PRINCE_POSITION = "1";
    private static String PRINCES_POSITION = "2";

    private int seconds;


    private String[][][] arr = {
            {
                    {"1", ".", "."},
                    {"o", "o", "."},
                    {".", ".", "."}
            },
            {
                    {"o", "o", "o"},
                    {".", ".", "o"},
                    {".", "o", "o"}
            },
            {
                    {"o", "o", "o"},
                    {"o", ".", "."},
                    {"o", ".", "2"}
            }
    };

    /*    public int f() {
            for (int i = 0; i <3; i++) {
                for (int j = 0; j <3; j++) {
                    for (int k = 0; k <3; k++) {
                        System.out.print(arr[i][j][k]);
                    }
                    System.out.println();
                }
                System.out.println("------------");
            }
            return 0;
        }*/
    public int f() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // System.out.print(arr[i][j][k]);
                    if (!arr[i][j][k + 1].equals(COLUMNS)) {
                        seconds += 5;
                        k++;
                    } else if (!arr[i][j + 1][k].equals(COLUMNS)) {
                        seconds += 5;
                    } else if (!arr[i + 1][j][k].equals(COLUMNS)) {
                        seconds += 5;
                        i++;
                    }
                }
            }
          //  System.out.print("|");
        }
        System.out.println("seconds: "+seconds);
        return seconds;
    }
}

