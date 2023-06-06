package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewItem extends AppCompatActivity {

    private EditText name_input, phone_input, description_input, date_input, location_input;
    private AppCompatButton save_button, back_button;

    private String post_type = "";
    private RadioButton lostButton, foundButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        name_input = findViewById(R.id.name_input);
        phone_input = findViewById(R.id.phone_input);
        description_input = findViewById(R.id.description_input);
        date_input = findViewById(R.id.date_input);
        location_input = findViewById(R.id.location_input);
        save_button = findViewById(R.id.save_button);
        lostButton = findViewById(R.id.radio_lost);
        foundButton = findViewById(R.id.radio_found);
        back_button = findViewById(R.id.back_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_input.getText().toString();
                String phone = phone_input.getText().toString();
                String description = description_input.getText().toString();
                String date = date_input.getText().toString();
                String location = location_input.getText().toString();
                String status = post_type;

                resetFormErrors();

                if(validateInputs(name, phone, description, date, location)) {
                    databaseHelper.insertItem(new DatabaseItem(name, phone, description, date, location, status));
                    Toast.makeText(NewItem.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                    resetForm();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(NewItem.this, MainActivity.class);
                startActivity(mainMenu);
            }
        });
    }

    private void resetForm() {
        name_input.setText("");
        phone_input.setText("");
        description_input.setText("");
        date_input.setText("");
        location_input.setText("");
        this.post_type = "";
    }

    private boolean validateInputs(String name, String phone, String description, String date, String location) {
        boolean validated = true;

        if (name.equals("")) {
            name_input.setError("Please enter valid name");
            validated = false;
        }
        if(phone.equals("") ) {
            phone_input.setError("Please enter valid phone");
            validated = false;
        }
        if (description.equals("")) {
            description_input.setError("Please enter valid description");
            validated = false;
        }
        if (date.equals("")) {
            date_input.setError("Please enter valid date");
            validated = false;
        }
        if (location.equals("")) {
            location_input.setError("Please enter valid location");
            validated = false;
        }
        if (!(post_type.equals("lost") || post_type.equals("found"))) {
            lostButton.setError("Select");
            foundButton.setError("Select");
            validated = false;
        }

        return validated;
    }

    private void resetFormErrors() {
        name_input.setError(null);
        phone_input.setError(null);
        description_input.setError(null);
        date_input.setError(null);
        location_input.setError(null);
        lostButton.setError(null);
        foundButton.setError(null);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_lost:
                if (checked)
                    post_type = "lost";
                    break;
            case R.id.radio_found:
                if (checked)
                    post_type = "found";
                    break;
        }
    }
}