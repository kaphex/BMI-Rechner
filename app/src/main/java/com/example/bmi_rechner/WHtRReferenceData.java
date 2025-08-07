package com.example.bmi_rechner;

public class WHtRReferenceData {
    /**
     * Gibt die Referenztabelle für die WHtR Strategie zurück
     * @param gender
     * @return String
     */
    public static String getWHtRReferenceCard(String gender) {
        String reference_table = "";
        switch (gender) {
            case "male":
                reference_table =
                        "           WHtR - Referenztabelle\n" +
                                "Männer [Werte bis ...]\n" +
                                "-----------------------------------------------\n" +
                                " EUG   | UG   |  NG   |  ÜG   |  AD I |  AD II \n" +
                                "-----------------------------------------------\n" +
                                " 0.34  | 0.42 | 0.52  | 0.57  | 0.62  | >0.62  \n";              ;
                break;
            case "female":
                reference_table =
                        "           WHtR - Referenztabelle\n" +
                                "Frauen [Werte bis ...]\n" +
                                "-----------------------------------------------\n" +
                                " EUG   |  UG  |  NG   |  ÜG   |  AD I |  AD II \n" +
                                "-----------------------------------------------\n" +
                                " 0.34  | 0.41 | 0.48  | 0.53  | 0.57  | 0.58   \n";
                break;

        }



        return reference_table;
    }
}
