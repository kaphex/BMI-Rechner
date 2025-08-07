package com.example.bmi_rechner;

import android.text.SpannableStringBuilder;

public interface IF_BMI_Strategy {

    double executeStrategy(double size, double weight);
    boolean validateInput(String age, String size, String weight);
    SpannableStringBuilder highlightRange(String reference_table);
    void highlightCategory(String category);
    void displayReferenceCard(String gender);
}

