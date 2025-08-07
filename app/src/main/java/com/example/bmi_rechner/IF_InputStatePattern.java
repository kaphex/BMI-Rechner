package com.example.bmi_rechner;

public interface IF_InputStatePattern {
    void updateLabels(String label, String hint);
    void updateLabels();
    void toggleState(InputState currentState);
}
