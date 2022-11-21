package com.example.ungdungdatlichcattoc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdatlichcattoc.Interface.ItemClickListener;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;

import java.util.List;

public class Adapter_rec_btnTime extends RecyclerView.Adapter<Adapter_rec_btnTime.NoteVH> {

    private List<String> mNoteList;
    private ItemClickListener itemClickListener;

    public Adapter_rec_btnTime(List<String> noteList, ItemClickListener listener) {
        mNoteList = noteList;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rc_btntime, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
        holder.bindData(mNoteList.get(position));
        String time = mNoteList.get(position);
        DatlichActivity datlichActivity = new DatlichActivity();

        holder.layoutitemtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datlichActivity.hour =time;
                itemClickListener.onClickItemTime(time);
            }
        });
    }

    public void addNewNote(String note) {
        if (!mNoteList.contains(note)) {
            mNoteList.add(note);
            notifyItemInserted(mNoteList.size());
        }
    }

    @Override
    public int getItemCount() {
        return mNoteList != null ? mNoteList.size() : 0;
    }

    class NoteVH extends RecyclerView.ViewHolder {

        private AppCompatButton btnTime;
        private LinearLayout layoutitemtime;

        public NoteVH(View itemView) {
            super(itemView);
            btnTime = (AppCompatButton) itemView.findViewById(R.id.btnTime);

            layoutitemtime = itemView.findViewById(R.id.layoutitemtime);
        }

        public void bindData(String note) {
            btnTime.setText(note);
        }
    }
}

