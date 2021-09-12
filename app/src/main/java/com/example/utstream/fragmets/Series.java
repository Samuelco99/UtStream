package com.example.utstream.fragmets;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utstream.R;
import com.example.utstream.adapters.ItemClick.SerieItemClickListener;
import com.example.utstream.adapters.SerieAdapter;
import com.example.utstream.models.Libro;
import com.example.utstream.models.Serie;
import com.example.utstream.ui.MovieDetailActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Series#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Series extends Fragment implements SerieItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView RvSeries;
    List<Serie> lsSeries=new ArrayList<>();
    FirebaseFirestore db;

    public Series() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Series.
     */
    // TODO: Rename and change types and number of parameters
    public static Series newInstance(String param1, String param2) {
        Series fragment = new Series();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getSeriesContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_series, container, false);
        RvSeries=view.findViewById(R.id.Rv_Serie);
        SerieAdapter serieAdapter=new SerieAdapter(getActivity().getApplicationContext(),lsSeries,this);
        RvSeries.setAdapter(serieAdapter);
        RvSeries.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        return view;
    }

    public void Fireba() {
        FirebaseApp.initializeApp(getActivity().getApplicationContext());
        db = FirebaseFirestore.getInstance();
    }

    public void getSeriesContent(){
        Fireba();
        lsSeries.add(new Serie("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fcdz_c.jpg?alt=media&token=8550992e-8dc7-4457-b148-f8940055ce75",
                "B-15",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fcdz_co.jpg?alt=media&token=ac4241d2-77fb-4c54-b63e-437673df622c",
                "Según cuenta la leyenda, unos jóvenes guerreros poseedores de grandes habilidades son los principales responsables de proteger a Atenea, la diosa de la guerra. Años más tarde, la princesa Saori Kido, reencarnación de la Diosa, se encuentra en peligro y necesita de esa legendaria protección.\n" +
                        "\n" +
                        "Cién jóvenes huérfanos son enviados por todo el mundo para seguir un duro entrenamiento con el propósito de convertise en caballeros sagrados. Una vez pasadas estas pruebas, reciben la armadura celestial, colocando bajo cada una de ellas unas constelación. Sin embargo, la lucha apenas acaba de comenzar....\n",
                "Kōzō Morishita",
                "21 min",
                "11 de octubre de 1986",
                "Accion",
                "73",
                "serie",
                "Los Caballeros del Zodiaco",
                "ebYpZ9550dyBH3ylZrb7"));
        db.collection("Series")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("series", document.getId() + " => " + document.getData());
                                if(document.getId().equals("ebYpZ9550dyBH3ylZrb7")) {
                                }else{
                                    lsSeries.add(new Serie(document.getString("caratula"),
                                            document.getString("clasificacion"),
                                            document.getString("cover"),
                                            document.getString("descripcion"),
                                            document.getString("director"),
                                            document.getString("duracion"),
                                            document.getString("fecha"),
                                            document.getString("genero"),
                                            document.getString("numerCap"),
                                            document.getString("tipo"),
                                            document.getString("titulo"),
                                            document.getId()));
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });

    }

    @Override
    public void onMovieClick(Serie serie, ImageView imageView) {
        //aqui mandamos la informacion de la pelicula a la siguiente ventana


        Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra("titulo",serie.getTitulo());
        intent.putExtra("imgURL",serie.getCaratula());
        intent.putExtra("cover",serie.getCover());
        intent.putExtra("descripcion",serie.getSinopsis());
        intent.putExtra("clasificacion",serie.getClasificacion());
        intent.putExtra("director",serie.getDirector());
        intent.putExtra("duracion",serie.getDuracion());
        intent.putExtra("fecha",serie.getFecha());
        intent.putExtra("genero",serie.getGenero());
        intent.putExtra("tipo",serie.getTipo());
        intent.putExtra("numerCap",serie.getNumerCap());
        intent.putExtra("id",serie.getId().toString());

        //Toast.makeText(getContext(),"Pelicula:"+serie.getId().toString(),Toast.LENGTH_LONG).show();

        //tambien creamos la animacion de cambio
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(), imageView,"sharedName");
        startActivity(intent,options.toBundle());
    }
}