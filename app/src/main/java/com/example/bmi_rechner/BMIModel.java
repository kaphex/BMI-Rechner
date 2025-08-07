package com.example.bmi_rechner;

public class BMIModel {
    /**
     * Strategie-Pattern
     * Führt je nach ausgewählter Strategie die jeweils zugehörige Methode aus.
     * @param strategy
     * @param size
     * @param weight
     * @return double
     */
    public static double calculateBMI(IF_BMI_Strategy strategy, double size, double weight) {
        if (strategy != null) {
            return strategy.executeStrategy(size, weight);
        } else {
            throw new IllegalStateException("Strategie nicht gesetzt");
        }
    }
}
