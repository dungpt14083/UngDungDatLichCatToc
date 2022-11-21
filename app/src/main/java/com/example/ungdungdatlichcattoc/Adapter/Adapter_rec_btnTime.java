package com.example.ungdungdatlichcattoc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdatlichcattoc.R;

import java.util.List;

public class Adapter_rec_btnTime extends RecyclerView.Adapter<Adapter_rec_btnTime.NoteVH> {

    private List<String> mNoteList;

    public Adapter_rec_btnTime(List<String> noteList) {
        mNoteList = noteList;
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

        public NoteVH(View itemView) {
            super(itemView);
            btnTime = (AppCompatButton) itemView.findViewById(R.id.btnTime);
        }

        public void bindData(String note) {
            btnTime.setText(note);
        }
    }
}

