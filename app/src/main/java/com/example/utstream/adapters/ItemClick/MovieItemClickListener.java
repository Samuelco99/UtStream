package com.example.utstream.adapters.ItemClick;

import android.widget.ImageView;

import com.example.utstream.models.Movie;

public interface MovieItemClickListener {
    void onMovieClick(Movie movie, ImageView movieImageView);
}
