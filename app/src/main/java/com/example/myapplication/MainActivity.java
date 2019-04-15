package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int contact_id = 0;
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private TextView name;
    private ImageView avatar;
    public static final int CONTACT_REQUEST = 1;
    public static final int SOUND_REQUEST = 2;

    public void changeCurrentContact() {
        name = (TextView) findViewById(R.id.contact_name_text);
        name.setText(contacts.get(contact_id).getName());

        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setImageResource(contacts.get(contact_id).getImage());
    }

    public int getRandomImage() throws IOException {
        TypedArray avatars = getResources().obtainTypedArray(R.array.avatars);
        int choise = (int) (Math.random() * avatars.length());
        return avatars.getResourceId(choise, R.raw.avatar_1);
    }

    protected int getRandomSound() {
        TypedArray sounds = getResources().obtainTypedArray(R.array.sounds);
        int choise = (int) (Math.random() * sounds.length());
        return sounds.getResourceId(choise, R.raw.mario);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = (ImageView) findViewById(R.id.avatar);
        Button contact_button = (Button) findViewById(R.id.contact_button);
        Button sound_button = (Button) findViewById(R.id.sound_button);

        try {
            contacts.add(new Contact("Jan Kowalski", getRandomSound(), getRandomImage()));
            contacts.add(new Contact("Andrzej Cichocki", getRandomSound(), getRandomImage()));
            contacts.add(new Contact("Janusz Tracz", getRandomSound(), getRandomImage()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        changeCurrentContact();

        contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectContact = new Intent(MainActivity.this, ContactActivity.class);
                selectContact.putExtra("contact_id", contact_id);
                selectContact.putExtra("contacts", contacts);
                startActivityForResult(selectContact, CONTACT_REQUEST);
            }
        });

        sound_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectContact = new Intent(MainActivity.this, SoundActivity.class);
                selectContact.putExtra("contact_id", contact_id);
                selectContact.putExtra("contacts", contacts);
                startActivityForResult(selectContact, SOUND_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (CONTACT_REQUEST) : {
                if (resultCode == Activity.RESULT_OK) {
                    contact_id = data.getIntExtra("contact_id", 0);
                    name.setText(((Contact) contacts.get(contact_id)).getName());
                    avatar.setImageResource(((Contact) contacts.get(contact_id)).getImage());
                }
                break;
            }
            case (SOUND_REQUEST) : {
                if (resultCode == Activity.RESULT_OK) {
                    int sound = data.getIntExtra("sound_id", 0);
                    TypedArray sounds = getResources().obtainTypedArray(R.array.sounds);
                    ((Contact) contacts.get(contact_id)).setSound(sounds.getResourceId(sound, -1));
                }
                break;
            }
        }
    }
}
