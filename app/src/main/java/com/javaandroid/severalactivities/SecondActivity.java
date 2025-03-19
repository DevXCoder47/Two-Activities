package com.javaandroid.severalactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.javaandroid.severalactivities.model.Contact;

public class SecondActivity extends AppCompatActivity {


    private boolean isEdit;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtv1 = findViewById(R.id.editTextText);
        TextView txtv2 = findViewById(R.id.editTextText2);
        Bundle arguments = getIntent().getExtras();
        isEdit = arguments.getBoolean("flag");
        if(isEdit) {
            position = arguments.getInt("pos");
            Contact contact = (Contact) arguments.getSerializable(Contact.class.getSimpleName());
            txtv1.setText(contact.getName());
            txtv2.setText(contact.getNumber());
        }
    }
    public void onSubmitButtonClick(View sender) {
        Intent intent = new Intent(this, MainActivity.class);
        TextView txtv1 = findViewById(R.id.editTextText);
        TextView txtv2 = findViewById(R.id.editTextText2);
        intent.putExtra(Contact.class.getSimpleName(), new Contact(txtv1.getText().toString(), txtv2.getText().toString()));
        intent.putExtra("flag", isEdit);
        if(isEdit)
            intent.putExtra("pos", position);
        startActivity(intent);
    }
}