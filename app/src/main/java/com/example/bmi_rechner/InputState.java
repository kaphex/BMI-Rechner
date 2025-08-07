package com.example.bmi_rechner;
import android.content.Context;

public class InputState implements IF_InputStatePattern{
    String label, hint;

    public InputState(String label, String hint) {
        this.label = label;
        this.hint = hint;
    }

    @Override
    public void updateLabels(String label, String hint) {
        FirstFragment.setTextViewGewicht(label);
        FirstFragment.setHintTextEditWeight(hint);
    }

    @Override
    public void updateLabels() {
        if (!label.isEmpty() && !hint.isEmpty()) {
            FirstFragment.setTextViewGewicht(label);
            FirstFragment.setHintTextEditWeight(hint);
        }
    }

    @Override
    public void toggleState(InputState currentState) {
        if(currentState instanceof  InputState) {
            String label = FirstFragment.getTextViewGewicht();

            if (label.equals("Gewicht")) {
                this.label = "Bauchumfang";
                this.hint = "cm";
                updateLabels();
            } else if (label.equals("Bauchumfang")){
                this.label = "Gewicht";
                this.hint = "kg";
                updateLabels();
            }
        }
    }
}
