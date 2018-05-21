package com.example.gmachine.mynewsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gmachine.mynewsapp.MessagePack.Message;
import com.example.gmachine.mynewsapp.R;

import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> {
    private List<Message> list;

    public MyCustomAdapter(List<Message> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = list.get(position);
        holder.txtHeader.setText(message.getHeaderMsg());
        holder.txtDesc.setText(message.getDescriptionMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMsg;
        TextView txtHeader;
        TextView txtDesc;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            imgMsg = (ImageView)itemView.findViewById(R.id.imgMsg);
            txtHeader = (TextView)itemView.findViewById(R.id.txtHeader);
            txtDesc = (TextView)itemView.findViewById(R.id.txtDesc);
            cv=(CardView) itemView.findViewById(R.id.cardView);


        }
    }
}
