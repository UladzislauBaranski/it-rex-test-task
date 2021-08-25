package com.gmail.vladbaransky;

import com.gmail.vladbaransky.impl.FirstTaskImpl;
import com.gmail.vladbaransky.impl.SecondTaskImpl;

public class Main {

    public static void main(String[] args) {
        FirstTask firstTask = new FirstTaskImpl();
        firstTask.removeCFromTheText("the the th the qqqqqqqwwwqwwqwqeeeer222");
        firstTask.removeDoubleLetter("the the th the qqqqqqqwwwqwwqwqeeeer222");
        firstTask.removeLetterEAtEnd("the the th the qqqqqqqwwwqwwqwqeeeer222");
        firstTask.removeArticles("the the th the qqqqqqqwwwqwwqwqeeeer222");

        SecondTask secondTask = new SecondTaskImpl();
        secondTask.getShortestPathToPrincess();
    }
}
