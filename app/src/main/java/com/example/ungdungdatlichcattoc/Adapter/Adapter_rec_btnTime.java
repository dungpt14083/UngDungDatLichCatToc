package com.example.ungdungdatlichcattoc.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdatlichcattoc.Interface.ItemClickListener;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.activity.DatlichActivity;
import com.example.ungdungdatlichcattoc.model.TimePickerStylelish;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Adapter_rec_btnTime extends RecyclerView.Adapter<Adapter_rec_btnTime.NoteVH> {
    private Integer selectedItemPosition = -1;
    private List<TimePickerStylelish> mNoteList;
    private ItemClickListener itemClickListener;
    String thistimenow;
    String strtimenow;
    public Adapter_rec_btnTime(List<TimePickerStylelish> noteList, ItemClickListener listener) {
        mNoteList = noteList;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rc_btntime, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
        holder.bindData(mNoteList.get(position).getHours());
        String time = mNoteList.get(position).getHours();
        String boder = mNoteList.get(position).getBlock().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
         strtimenow = simpleDateFormat.format(Calendar.getInstance().getTime());
         Date dateNow = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
//            if (mNoteList.get(position).getTime().after(timenow)) {
//                holder.btnTime.setEnabled(true);
//            } else {
//                holder.btnTime.setEnabled(false);
//                holder.btnTime.setBackgroundColor(Color.parseColor("#E49B83"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("TAG", "onBindViewHolder: " + time + "boler" + boder);
        DatlichActivity datlichActivity = new DatlichActivity();
        if (mNoteList.get(position).getBlock()) {
            holder.btnTime.setEnabled(true);
        }
        else {
            holder.btnTime.setEnabled(false);
            holder.btnTime.setBackgroundColor(Color.parseColor("#E49B83"));
        }
        if(mNoteList.get(position).getTime().before(dateNow)==true){
            holder.btnTime.setEnabled(false);
            holder.btnTime.setBackgroundColor(Color.parseColor("#E49B83"));
        }
        Log.d("timenow", "onBindViewHolder: "+dateNow.toString());
        Log.d("timeposition", "onBindViewHolder: "+mNoteList.get(position).getTime().toString());

        holder.btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItemPosition = position;
                datlichActivity.hour = time;
                itemClickListener.onClickItemTime(time);
                notifyDataSetChanged();
            }
        });
        if (selectedItemPosition == position)
            holder.btnTime.setBackgroundColor(Color.parseColor("#FFCA39"));
        else
            holder.btnTime.setBackgroundColor(Color.parseColor("#E7E7E7"));
    }

//    public void addNewNote(String note) {
//        if (!mNoteList.contains(note)) {
//            mNoteList.add(note);
//            notifyItemInserted(mNoteList.size());
//        }
//    }

    @Override
    public int getItemCount() {
        return mNoteList != null ? mNoteList.size() : 0;
    }

    class NoteVH extends RecyclerView.ViewHolder {

        private AppCompatButton btnTime;
        //private Button layoutitemtime;

        public NoteVH(View itemView) {
            super(itemView);
            btnTime = (AppCompatButton) itemView.findViewById(R.id.btnTime);

            //  layoutitemtime = itemView.findViewById(R.id.btnTime);
        }

        public void bindData(String note) {
            btnTime.setText(note);
        }
    }
}


