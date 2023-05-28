package gr.aueb.cf.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private EditText inputUnitET;
    private Spinner inputSpinner;
    private Spinner settingsSpinner;
    private TextView outputTV;
    private Button convertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUnitET = findViewById(R.id.inputUnitET);
        inputSpinner = findViewById(R.id.inputSpinner);
        settingsSpinner = findViewById(R.id.settingsSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        outputTV = findViewById(R.id.outputTV);


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

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultString;
                double inputValue = Double.parseDouble(inputUnitET.getText().toString());
                String unitFrom = inputSpinner.getSelectedItem().toString();
                String unitTo = settingsSpinner.getSelectedItem().toString();

                double convertedValue = convert(inputValue, unitFrom, unitTo);

                resultString = convertedValue + " " + unitTo;
                outputTV.setText(resultString);

                inputUnitET.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
    }

    /**
     * Private method to convert input to requested units.
     * @param inputValue The user input value.
     * @param unitFrom  The units which the input value is in.
     * @param unitTo    The units to convert to
     * @return  The result of the conversion. If no unitFrom or unitTo are
     *          selected, returns the input value. (Hopefully...)
     */
    private double convert(double inputValue, String unitFrom, String unitTo) {

        double result;

        switch (unitFrom) {
            case "kg":
                switch (unitTo) {
                    case "gr":
                        result = inputValue * 1000;
                        break;
                    case "mg":
                        result = inputValue * 1000000;
                        break;
                    case "tn":
                        result = inputValue / 1000;
                        break;
                    case "lb":
                        result = inputValue * 2.20462;
                        break;
                    case "oz":
                        result = inputValue * 35.274;
                        break;
                    default:
                        result = inputValue;
                        break;
                }
                break;
            case "gr":
                switch (unitTo) {
                    case "kg":
                        result = inputValue / 1000;
                        break;
                    case "mg":
                        result = inputValue * 1000;
                        break;
                    case "tn":
                        result = inputValue / 1000000;
                        break;
                    case "lb":
                        result = inputValue * 0.00220462;
                        break;
                    case "oz":
                        result = inputValue * 0.035274;
                        break;
                    default:
                        result = inputValue;
                        break;
                }
                break;
            case "mg":
                switch (unitTo) {
                    case "kg":
                        result = inputValue * 1000000;
                        break;
                    case "gr":
                        result = inputValue * 1000;
                        break;
                    case "tn":
                        result = inputValue * 1000000000;
                        break;
                    case "lb":
                        result = inputValue * 2.2046e-6;
                        break;
                    case "oz":
                        result = inputValue * 3.5274e-5;
                        break;
                    default:
                        result = inputValue;
                        break;
                }
                break;
            case "tn":
                switch (unitTo) {
                    case "kg":
                        result = inputValue / 1000;
                        break;
                    case "gr":
                        result = inputValue / 1000000;
                        break;
                    case "mg":
                        result = inputValue / 1000000000;
                        break;
                    case "lb":
                        result = inputValue * 2204.62;
                        break;
                    case "oz":
                        result = inputValue * 35274;
                        break;
                    default:
                        result = inputValue;
                        break;
                }
                break;
            case "lb":
                switch (unitTo) {
                    case "kg":
                        result = inputValue * 0.453592;
                        break;
                    case "gr":
                        result = inputValue * 453.592;
                        break;
                    case "mg":
                        result = inputValue * 453592;
                        break;
                    case "tn":
                        result = inputValue * 0.000453592;
                        break;
                    case "oz":
                        result = inputValue * 16;
                        break;
                    default:
                        result = inputValue;
                        break;
                }
                break;
            case "oz":
                switch (unitTo) {
                    case "kg":
                        result = inputValue * 0.0283495;
                        break;
                    case "gr":
                        result = inputValue * 28.3495;
                        break;
                    case "mg":
                        result = inputValue * 28349.5;
                        break;
                    case "tn":
                        result = inputValue * 2.835e-5;
                        break;
                    case "lb":
                        result = inputValue / 16;
                        break;
                    default:
                        result = inputValue;
                        break;
                }
                break;
            default:
                result = inputValue;
                break;
        }

        return result;
    }
}