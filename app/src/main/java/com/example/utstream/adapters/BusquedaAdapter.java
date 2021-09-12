package com.example.utstream.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utstream.R;
import com.example.utstream.adapters.ItemClick.BusquedaItemClickListener;
import com.example.utstream.models.Busqueda;
import com.example.utstream.ui.BusquedaActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusquedaAdapter extends RecyclerView.Adapter<BusquedaAdapter.MyViewHolder> {

    Context context;
    List<Busqueda>mData;
    List<Busqueda>mDataOriginal;
    BusquedaItemClickListener busquedaItemClickListener;



    public BusquedaAdapter(Context context, List<Busqueda> mData, BusquedaItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        busquedaItemClickListener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        this.mDataOriginal=new ArrayList<>();
        mDataOriginal.addAll(this.mData);
        View view= LayoutInflater.from(context).inflate(R.layout.item_movie_busqueda,viewGroup,false);
        return new MyViewHolder(view);

    }


    public void filtTitulo(String txtBuscar){

        int cadena=txtBuscar.length();
        if(cadena==0){
            mData.clear();
            mData.addAll(mDataOriginal);


        }else {
            List<Busqueda> titulos=mData.stream().filter(i ->i.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            mData.clear();
            mData.addAll(titulos);

        }
        notifyDataSetChanged();
    }

    public void filtClasifi(String txtBuscar){
        int cadena=txtBuscar.length();
        if(cadena==0){
            mData.clear();
            mData.addAll(mDataOriginal);

        }else {
            List<Busqueda> clasificacion=mData.stream().filter(i ->i.getClasificacion().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            mData.clear();
            mData.addAll(clasificacion);
            /*if(mData.size()==0){
                mData.clear();
                mData.addAll(mDataOriginal);
            }*/

        }
        notifyDataSetChanged();
    }

    public void filtCategori(String txtBuscar){
        int cadena=txtBuscar.length();
        if(cadena==0){
            mData.clear();
            mData.addAll(mDataOriginal);

        }else {
            List<Busqueda> categoria=mData.stream().filter(i ->i.getGenero().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            mData.clear();
            mData.addAll(categoria);
            /*if(mData.size()==0){
                mData.clear();
                mData.addAll(mDataOriginal);
            }*/

        }
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.TvTitle.setText(mData.get(i).getTitulo());
        Picasso.get().load(mData.get(i).getCaratula()).error(R.mipmap.ic_launcher_round).into(myViewHolder.ImgMovie);
        myViewHolder.TvDescrip.setText(mData.get(i).getDescripcion());
        myViewHolder.TvClasif.setText("Clasificación: "+mData.get(i).getClasificacion());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle,TvDescrip,TvClasif;
        private ImageView ImgMovie;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ImgMovie=itemView.findViewById(R.id.item_movie_img);
            TvTitle=itemView.findViewById(R.id.item_movie_title);
            TvDescrip=itemView.findViewById(R.id.item_movie_desc);
            TvClasif=itemView.findViewById(R.id.item_movie_clas);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    busquedaItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}
