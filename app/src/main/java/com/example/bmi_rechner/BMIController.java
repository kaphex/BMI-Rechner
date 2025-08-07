package com.example.bmi_rechner;

import android.content.Context;
import java.text.DecimalFormat;
import java.util.HashMap;

public class BMIController implements IF_BMI_Constants {
    private String age;
    private String size;
    private String weight;
    private String gender;
    private String bmiResult;
    private String category;
    public  boolean validateInput;
    private Context context;

    /**
     * Konstruktor für BMIController
     */
    public  BMIController(Context context) {
        this.context = context;
    }

    public void setBMIController(String age, String size, String weight, String gender) {
        this.age = age;
        this.size = size;
        this.weight = weight;
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public  String getBmiResult() {
        return bmiResult;
    }

    /**
     * Schnittstelle zwischen View und Model.
     * Steuert den Programmablauf.
     *
     */
    public BMIController handleUserInteraction() {
        // Erzeuge eine verschachtelte Hashmap mit Kategorien und Altersbereichen
        HashMap<String, HashMap<String, HashMap<String, String>>> bmiMap = BMICategoryUtility.createBMIRangeMap();

        DecimalFormat df = new DecimalFormat("0.00");
        StrategyManager strategyManager = chooseStrategy();

        // Hier wird eine Strategie zur Berechnung festgelegt
        // Optionen BMIStrategy und WaistToHeightStrategy
        if (strategyManager.validateInput(age, size, weight)) {
        double result = BMIModel.calculateBMI(strategyManager.getStrategy(), Double.parseDouble(size), Double.parseDouble(weight));

        // Kategoriensuche für BMI
        if (FirstFragment.getSwitchPosition()) {
            this.category = WHtRCategoryUtility.getWHtRCategory(gender, result);
            FirstFragment.setTextViewBMI("Waist to Height Ratio");
        } else {
            this.category = BMICategoryUtility.getBMICategory(result, gender, Integer.parseInt(age), bmiMap);
            FirstFragment.setTextViewBMI("Body-Mass-Index");
        }
        this.bmiResult = df.format(result);

        FirstFragment.setTextViewResult(bmiResult);
        FirstFragment.setTextViewCategory(category);

        strategyManager.highlightCategory(category);
        strategyManager.displayReferenceCard(gender);

        validateInput = true;
        return this;
        } else {
            validateInput = false;
        }
        return this;
    }

    /**
     * Auswahl der Strategie wird festgelegt durch StrategyManager.setStrategy()
     * @return
     */
    private StrategyManager chooseStrategy() {
        StrategyManager strategyManager = new StrategyManager();

        if (FirstFragment.getSwitchPosition()) {
            strategyManager.setStrategy(StrategyManager.CalculationStrategy.WAIST_TO_HEIGHT_STRATEGY);
        } else {
            strategyManager.setStrategy(StrategyManager.CalculationStrategy.BMI_STRATEGY);
        }

        return strategyManager;
    }
}
