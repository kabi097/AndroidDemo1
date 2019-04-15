package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ContactActivity extends AppCompatActivity {

    private final int current_user_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        final RadioGroup contact_group = (RadioGroup) findViewById(R.id.contact_group);

        int contact_id = getIntent().getIntExtra("contact_id", 0);
        ArrayList contacts = (ArrayList<Contact>) getIntent().getSerializableExtra("contacts");


        for (int i=0; i<contacts.size(); i++) {
            Contact contact = (Contact) contacts.get(i);
            RadioButton new_contact_radio = new RadioButton(this);
            new_contact_radio.setText(contact.getName());
            contact_group.addView(new_contact_radio);
            if (i==contact_id) contact_group.check(new_contact_radio.getId());
        }

        Button ok_button = (Button) findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selected_button = (RadioButton) findViewById(contact_group.getCheckedRadioButtonId());

                int selected_index = contact_group.indexOfChild(selected_button);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("contact_id", selected_index);
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
