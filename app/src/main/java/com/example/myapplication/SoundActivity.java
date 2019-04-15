package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class SoundActivity extends AppCompatActivity {

    private int sound_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        int contact_id = getIntent().getIntExtra("contact_id", 0);
        ArrayList contacts = (ArrayList<Contact>) getIntent().getSerializableExtra("contacts");

        Contact current_contact = (Contact) contacts.get(contact_id);
        int current_sound = current_contact.getSound();

        TypedArray sounds = getResources().obtainTypedArray(R.array.sounds);
        getResources().getStringArray(R.array.sounds);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> arraySpinner = new ArrayList<String>();

        for (int i=0; i<sounds.length(); i++) {
            arraySpinner.add("Sound " + i);
            if (((Integer) sounds.getResourceId(i, -1)).equals((Integer) current_sound)) {
                sound_id = i;
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(sound_id);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sound_id = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button ok_button = (Button) findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("sound_id", sound_id);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        Button cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();
            }
        });
    }
}
