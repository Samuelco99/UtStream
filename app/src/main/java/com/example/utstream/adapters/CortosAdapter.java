package com.example.utstream.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utstream.R;
import com.example.utstream.adapters.ItemClick.CortoItemClickListener;
import com.example.utstream.models.Corto;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CortosAdapter extends RecyclerView.Adapter<CortosAdapter.MyViewHolder> {

    Context context;
    List<Corto>mData;
    CortoItemClickListener cortoItemClickListener;

    public CortosAdapter(Context context,List<Corto> mData, CortoItemClickListener listener){
        this.context=context;
        this.mData=mData;
        cortoItemClickListener=listener;
    }


    @NonNull
    @Override
    public CortosAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CortosAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.TvTitle.setText(mData.get(i).getTitulo());
        Picasso.get().load(mData.get(i).getCaratula()).error(R.mipmap.ic_launcher_round).into(myViewHolder.ImgMovie);

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
                    cortoItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }

}
