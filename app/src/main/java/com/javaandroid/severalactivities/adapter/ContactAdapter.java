package com.javaandroid.severalactivities.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.javaandroid.severalactivities.MainActivity;
import com.javaandroid.severalactivities.R;
import com.javaandroid.severalactivities.SecondActivity;
import com.javaandroid.severalactivities.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater inflater;
    private int layout;
    private List<Contact> contacts;
    public ContactAdapter(Context context, int resource, List<Contact> contacts) {
        super(context, resource, contacts);
        this.contacts = contacts;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        Contact contact = contacts.get(position);

        viewHolder.nameView.setText(contact.getName());
        viewHolder.numberView.setText(contact.getNumber());

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = contacts.get(position);
                Context context = getContext();
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra(Contact.class.getSimpleName(), contact);
                intent.putExtra("pos", position);
                intent.putExtra("flag", true);
                context.startActivity(intent);
            }
        });

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacts.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    private static class ViewHolder {

        final Button editButton, deleteButton;
        final TextView nameView, numberView;
        ViewHolder(View view){
            nameView = view.findViewById(R.id.nameTextView);
            numberView = view.findViewById(R.id.numberTextView);
            editButton = view.findViewById(R.id.editButton);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }
}
