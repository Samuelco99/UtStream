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
import com.example.utstream.adapters.ItemClick.CortoItemClickListener;
import com.example.utstream.adapters.CortosAdapter;
import com.example.utstream.models.Corto;
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
 * Use the {@link Cortos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cortos extends Fragment implements CortoItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView RvCortos;
    List<Corto> lsCortos=new ArrayList<>();
    FirebaseFirestore db;

    public Cortos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cortos.
     */
    // TODO: Rename and change types and number of parameters
    public static Cortos newInstance(String param1, String param2) {
        Cortos fragment = new Cortos();
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
        getCortoContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cortos, container, false);
        RvCortos=(RecyclerView)view.findViewById(R.id.Rv_cortos);
        CortosAdapter cortosAdapter=new CortosAdapter(getActivity().getApplicationContext(),lsCortos,this);
        RvCortos.setAdapter(cortosAdapter);
        RvCortos.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        return view;
    }

    public void Fireba() {
        FirebaseApp.initializeApp(getActivity().getApplicationContext());
        db = FirebaseFirestore.getInstance();
    }

    public void getCortoContent(){
        Fireba();
        lsCortos.add(new Corto("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fpn_c.jpg?alt=media&token=2e6d8d6a-e498-4d16-a918-e94027b3a1ec",
                "A",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fpn_co.jpg?alt=media&token=e7459b15-1876-4759-abe3-395749711480",
                "Peter Sohn",
                "6 min",
                "16 de mayo de 2009",
                "Animado",
                "https://firebasestorage.googleapis.com/v0/b/servidorcortos.appspot.com/o/parcialmente%20nublado.mp4?alt=media&token=86f6b7cf-f0c7-4033-a6ec-c1378298899c",
                "Gus, una nube gris solitaria e insegura, esculpe bebés peligrosos que serán entregados por la cigüeña Peck. Las creaciones de Gus se vuelven más complicadas y Peck tiene que encontrar una manera de manejar su carga y el mal temperamento de su amigo.",
                "corto",
                "Parcialmente nublado"));
        db.collection("Cortos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                lsCortos.add(new Corto(document.getString("caratula"),
                                        document.getString("clasificacion"),
                                        document.getString("cover"),
                                        document.getString("director"),
                                        document.getString("duracion"),
                                        document.getString("fecha"),
                                        document.getString("genero"),
                                        document.getString("link"),
                                        document.getString("sinopsis"),
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
    public void onMovieClick(Corto corto, ImageView cortoImageView) {
        Intent intent = new Intent(getActivity().getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra("titulo",corto.getTitulo());
        intent.putExtra("imgURL",corto.getCaratula());
        intent.putExtra("cover",corto.getCover());
        intent.putExtra("descripcion",corto.getSinopsis());
        intent.putExtra("clasificacion",corto.getClasificacion());
        intent.putExtra("director",corto.getDirector());
        intent.putExtra("duracion",corto.getDuracion());
        intent.putExtra("fecha",corto.getFecha());
        intent.putExtra("genero",corto.getGenero());
        intent.putExtra("tipo",corto.getTipo());
        intent.putExtra("link",corto.getLink());


        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(), cortoImageView,"sharedName");
        startActivity(intent,options.toBundle());

        //Toast.makeText(getActivity(),"Corto:"+corto.getTitulo(),Toast.LENGTH_LONG).show();
    }
}