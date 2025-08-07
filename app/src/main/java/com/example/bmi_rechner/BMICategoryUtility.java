package com.example.bmi_rechner;

import java.util.HashMap;

public class BMICategoryUtility implements IF_BMI_Constants {

    public static String FOUND_BMI_RANGE;
    private static HashMap<String, HashMap<String, HashMap<String, String>>> mapGenderSortedAgeRangesToBMICategories;


    public static String getBMIRange() {
        return FOUND_BMI_RANGE;
    }

    /**
     * Erzeugt Hashmaps mit Altersbereichen und Kategorien für beide Geschlechter
     * @return verschachtelte Hashmap
     */
    public static HashMap<String, HashMap<String, HashMap<String, String>>> createBMIRangeMap() {
        mapGenderSortedAgeRangesToBMICategories = new HashMap<>();

        // Male Kategorien
        HashMap<String, HashMap<String, String>> maleCategories = new HashMap<>();
        maleCategories.put("19-24", createBMIRangeMap(new String[]{"0-19", "19-24", "24-29", "29-34", "34-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        maleCategories.put("25-34", createBMIRangeMap(new String[]{"0-20", "20-25", "25-30", "30-35", "35-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        maleCategories.put("35-44", createBMIRangeMap(new String[]{"0-21", "21-26", "26-31", "31-36", "36-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        maleCategories.put("45-54", createBMIRangeMap(new String[]{"0-22", "22-27", "27-32", "32-37", "37-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        maleCategories.put("55-65", createBMIRangeMap(new String[]{"0-23", "23-28", "28-33", "33-38", "38-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        maleCategories.put("66-120", createBMIRangeMap(new String[]{"0-24", "24-29", "29-34", "34-39", "39-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));

        // Female Kategorien
        HashMap<String, HashMap<String, String>> femaleCategories = new HashMap<>();
        femaleCategories.put("19-24", createBMIRangeMap(new String[]{"0-18", "18-23", "23-28", "28-33", "33-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        femaleCategories.put("25-34", createBMIRangeMap(new String[]{"0-19", "19-24", "24-29", "29-34", "34-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        femaleCategories.put("35-44", createBMIRangeMap(new String[]{"0-20", "20-25", "25-30", "30-35", "35-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        femaleCategories.put("45-54", createBMIRangeMap(new String[]{"0-21", "21-26", "26-31", "31-36", "36-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        femaleCategories.put("55-65", createBMIRangeMap(new String[]{"0-22", "22-27", "27-32", "32-37", "37-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));
        femaleCategories.put("66-120", createBMIRangeMap(new String[]{"0-23", "23-28", "28-33", "33-38", "38-100"},
                new String[]{"Untergewicht", "Normalgewicht", "Übergewicht", "Adipositas Stufe I", "Adipositas Stufe II"}));

        mapGenderSortedAgeRangesToBMICategories.put("male", maleCategories);
        mapGenderSortedAgeRangesToBMICategories.put("female", femaleCategories);

        return mapGenderSortedAgeRangesToBMICategories;

    }

    /**
     * Hilfsmethode, die eine Hashmap erzeugt und fügt die Übergebenen Kategorien und Altersbereiche der HashMap hinzufügt
     * @param ranges
     * @param categories
     * @return HashMap<String, String>
     */
    private static HashMap<String, String> createBMIRangeMap(String[] ranges, String[] categories) {
        HashMap<String, String> bmiRangeMap = new HashMap<>();
        for (int i = 0; i < ranges.length; i++) {
            bmiRangeMap.put(ranges[i], categories[i]);
        }
        return bmiRangeMap;
    }

    /**
     * Durchsucht die übergebene HashMap nach der zugehörigen Kategorie
     * @param bmi
     * @param gender
     * @param age
     * @param bmiMap
     * @return
     */
    public static String getBMICategory(double bmi, String gender, int age, HashMap<String, HashMap<String, HashMap<String, String>>> bmiMap) {
            // Prüfen, ob das Geschlecht in der Map existiert
            if (!bmiMap.containsKey(gender)) {
                throw new IllegalArgumentException("Ungültiges Geschlecht: " + gender);
            }

            HashMap<String, HashMap<String, String>> ageRanges = bmiMap.get(gender);

            // Passenden Altersbereich finden
            for (String ageRange : ageRanges.keySet()) {
                if (isInRange(age, ageRange)) {
                    HashMap<String, String> bmiRanges = ageRanges.get(ageRange);

                    // Passenden BMI-Bereich finden
                    for (String bmiRange : bmiRanges.keySet()) {
                        if (isInRange(bmi, bmiRange)) {
                            FOUND_BMI_RANGE = bmiRange;
                            return bmiRanges.get(bmiRange); // Rückgabe der Kategorie
                        }
                    }
                }
            }

            return "Keine passende Kategorie gefunden";
}

    /**
     * Überprüft ob das übergebene Alter innerhalb der range liegt.
     * Gibt im Falle das der Wert in der range liegt True zurück.
     * @param value
     * @param range
     * @return boolean
     */
        private static boolean isInRange(double value, String range) {
            // bsp range: "66-120" -> lower=66; upper=120
            String[] bounds = range.split("-");         // splitted den String nach "-"
            double lower = Double.parseDouble(bounds[0]);
            double upper = Double.parseDouble(bounds[1]);
            return (value >= lower) && (value <= upper);
        }
}
