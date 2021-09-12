package com.example.utstream.adapters.ItemClick;

import android.widget.ImageView;

import com.example.utstream.models.Busqueda;
import com.example.utstream.models.Populate;

public interface BusquedaItemClickListener {
    void onMovieClick(Busqueda movie, ImageView movieImageView);
}
