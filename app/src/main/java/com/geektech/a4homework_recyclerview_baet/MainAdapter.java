package com.geektech.a4homework_recyclerview_baet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public List<Title> list;
    ItemClickListener listener;

    public MainAdapter(List<Title> list) {
        this.list = list;
    }

    public void setElement(Title title, int position){
        list.set(position,title);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        Title title;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.txtView);
        }

        public void onBind(Title title) {
            this.title = title;
            textView.setText(title.name);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(title, getAdapterPosition());
            }
        }
    }

    public void setOnClick(ItemClickListener mListener) {
        this.listener = mListener;
    }

    public interface ItemClickListener {
        void onItemClick(Title title, int pos);
    }
}