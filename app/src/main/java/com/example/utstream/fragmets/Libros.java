package com.example.utstream.fragmets;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.utstream.R;
import com.example.utstream.adapters.ItemClick.LibroItemClickListener;
import com.example.utstream.adapters.LibroAdapter;
import com.example.utstream.models.Corto;
import com.example.utstream.models.Libro;
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
 * Use the {@link Libros#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Libros extends Fragment implements LibroItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView RvLibros;
    List<Libro> lsLibros=new ArrayList<>();
    FirebaseFirestore db;

    public Libros() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Libros.
     */
    // TODO: Rename and change types and number of parameters
    public static Libros newInstance(String param1, String param2) {
        Libros fragment = new Libros();
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
        getLibroContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_libros, container, false);
        RvLibros=(RecyclerView)view.findViewById(R.id.Rv_Libros);
        LibroAdapter libroAdapter=new LibroAdapter(getActivity().getApplicationContext(),lsLibros,this);
        RvLibros.setAdapter(libroAdapter);
        RvLibros.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        return view;
    }

    public void Fireba() {
        FirebaseApp.initializeApp(getActivity().getApplicationContext());
        db = FirebaseFirestore.getInstance();
    }

    public void getLibroContent(){
        Fireba();
        lsLibros.add(new Libro("Stephen King",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fit_c.jpg?alt=media&token=34dc7e38-4422-4cff-9810-8717181a1249",
                "C",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fit_co.jpg?alt=media&token=928bb46e-516c-4a89-a16d-be9e4a647494",
                "It es una novela de terror publicada en 1986 por el escritor estadounidense Stephen King. Cuenta la historia de un grupo de siete niños que son aterrorizados por un malvado monstruo -al que llaman «Eso»- que es capaz de cambiar de forma, alimentándose del terror que produce en sus víctimas.",
                "Viking Press",
                "15 de septiembre de 1986",
                "https://firebasestorage.googleapis.com/v0/b/servidor3-748b1.appspot.com/o/LIBROS%2FLibro.It.(Eso).pdf?alt=media&token=cfcc9087-6d2c-41be-998d-303e238f4620",
                "Terror",
                "990",
                "libro",
                "It (Eso)"));
        db.collection("Libro")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                lsLibros.add(new Libro(document.getString("autor"),
                                        document.getString("caratula"),
                                        document.getString("clasificacion"),
                                        document.getString("cover"),
                                        document.getString("descripcion"),
                                        document.getString("editorial"),
                                        document.getString("fecha"),
                                        document.getString("link"),
                                        document.getString("genero"),
                                        document.getString("paginas"),
                                        document.getString("tipo"),
                                        document.getString("titulo")));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });

    }

    @Override
    public void onMovieClick(Libro libro, ImageView imageView) {
        Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra("titulo",libro.getTitulo());
        intent.putExtra("imgURL",libro.getCaratula());
        intent.putExtra("cover",libro.getCover());
        intent.putExtra("descripcion",libro.getSinopsis());
        intent.putExtra("clasificacion",libro.getClasificacion());
        intent.putExtra("fecha",libro.getFecha());
        intent.putExtra("genero",libro.getGenero());
        intent.putExtra("link",libro.getLink());
        intent.putExtra("tipo",libro.getTipo());
        intent.putExtra("paginas",libro.getPaginas());
        intent.putExtra("autor",libro.getAutor());
        intent.putExtra("editorial",libro.getEditorial());

        //tambien creamos la animacion de cambio
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(), imageView,"sharedName");
        startActivity(intent,options.toBundle());

    }
}