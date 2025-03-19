package com.javaandroid.severalactivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.javaandroid.severalactivities.adapter.ContactAdapter;
import com.javaandroid.severalactivities.model.Contact;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts = new ArrayList<>(List.of(new Contact("N1", "Num1"),
            new Contact("N2", "Num2"),
            new Contact("N3", "Num3")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView contactsList = findViewById(R.id.contactList);
        ContactAdapter adapter = new ContactAdapter(this, R.layout.list_item, contacts);
        contactsList.setAdapter(adapter);

        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            boolean isEdit = arguments.getBoolean("flag");
            if(!isEdit) {
                Contact contact = (Contact) arguments.getSerializable(Contact.class.getSimpleName());
                adapter.add(contact);
                adapter.notifyDataSetChanged();
            }
            else {
                Contact contact = (Contact) arguments.getSerializable(Contact.class.getSimpleName());
                int position = arguments.getInt("pos");
                contacts.set(position, contact);
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void onAddButtonClick(View sender) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("flag", false);
        startActivity(intent);
    }
}