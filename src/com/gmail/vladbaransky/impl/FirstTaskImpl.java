package com.gmail.vladbaransky.impl;

import com.gmail.vladbaransky.FirstTask;

import java.util.*;

public class FirstTaskImpl implements FirstTask {
    private final static String CI = "ci";
    private final static String SI = "si";
    private final static String CE = "ce";
    private final static String SE = "se";
    private final static String CK = "ck";
    private final static String K = "k";
    private final static String C = "c";

    private final static String EE = "ee";
    private final static String I = "i";
    private final static String OO = "oo";
    private final static String U = "u";
    private final static String REGEX = "[\\s.,]+";

    public String removeCFromTheText(String text) {
        System.out.println("Text before modification: " + text);
        String stringWithoutCi = (text.contains(CI)) ? text.replace(CI, SI) : text;
        String stringWithoutCe = (stringWithoutCi.contains(CE)) ? stringWithoutCi.replace(CE, SE) : text;
        String stringWithoutCk = (stringWithoutCe.contains(CK)) ? stringWithoutCe.replace(CK, K) : text;
        String stringWithoutC = (stringWithoutCk.contains(C)) ? stringWithoutCk.replace(C, K) : text;
        System.out.println("Text after modification: " + stringWithoutC);
        return stringWithoutC;
    }


    public String removeDoubleLetter(String text) {
        String stringWithoutEe = (text.contains(EE)) ? text.replace(EE, I) : text;
        String stringWithoutOo = (stringWithoutEe.contains(OO)) ? stringWithoutEe.replace(OO, U) : text;
        System.out.println(text);
        String stringWithoutDoubleLeLetter = stringWithoutOo.replaceAll("([0-9a-zA-Z])\\1", "$1");
        System.out.println("Result string:" + stringWithoutDoubleLeLetter);
        return stringWithoutDoubleLeLetter;
    }

    public String removeLetterEAtEnd(String text) {
        char[] wordByCharacterWithoutLastElementE;
        StringBuilder resultString = new StringBuilder();
        String[] textByWord = text.split(REGEX);

        for (int i = 0; i < textByWord.length; i++) {
            char[] wordByCharacter = textByWord[i].toCharArray();
            if (wordByCharacter[wordByCharacter.length - 1] == 'e' && wordByCharacter.length > 1) {
                wordByCharacterWithoutLastElementE = Arrays.copyOfRange(wordByCharacter, 0, wordByCharacter.length - 1);
                String wordWithoutLastElementE = String.valueOf(wordByCharacterWithoutLastElementE);
                System.out.println("wordWithoutLastElementE:" + wordWithoutLastElementE);
                resultString.append(wordWithoutLastElementE).append(" ");
            }
        }
        return resultString.toString();
    }

    public String removeArticles(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] originalTextByWord = text.split(REGEX);
        String[] modifiedTxtByWord = text.split(REGEX);

        for (int i = 0; i < originalTextByWord.length; i++) {
            if (!originalTextByWord[i].equals("a") || !originalTextByWord[i].equals("an") || !originalTextByWord[i].equals("the")) {
                stringBuilder.append(modifiedTxtByWord[i]);
            }
        }
        String resultString = stringBuilder.toString();
        System.out.println("Modified string:" + resultString);
        return resultString;
    }
}