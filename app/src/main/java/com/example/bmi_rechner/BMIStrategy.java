package com.example.bmi_rechner;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;

public class BMIStrategy implements IF_BMI_Strategy , IF_BMI_Constants{
    private Context context;

    @Override
    public double executeStrategy(double size, double weight) {
        double bmi = weight / Math.pow((1*size/100), 2);

        return bmi;
    }

    @Override
    public boolean validateInput(String age, String size, String weight) {
        boolean data_correct = true;
        //Context context = getContext() ;
        // int duration = Toast.LENGTH_SHORT;
        int iAge, iSize, iWeight = 0;

        iAge = FirstFragment.parseSafe(age , FirstFragment.getInputTextEditAge());
        iSize = FirstFragment.parseSafe(size, FirstFragment.getInputTextEditAge());
        iWeight = FirstFragment.parseSafe(weight, FirstFragment.getInputTextEditSize());

        if (iAge == -1 || iSize == -1 || iWeight == -1) {
            data_correct = false;
        }

        if (age.isBlank()) {
            FirstFragment.setTextErrorAge("Kein Wert angegeben");
            data_correct = false;
        } else if (iAge < 19 && iAge >= 0) {
            FirstFragment.setTextErrorAge("Wert unzulässig. Alter >= 19");
            data_correct = false;
        } else if (iAge > 120) {
            FirstFragment.setTextErrorAge("Wert unzulässig. Alter <= 120");
            data_correct = false;
        }

        if (size.isBlank()) {
            FirstFragment.setTextErrorSize("Kein Wert angegeben");
            data_correct = false;
        } else if (iSize < 100 && iSize >= 0) {
            FirstFragment.setTextErrorSize("Wert unzulässig. Größe >= 100");
            data_correct = false;
        } else if (iSize > 240) {
            FirstFragment.setTextErrorSize("Wert unzulässig. Größe <= 240");
            data_correct = false;
        }

        if (weight.isBlank()) {
            FirstFragment.setTextErrorWeight("Kein Wert angegeben");
            data_correct = false;
        } else if (iWeight < 10 && iWeight >= 0) {
            FirstFragment.setTextErrorWeight("Wert unzulässig. Gewicht >= 10");
            data_correct = false;
        } else if (iWeight > 400) {
            FirstFragment.setTextErrorWeight("Wert unzulässig. Gewicht <= 400");
            data_correct = false;
        }

        return data_correct;
    }

    @Override
    public SpannableStringBuilder highlightRange(String reference_table) {
        return null;
    }

    @Override
    public void highlightCategory(String category) {

        switch (category) {
            case BMI_CATEGORY_UNDERWEIGHT:
                FirstFragment.setCardViewBackground(Color.rgb(225, 193, 1));
                break;
            case BMI_CATEGORY_HEALTHYWEIGHT:
                FirstFragment.setCardViewBackground(Color.rgb(76, 175, 80));
                break;
            case BMI_CATETORY_OVERWEIGHT:
                FirstFragment.setCardViewBackground(Color.RED);
                break;
            case BMI_CATEGORY_OBESITY:
                FirstFragment.setCardViewBackground(Color.rgb(139, 0, 0));
                break;
            case BMI_CATEGORY_EXTREME_OBESITY:
                FirstFragment.setCardViewBackground(Color.rgb(116, 0, 75));
                break;
        }
    }

    /**
     * displays the reference cards by gender
     * @param gender
     */
    @Override
    public void displayReferenceCard(String gender) {
        String reference_table = "";

        if (!BMICategoryUtility.getBMIRange().isEmpty()) {
            reference_table = BMIReferenceData.getBMIReferenceCard(gender);
            FirstFragment.setBMIReferenceCard(highlightBMIRange(reference_table));
        } else {
            FirstFragment.createToast(context,"Referenztabelle konnte nicht erstellt werden");
        }
    }

    /**
     * Hebt den berechneten BMI-Wert in der Referenztabelle farblich hervor unter Verwendung der
     * SpannableStringBuilder Klasse
     * @param reference_table
     * @return SpannableStringBuilder
     */
    private SpannableStringBuilder highlightBMIRange(String reference_table) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(reference_table);

        String foundRange = BMICategoryUtility.getBMIRange();
        int start = reference_table.indexOf(foundRange);

        if (start >= 0) {
            int end = start + foundRange.length();

            spannable.setSpan(new BackgroundColorSpan(Color.GREEN),start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannable;
        } else {
            return spannable;
        }
    }
}
