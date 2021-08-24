package com.gmail.vladbaransky;

import com.gmail.vladbaransky.impl.FirstTaskImpl;
import com.gmail.vladbaransky.impl.SecondTaskImpl;

public class Main {

    public static void main(String[] args) {
        FirstTask firstTask = new FirstTaskImpl();
        firstTask.removeArticles(" the the th the qqqqqqqwwwqwwqwqeeeer222");

        SecondTask secondTask = new SecondTaskImpl();
        secondTask.getShortestPathToPrincess();
    }
}
