package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random rand = new Random();
        int numberOfTravels = 100000;
        ArrayList<Integer> travels = new ArrayList<>();
        for (int i = 0; i < numberOfTravels; i++) {
            travels.add(0);
        }

        int numberOfSells = 450000;
        for (int i = 0; i < numberOfSells; i++) {
            int travelId = rand.nextInt(numberOfTravels);
            travels.set(travelId, travels.get(travelId) + 1);
        }

        ArrayList<Integer> hist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            hist.add(0);
        }
        int numberOfMore10 = 0;
        for (int i = 0; i < numberOfTravels; i++) {
            int travelLen = travels.get(i);
            if (travelLen < 10) {
                hist.set(travelLen, hist.get(travelLen) + 1);
            } else {
                numberOfMore10++;
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.printf("%d - %d\n", i, hist.get(i));
        }
        System.out.println("more10: " + numberOfMore10);

        int left = hist.get(0) + hist.get(1) + hist.get(2) + hist.get(3) + hist.get(4);
        int right = hist.get(5) + hist.get(6) + hist.get(7) + hist.get(8) + hist.get(9) + numberOfMore10;
        System.out.println("left" + left);
        System.out.println("right" + right);
    }
}
