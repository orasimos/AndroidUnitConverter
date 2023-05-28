package gr.aueb.cf.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputET;
    private Spinner inputSpinner;
    private Spinner settingsSpinner;
    private TextView outputTV;
    private Button convertBtn;
    private Double inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputET = findViewById(R.id.inputUnitET);
        inputSpinner = findViewById(R.id.inputSpinner);
        settingsSpinner = findViewById(R.id.settingsSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        outputTV = findViewById(R.id.outputTV);

        inputET.addTextChangedListener(inputTextWatcher);

//      --------- Input Array Adapter ---------
        ArrayAdapter<CharSequence> inputAdapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(inputAdapter);

        inputSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//      --------- Settings Array Adapter ---------
        ArrayAdapter<CharSequence> settingsAdapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        settingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        settingsSpinner.setAdapter(settingsAdapter);

        settingsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        --------- OnClickListener for convertBtn ---------
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultString;

                inputValue = Double.parseDouble(inputET.getText().toString().trim());
                String unitFrom = inputSpinner.getSelectedItem().toString();
                String unitTo = settingsSpinner.getSelectedItem().toString();

                double convertedValue = convert(inputValue, unitFrom, unitTo);

                resultString = convertedValue + " " + unitTo;
                outputTV.setText(resultString);

                inputET.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
    }


    private TextWatcher inputTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String inputETString = inputET.getText().toString().trim();

            convertBtn.setEnabled(!inputETString.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * Private method to convert input to requested units.
     * @param inputValue The user input value. 0.0 if no value is provided.
     * @param unitFrom  The units which the input value is in. Selected from inputSpinner.
     * @param unitTo    The units to convert to. Selected from settings Spinner.
     * @return  The result of the conversion. If no unitFrom or unitTo are
     *          selected, returns the input value. (Hopefully...)
     */
    private double convert(Double inputValue, String unitFrom, String unitTo) {

        switch (unitFrom) {
            case "kg":
                switch (unitTo) {
                    case "gr":
                        return inputValue * 1000;
                    case "mg":
                        return inputValue * 1000000;
                    case "tn":
                        return inputValue / 1000;
                    case "lb":
                        return inputValue * 2.20462;
                    case "oz":
                        return inputValue * 35.274;
                    default:
                        return inputValue;
                }
            case "gr":
                switch (unitTo) {
                    case "kg":
                        return inputValue / 1000;
                    case "mg":
                        return inputValue * 1000;
                    case "tn":
                        return inputValue / 1000000;
                    case "lb":
                        return inputValue * 0.00220462;
                    case "oz":
                        return inputValue * 0.035274;
                    default:
                        return inputValue;
                }
            case "mg":
                switch (unitTo) {
                    case "kg":
                        return inputValue * 1000000;
                    case "gr":
                        return inputValue * 1000;
                    case "tn":
                        return inputValue * 1000000000;
                    case "lb":
                        return inputValue * 2.2046e-6;
                    case "oz":
                        return inputValue * 3.5274e-5;
                    default:
                        return inputValue;
                }
            case "tn":
                switch (unitTo) {
                    case "kg":
                        return inputValue / 1000;
                    case "gr":
                        return inputValue / 1000000;
                    case "mg":
                        return inputValue / 1000000000;
                    case "lb":
                        return inputValue * 2204.62;
                    case "oz":
                        return inputValue * 35274;
                    default:
                        return inputValue;
                }
            case "lb":
                switch (unitTo) {
                    case "kg":
                        return inputValue * 0.453592;
                    case "gr":
                        return inputValue * 453.592;
                    case "mg":
                        return inputValue * 453592;
                    case "tn":
                        return inputValue * 0.000453592;
                    case "oz":
                        return inputValue * 16;
                    default:
                        return inputValue;
                }
            case "oz":
                switch (unitTo) {
                    case "kg":
                        return inputValue * 0.0283495;
                    case "gr":
                        return inputValue * 28.3495;
                    case "mg":
                        return inputValue * 28349.5;
                    case "tn":
                        return inputValue * 2.835e-5;
                    case "lb":
                        return inputValue / 16;
                    default:
                        return inputValue;
                }
            default:
                return inputValue;
        }
    }
}