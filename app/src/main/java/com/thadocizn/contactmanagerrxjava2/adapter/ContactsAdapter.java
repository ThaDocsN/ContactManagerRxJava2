package com.thadocizn.contactmanagerrxjava2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thadocizn.contactmanagerrxjava2.R;
import com.thadocizn.contactmanagerrxjava2.model.Contact;
import com.thadocizn.contactmanagerrxjava2.view.MainActivity;

import java.util.ArrayList;

/**
 * Created by charles on 10,June,2019
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Contact> contacts;
    private MainActivity activity;

    public ContactsAdapter(Context context, ArrayList<Contact> contacts, MainActivity activity) {
        this.context = context;
        this.contacts = contacts;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.MyViewHolder myViewHolder, final int position) {

        final Contact contact = contacts.get(position);
        myViewHolder.name.setText(contact.getName());
        myViewHolder.email.setText(contact.getEmail());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.addAndEditContacts(true, contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }
    }
}
