package com.example.bmi_rechner;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.bmi_rechner.databinding.FragmentFirstBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstFragment extends Fragment implements IF_BMI_Constants {

    private FragmentFirstBinding binding;
    private static CardView cardView_BMI;
    private View cardView_Reference;
    private Button btn_reset;
    private Button btn_BMI;
    private static TextView textViewResult;
    private static TextView textViewCategory;
    private static TextInputEditText textEditAge;
    private static TextInputEditText textEditSize;
    private static TextInputEditText textEditWeight;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private static TextView textViewBMIReference;
    private static TextView textViewGewicht;
    private static Switch switchStrategy;
    private InputState currentState;
    private static TextInputLayout textInputLayoutWeight;
    private static TextView textViewBMI;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        cardView_BMI = binding.cardViewBMIValue;
        cardView_Reference = binding.cardViewReference;
        textViewResult = binding.textViewBMIValue;
        textViewCategory = binding.textViewBMICategory;
        textEditAge = binding.textEditAge;
        textEditSize = binding.textEditSize;
        textEditWeight = binding.textEditWeight;
        radioMale = binding.radioMale;
        radioFemale = binding.radioFemale;
        textViewBMIReference = binding.textViewReferenceCard;
        textViewGewicht = binding.textViewGewicht;
        switchStrategy = binding.switchStrategy;
        textInputLayoutWeight = binding.textInputLayoutWeight;
        textViewBMI = binding.textViewBMI1;
        btn_BMI = binding.BMIButton;

        textInputLayoutWeight.setHint("kg");

        currentState = new InputState("Gewicht", "kg");
        currentState.updateLabels();

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.BMI_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = "";
                String size = "";
                String weight = "";
                Context context = getContext() ;

                String gender = "";

                if (radioFemale.isChecked() == true) {
                    gender = GENDER_FEMALE;

                } else if (radioMale.isChecked() == true) {
                    gender = GENDER_MALE;
                }

                try {
                    age = textEditAge.getText().toString();
                    size = textEditSize.getText().toString();
                    weight = textEditWeight.getText().toString();
                } catch (NullPointerException e) {
                   createToast(context, "Nullpointer exception");
                }

                BMIController bmiController = new BMIController(getContext());

                bmiController.setBMIController(age, size, weight, gender);
                bmiController.handleUserInteraction();

                if (bmiController.validateInput) {
                    cardView_BMI.setVisibility(View.VISIBLE);
                    cardView_Reference.setVisibility(View.VISIBLE);
                } else {
                    // Nutzerdaten fehlerhaft
                    createToast(context, "Eingabe fehlerhaft.");
                } // validate User Data
            }
        });

        view.findViewById(R.id.reset_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        // OnClickListener für den Switch
        switchStrategy.setOnClickListener(v -> {
            // Aktuellen Zustand holen
            boolean isChecked = switchStrategy.isChecked();

            // Aktion ausführen
            if (isChecked) {
                // WHtR aktiv
                currentState.toggleState(currentState);
                btn_BMI.setText("Calculate WHtR");
                textEditWeight.setText("");         // betroffenes Textfeld leeren
            } else {
                // BMI aktiv
                currentState.toggleState(currentState);
                btn_BMI.setText("Calculate BMI");
                textEditWeight.setText("");         // betroffenes Textfeld leeren
            }
        });
    } // onViewCreated

    /**
     * Setzt alle Schaltflächen und Eingabefelder auf den Ausgangzustand zurück.
     */
    private void reset() {
        cardView_BMI.setVisibility(View.INVISIBLE);
        cardView_Reference.setVisibility(View.INVISIBLE);

        textEditSize.setText("");
        textEditAge.setText("");
        textEditWeight.setText("");

        // Fehler der textEdits zurücksetzen
        textEditSize.setError(null);
        textEditAge.setError(null);
        textEditWeight.setError(null);
    }

    /**
     * Konvertiert String zu int mit error handling
     * @param value, textedit
     * @return Integer
     */
    public static Integer parseSafe(String value, TextInputEditText textEdit) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // exception wird geworfen durch Integer.parseInt()
            textEdit.setError("Unerlaubte Zeichen");

            return -1;         // Falls Fehler auftritt, wird 0 zurückgegeben
        }
    }

    public  static String getTextEditAge() {
        return textEditAge.getText().toString();
    }

    public static String getTextEditSize() {
        return textEditSize.getText().toString();
    }

    public static String getTextEditWeight() {
        return textEditWeight.getText().toString();
    }

    public static void setTextErrorSize(String errorMessage) {
        textEditSize.setError(errorMessage);
    }

    public static void setTextErrorWeight(String errorMessage) {
        textEditWeight.setError(errorMessage);
    }

    public static void setTextErrorAge(String errorMessage) {
        textEditAge.setError(errorMessage);
    }

    public static TextInputEditText getInputTextEditAge() {
        return textEditAge;
    }

    public static TextInputEditText getInputTextEditSize() {
        return textEditSize;
    }

    public static TextInputEditText getInputTextEditWeight() {
        return textEditWeight;
    }

    public static void setHintTextEditWeight(String hintText) {
        textInputLayoutWeight.setHint(hintText);
    }

    public static void setCardViewBackground(int r, int g, int b) {
        cardView_BMI.setCardBackgroundColor(Color.rgb(r, g, b));
    }

    public static void setCardViewBackground(int color) {
        cardView_BMI.setCardBackgroundColor(color);
    }

    public static void setTextViewResult(String resultText) {
        textViewResult.setText(resultText);
    }

    public static void setTextViewCategory(String categoryText) {
        textViewCategory.setText(categoryText);
    }

    public static void setBMIReferenceCard(SpannableStringBuilder referenceTableText) {
        textViewBMIReference.setText(referenceTableText);
    }

    public static void setBMIReferenceCard(String referenceTableText) {
        textViewBMIReference.setText(referenceTableText);
    }

    public static void setTextViewGewicht(String text) {
        textViewGewicht.setText(text);
    }

    public static String getTextViewGewicht() {
        return textViewGewicht.getText().toString();
    }

    public static void setTextViewBMI(String text) {
        textViewBMI.setText(text);
    }

    public static void createToast(Context context, String toastMessage) {
        int duration = Toast.LENGTH_SHORT;

        Toast myToast = Toast.makeText(context, toastMessage, duration);
        myToast.show();
    }




    /**
     * Gibt die Schalterposition zurück
     * @return Boolean
     *  true: BMI
     *  false: WHtR
     */
    public static Boolean getSwitchPosition() {
        if (switchStrategy.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}