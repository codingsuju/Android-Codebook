package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Contact> contacts;
    ItemClicked activity;
    public interface ItemClicked
    {
        void onItemSelected(int index);
    }
    public ContactsAdapter(Context context,List<Contact> list)
    {
        contacts=list;
        activity=(ItemClicked)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChar,tvName,tvMail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChar=itemView.findViewById(R.id.tvChar);
            tvName=itemView.findViewById(R.id.tvName);
            tvMail=itemView.findViewById(R.id.tvMail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemSelected(contacts.indexOf((Contact)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(contacts.get(position));
        holder.tvName.setText(contacts.get(position).getName());
        holder.tvMail.setText(contacts.get(position).getEmail());
        holder.tvChar.setText(contacts.get(position).getName().toString().toUpperCase().charAt(0)+"");
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
