package com.example.utstream.fragmets;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utstream.R;
import com.example.utstream.adapters.MovieAdapter;
import com.example.utstream.adapters.ItemClick.MovieItemClickListener;
import com.example.utstream.models.Movie;
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
 * Use the {@link Peliculas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Peliculas extends Fragment implements MovieItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView RvPelis;
    List<Movie> lsMovies=new ArrayList<>();
    FirebaseFirestore db;

    public Peliculas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Peliculas.
     */
    // TODO: Rename and change types and number of parameters
    public static Peliculas newInstance(String param1, String param2) {
        Peliculas fragment = new Peliculas();
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
        getMovieContent();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View view=inflater.inflate(R.layout.fragment_peliculas, container, false);
        RvPelis=(RecyclerView)view.findViewById(R.id.Rv_movies2);

        MovieAdapter movieAdapter=new MovieAdapter(getActivity().getApplicationContext(),lsMovies,this);
        RvPelis.setAdapter(movieAdapter);
        RvPelis.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        return view;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        //aqui mandamos la informacion de la pelicula a la siguiente ventana


        Intent intent = new Intent(getActivity().getApplicationContext(),MovieDetailActivity.class);
        intent.putExtra("titulo",movie.getTitulo());
        intent.putExtra("imgURL",movie.getCaratula());
        intent.putExtra("cover",movie.getCover());
        intent.putExtra("imdb",movie.getImdb());
        intent.putExtra("descripcion",movie.getSinopsis());
        intent.putExtra("duracion",movie.getDuracion());
        intent.putExtra("director",movie.getDirector());
        intent.putExtra("genero",movie.getGenero());
        intent.putExtra("clasificacion",movie.getClasificacion());
        intent.putExtra("tipo",movie.getTipo());
        intent.putExtra("clasificacion",movie.getClasificacion());
        intent.putExtra("fecha",movie.getFecha());
        intent.putExtra("link",movie.getLink());


        //tambien creamos la animacion de cambio
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView,"sharedName");
        startActivity(intent,options.toBundle());
    }

    public void Fireba() {
        FirebaseApp.initializeApp(getActivity().getApplicationContext());
        db = FirebaseFirestore.getInstance();
    }

    public void getMovieContent(){
        Fireba();
        lsMovies.add(new Movie("6.8/10",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2F21bj_c.jpg?alt=media&token=6251aabf-fd9b-4499-9d4f-7203086bd10a",
                "B-15",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2F21bj_co.webp?alt=media&token=c0f35bd5-ad12-4aab-9929-f790555b6cc1",
                "Ben Campbell es un talentoso estudiante del Instituto Tecnológico de Massachusetts (Estados Unidos) que pasa por ciertos apuros económicos como para pagar sin problema la matrícula del centro. Al menos, hasta que halla la solución en los juegos de cartas, para los que resulta tener un don natural."
                ,"Robert Luketic"
                ,"2h 3m"
                ,"28 de marzo de 2008"
                ,"Drama"
                ,"https://firebasestorage.googleapis.com/v0/b/peliculas-6d897.appspot.com/o/Black%20Jack%2021.MP4?alt=media&token=73843a43-10a8-4d61-8d13-a036fa16278a"
                ,"pelicula"
                ,"21 Blackjack"));
        db.collection("Peliculas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                lsMovies.add(new Movie(document.getString("IMDb"),
                                        document.getString("caratula"),
                                        document.getString("clasificacion"),
                                        document.getString("cover"),
                                        document.getString("descripcion"),
                                        document.getString("director"),
                                        document.getString("duracion"),
                                        document.getString("fecha"),
                                        document.getString("genero"),
                                        document.getString("link"),
                                        document.getString("tipo"),
                                        document.getString("titulo")));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });

    }
}