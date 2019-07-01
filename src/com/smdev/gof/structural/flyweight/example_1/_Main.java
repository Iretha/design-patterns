package com.smdev.gof.structural.flyweight.example_1;

import java.util.ArrayList;
import java.util.List;

public class _Main {

    public static void main(String[] args) {

        List<Landmark> landmarks = new ArrayList<>();

        landmarks.add(new Landmark("Italy", "St Peter's Basilica, Vatican City", "It remains one of the two largest churches in the world."));
        landmarks.add(new Landmark("Italy", "Milan Cathedral (Duomo) – Milan, Italy", "It is the largest Gothic cathedral and the second largest Catholic cathedral in the world."));
        landmarks.add(new Landmark("Cambodia", "Siem Reap", "The majestic structure is Cambodia's most beloved and best-preserved temple."));
        landmarks.add(new Landmark("Peru", "Machu Picchu", "Located 8,000 ft high in the Andes, Peru's famous lost city is one of the most famous and spectacular ruins in the world."));
        landmarks.add(new Landmark("India", "Taj Mahal – Angra", "Standing majestically on the banks of the River Yamuna, India's national treasure is a symbol of love and romance. "));

        for (Landmark landmark : landmarks) {
            System.out.println(landmark);
        }
    }
}
