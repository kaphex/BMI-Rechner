package com.example.bmi_rechner;

public class StrategyManager {
    private static IF_BMI_Strategy strategy;

    public enum CalculationStrategy {
        BMI_STRATEGY,
        WAIST_TO_HEIGHT_STRATEGY
    }

    /**
     * Setzt die ausgewählte Strategie
     * @param strategyType
     */
    public   void setStrategy(CalculationStrategy strategyType) {
        if (CalculationStrategy.BMI_STRATEGY == strategyType) {
            this.strategy = new BMIStrategy();
        } else if (CalculationStrategy.WAIST_TO_HEIGHT_STRATEGY == strategyType) {
            this.strategy = new WaistToHeightStrategy();
        }
    }

    public IF_BMI_Strategy getStrategy() {
        return strategy;
    }

    /**
     * Validiert die Benutzereingaben in Abhängigkeit von verwendeter Strategie.
     * @param age
     * @param size
     * @param weight
     * @return
     */
    public boolean validateInput(String age, String size, String weight) {
        return strategy.validateInput(age, size, weight);
    }

    /**
     * Methode zum hervorheben der Kategorie implementiert als Strategy Pattern
     * @param category
     */
    public void highlightCategory(String category) {
        strategy.highlightCategory(category);
    }

    /**
     * Methode zur Darstellung der Referenztabelle als Strategy Pattern
     * @param gender
     */
    public void displayReferenceCard(String gender) {
        strategy.displayReferenceCard(gender);
    }
}
