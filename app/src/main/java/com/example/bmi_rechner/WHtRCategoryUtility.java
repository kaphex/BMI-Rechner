package com.example.bmi_rechner;

import java.util.HashMap;
import java.util.Map;

public class WHtRCategoryUtility {
    private static final Map<String, HashMap<String, String>> whtrCategories = new HashMap<>();

    static {
        // erwachsene Männer
        HashMap<String, String> male = new HashMap<>();
        male.put("0.00-0.34", "Extremely slim");
        male.put("0.35-0.42", "Slim");
        male.put("0.43-0.52", "Healthy");
        male.put("0.53-0.57", "Overweight");
        male.put("0.58-0.62", "Very overweight");
        male.put("0.63-1.00", "Obese");

        // Erwachene Frauen
        HashMap<String, String> female = new HashMap<>();
        female.put("0.00-0.34", "Extremely slim");
        female.put("0.35-0.41", "Slim");
        female.put("0.42-0.48", "Healthy");
        female.put("0.49-0.53", "Overweight");
        female.put("0.54-0.57", "Very overweight");
        female.put("0.58-1.00", "Obese");



        // Kategorien nach Alter oder Geschlecht zuordnen
        whtrCategories.put("male", male);
        whtrCategories.put("female", female);

    }

    /**
     * Methode, um die WHtR-Kategorie basierend auf Gruppe und WHtR-Wert zu bestimmen
     * @param group
     * @param whtr
     * @return String
     */
    public static String getWHtRCategory(String group, double whtr) {
        if (!whtrCategories.containsKey(group)) {
            return "Unknown group";
        }

        HashMap<String, String> selectedGroup = whtrCategories.get(group);
        for (Map.Entry<String, String> entry : selectedGroup.entrySet()) {
            String[] range = entry.getKey().split("-");
            double min = Double.parseDouble(range[0]);
            double max = Double.parseDouble(range[1]);

            if (whtr >= min && whtr <= max) {
                return entry.getValue();
            }
        }

        return "Unknown category";
    }
}
