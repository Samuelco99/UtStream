package com.example.utstream.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utstream.R;
import com.example.utstream.adapters.ItemClick.CapituloItemClickListener;
import com.example.utstream.adapters.ItemClick.PopulateItemClickListener;
import com.example.utstream.models.Capitulo;
import com.example.utstream.models.Populate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CapituloAdapter extends RecyclerView.Adapter<CapituloAdapter.MyViewHolder> {

    Context context;
    List<Capitulo>mData;
    CapituloItemClickListener capituloItemClickListener;



    public CapituloAdapter(Context context, List<Capitulo> mData, CapituloItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        capituloItemClickListener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_capitulo,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.TvTitle.setText(mData.get(i).getTitulo());
        Picasso.get().load(mData.get(i).getCover()).error(R.drawable.ic_baseline_cancel_24).into(myViewHolder.ImgMovie);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle;
        private ImageView ImgMovie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ImgMovie=itemView.findViewById(R.id.item_movie_img);
            TvTitle=itemView.findViewById(R.id.item_movie_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    capituloItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}
