package com.example.ungdungdatlichcattoc.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ungdungdatlichcattoc.UI.SliderFragment;
import com.example.ungdungdatlichcattoc.model.PhotoDes;

import java.util.List;

public class SliderAdapter extends FragmentStateAdapter {
    private List<PhotoDes> mlistphoto;
    public SliderAdapter(@NonNull FragmentActivity fragmentActivity,List<PhotoDes> mlistphoto) {

        super(fragmentActivity);
        this.mlistphoto =mlistphoto;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PhotoDes photoDes = mlistphoto.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("objPhoto",photoDes);
        SliderFragment sliderFragment = new SliderFragment();
        sliderFragment.setArguments(bundle);
        return sliderFragment;
    }

    @Override
    public int getItemCount() {
        if(mlistphoto!=null){
            return mlistphoto.size();
        }
        return 0;
    }
}
