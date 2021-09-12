package com.example.utstream.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.utstream.fragmets.Cortos;
import com.example.utstream.fragmets.Libros;
import com.example.utstream.fragmets.Peliculas;
import com.example.utstream.fragmets.Series;

public class PagerControler extends FragmentPagerAdapter {

    int numoftabs;

    public PagerControler(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Peliculas();
            case 1:
                return new Series();
            case 2:
                return new Libros();
            case 3:
                return new Cortos();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
