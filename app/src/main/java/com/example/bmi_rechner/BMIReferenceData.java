package com.example.bmi_rechner;

public class BMIReferenceData {
    /**
     * Gibt die Referenztabelle in der BMI-Strategie in Anhängigkeit vom Geschlecht zurück.
     * @param gender
     * @return String
     */
    public static String getBMIReferenceCard(String gender) {
        String reference_table = "";
        switch (gender) {
            case "male":
                reference_table =
                        "           BMI - Referenztabelle\n" +
                                "Männer\n" +
                                "-----------------------------------------------\n" +
                                "Alter  |  UG  |  NG   |  ÜG   |  AD I |  AD II\n" +
                                "-----------------------------------------------\n" +
                                "19-24  | 0-19 | 19-24 | 24-29 | 29-34 | 34-100\n" +
                                "25-34  | 0-20 | 20-25 | 25-30 | 30-35 | 35-100\n" +
                                "35-44  | 0-21 | 21-26 | 26-31 | 31-36 | 36-100\n" +
                                "45-54  | 0-22 | 22-27 | 27-32 | 32-37 | 37-100\n" +
                                "55-65  | 0-23 | 23-28 | 28-33 | 33-38 | 38-100\n" +
                                "65-120 | 0-24 | 24-29 | 29-34 | 34-39 | 39-100";
                break;
            case "female":
                reference_table =
                        "           BMI - Referenztabelle\n" +
                                "Frauen\n" +
                                "-----------------------------------------------\n" +
                                "Alter  |  UG  |  NG   |  ÜG   |  AD I |  AD II\n" +
                                "-----------------------------------------------\n" +
                                "19-24  | 0-18 | 18-23 | 23-28 | 28-33 | 33-100\n" +
                                "25-34  | 0-19 | 19-24 | 24-29 | 29-34 | 34-100\n" +
                                "35-44  | 0-20 | 20-25 | 25-30 | 30-35 | 35-100\n" +
                                "45-54  | 0-21 | 21-26 | 26-31 | 31-36 | 36-100\n" +
                                "55-65  | 0-22 | 22-27 | 27-32 | 32-37 | 37-100\n" +
                                "65-120 | 0-23 | 23-28 | 28-33 | 33-38 | 38-100";
                break;

        }
        return reference_table;
    }
}


