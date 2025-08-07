package com.example.bmi_rechner;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;

public class WaistToHeightStrategy implements IF_BMI_Strategy {

    @Override
    public double executeStrategy(double size, double waist) {
        return waist / size;
    }

    @Override
    public boolean validateInput(String age, String size, String weight) {
        int iAge, iSize, iWeight = 0;
        boolean data_correct = true;

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
        } else if (iWeight < 0) {
            FirstFragment.setTextErrorWeight("Wert unzulässig. Bauchumfang >= 0");
            data_correct = false;
        } else if (iWeight > 500) {
            FirstFragment.setTextErrorWeight("Wert unzulässig. Bauchumfang < 500");
            data_correct = false;
        }
        return data_correct;
    }

    @Override
    public SpannableStringBuilder highlightRange(String reference_table) {
        return null;
    }

    @Override
    public void displayReferenceCard(String gender) {
        String reference_table = "";
        reference_table = WHtRReferenceData.getWHtRReferenceCard(gender);
        FirstFragment.setBMIReferenceCard(reference_table);
    }

    @Override
    public void highlightCategory(String category) {

        switch (category) {
            case "Extremely slim":
                FirstFragment.setCardViewBackground(Color.rgb(225, 193, 1));
                break;
            case "Slim":
                FirstFragment.setCardViewBackground(Color.rgb(76, 175, 80));
                break;
            case "Healthy":
                FirstFragment.setCardViewBackground(Color.RED);
                break;

            case "Overweight":
                FirstFragment.setCardViewBackground(Color.rgb(139, 0, 0));
                break;
            case "Very overweight":
                FirstFragment.setCardViewBackground(Color.rgb(116, 0, 75));
                break;
            case "Obese":
                FirstFragment.setCardViewBackground(120, 0, 85);
        }
    }

 /*   private SpannableStringBuilder highlightBMIRange(String reference_table, String category) {
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
    }*/
}
