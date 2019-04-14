package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        int contact_id = getIntent().getIntExtra("contact_id", 0);
        ArrayList contacts = (ArrayList<Contact>) getIntent().getSerializableExtra("contacts");

        final RadioGroup contact_group = (RadioGroup) findViewById(R.id.contact_group);

        Contact contact;
        for (int i=0; i<contacts.size(); i++) {
            contact = (Contact) contacts.get(i);
            RadioButton new_contact_radio = new RadioButton(this);
            new_contact_radio.setText(contact.getName());
            contact_group.addView(new_contact_radio);
        }
    }
}
