package com.example.utstream.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.utstream.R;
import com.example.utstream.adapters.BusquedaAdapter;
import com.example.utstream.adapters.ItemClick.BusquedaItemClickListener;
import com.example.utstream.models.Busqueda;
import com.example.utstream.models.Populate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BusquedaActivity extends AppCompatActivity implements BusquedaItemClickListener, SearchView.OnQueryTextListener {

    List<Busqueda>lstBusqueda=new ArrayList<>();
    private RecyclerView RvBusqueda;
    private SearchView TxtBuscar;
    private BusquedaAdapter busquedaAdapter;
    private Spinner SpCategoria,SpClasificacion;
    private Button btnX;
    FirebaseFirestore db;
    boolean x=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        getSupportActionBar().setTitle("Buscar");
        btnX=findViewById(R.id.BtnX);
        btnX.setVisibility(View.INVISIBLE);
        RvBusqueda = findViewById(R.id.RvBusqueda);
        TxtBuscar = findViewById(R.id.TxtBuscar);
        getContent();
        busquedaAdapter = new BusquedaAdapter(this, lstBusqueda, this);
        RvBusqueda.setAdapter(busquedaAdapter);
        RvBusqueda.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        TxtBuscar.setOnQueryTextListener(this);

        SpCategoria= findViewById(R.id.SpCategoria);
        String[] categorias = {"Categoria","Infantil","Animado","Accion","Ciencia Ficcion","Comedia","Drama","Fantasia","Musical","Suspenso","Terror"};
        SpCategoria.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, categorias));
        SpClasificacion= findViewById(R.id.SpClasificacion);
        String[] clasificacion = {"Clasificacion","A","B-15","C"};
        SpClasificacion.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, clasificacion));
        SpClasificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position!=0){
                    busquedaAdapter.filtClasifi((String) adapterView.getItemAtPosition(position));
                    btnX.setVisibility(View.VISIBLE);
                    SpCategoria.setEnabled(false);
                    x=true;
                    //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }else{
                    if(x){
                        finish(); startActivity(getIntent());
                        x=false;
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position!=0){
                    busquedaAdapter.filtCategori((String) adapterView.getItemAtPosition(position));
                    btnX.setVisibility(View.VISIBLE);
                    x=true;
                    SpClasificacion.setEnabled(false);
                    //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }else{
                    if(x){
                        finish(); startActivity(getIntent());
                        x=false;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });


    }

    public void cancelar(){
        finish(); startActivity(getIntent());
        x=false;
    }




    public void Fireba() {
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
    }

    public void getContent(){
        Fireba();
        lstBusqueda.add(new Busqueda("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fpm_c.jpg?alt=media&token=068fc93d-b407-44c4-9554-d0caab23ac6b",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fpm_co.webp?alt=media&token=cac4f09c-f979-41c9-b592-491bfbba0e97",
                "corto",
                "Paperman",
                "Trama. Ambientado en Nueva York en los años 1940, el cortometraje muestra a un hombre joven esperando el tren en una estación. ... Cuando el hombre intenta hablar con ella, la mujer ya se ha subido a un tren y se aleja de la estación. El hombre llega a su trabajo preocupado por no volver a ver a la mujer que conoció.",
                "https://firebasestorage.googleapis.com/v0/b/servidorcortos.appspot.com/o/Reimagination%20of%20Disney's%20short%20film's%20music.mp4?alt=media&token=d80b73f0-02a9-4f6d-b64a-a95aeb9b41df",
                "",
                "7 min",
                "John Kahrs",
                "infantil",
                "A",
                "2 de noviembre de 2012 ",
                "",
                "",
                "",
                "",
                ""));
        lstBusqueda.add(new Busqueda("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2F21bj_c.jpg?alt=media&token=6251aabf-fd9b-4499-9d4f-7203086bd10a",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2F21bj_co.webp?alt=media&token=c0f35bd5-ad12-4aab-9929-f790555b6cc1",
                "pelicula",
                "21 Blackjack",
                "Ben Campbell es un talentoso estudiante del Instituto Tecnológico de Massachusetts (Estados Unidos) que pasa por ciertos apuros económicos como para pagar sin problema la matrícula del centro. Al menos, hasta que halla la solución en los juegos de cartas, para los que resulta tener un don natural.",
                "https://firebasestorage.googleapis.com/v0/b/peliculas-6d897.appspot.com/o/Black%20Jack%2021.MP4?alt=media&token=73843a43-10a8-4d61-8d13-a036fa16278a",
                "",
                "2h 3m",
                "Robert Luketic",
                "Drama",
                "B-15",
                "28 de marzo de 2008",
                "",
                "",
                "",
                "",
                ""));
        lstBusqueda.add(new Busqueda("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fcdz_c.jpg?alt=media&token=8550992e-8dc7-4457-b148-f8940055ce75",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fcdz_co.jpg?alt=media&token=ac4241d2-77fb-4c54-b63e-437673df622c",
                "serie",
                "Los Caballeros del Zodiaco",
                "Según cuenta la leyenda, unos jóvenes guerreros poseedores de grandes habilidades son los principales responsables de proteger a Atenea, la diosa de la guerra. Años más tarde, la princesa Saori Kido, reencarnación de la Diosa, se encuentra en peligro y necesita de esa legendaria protección.\nCién jóvenes huérfanos son enviados por todo el mundo para seguir un duro entrenamiento con el propósito de convertise en caballeros sagrados. Una vez pasadas estas pruebas, reciben la armadura celestial, colocando bajo cada una de ellas unas constelación. Sin embargo, la lucha apenas acaba de comenzar....",
                "",
                "",
                "21 min",
                "Kōzō Morishita",
                "Accion",
                "B-15",
                "11 de octubre de 1986",
                "",
                "",
                "",
                "73",
                "ebYpZ9550dyBH3ylZrb7"));
        lstBusqueda.add(new Busqueda("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fit_c.jpg?alt=media&token=34dc7e38-4422-4cff-9810-8717181a1249",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fit_co.jpg?alt=media&token=928bb46e-516c-4a89-a16d-be9e4a647494",
                "libro",
                "It (Eso)",
                "It es una novela de terror publicada en 1986 por el escritor estadounidense Stephen King. Cuenta la historia de un grupo de siete niños que son aterrorizados por un malvado monstruo -al que llaman «Eso»- que es capaz de cambiar de forma, alimentándose del terror que produce en sus víctimas.",
                "https://firebasestorage.googleapis.com/v0/b/servidor3-748b1.appspot.com/o/LIBROS%2FLibro.It.(Eso).pdf?alt=media&token=cfcc9087-6d2c-41be-998d-303e238f4620",
                "",
                "",
                "",
                "Terror",
                "C",
                "15 de septiembre de 1986",
                "Stephen King",
                "Viking Press",
                "990",
                "",
                ""));
        lstBusqueda.add(new Busqueda("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fpn_c.jpg?alt=media&token=2e6d8d6a-e498-4d16-a918-e94027b3a1ec",
                "https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/covers%2Fpn_co.jpg?alt=media&token=e7459b15-1876-4759-abe3-395749711480",
                "corto",
                "Parcialmente nublado",
                "Gus, una nube gris solitaria e insegura, esculpe bebés peligrosos que serán entregados por la cigüeña Peck. Las creaciones de Gus se vuelven más complicadas y Peck tiene que encontrar una manera de manejar su carga y el mal temperamento de su amigo.",
                "https://firebasestorage.googleapis.com/v0/b/servidorcortos.appspot.com/o/parcialmente%20nublado.mp4?alt=media&token=86f6b7cf-f0c7-4033-a6ec-c1378298899c",
                "",
                "6 min",
                "Peter Sohn",
                "Animado",
                "A",
                "16 de mayo de 2009",
                "",
                "",
                "",
                "",
                ""));
        db.collection("Libro")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                lstBusqueda.add(new Busqueda(document.getString("caratula"),
                                        document.getString("cover"),
                                        document.getString("tipo"),
                                        document.getString("titulo"),
                                        document.getString("descripcion"),
                                        document.getString("link"),
                                        document.getString("IMDb"),
                                        document.getString("duracion"),
                                        document.getString("director"),
                                        document.getString("genero"),
                                        document.getString("clasificacion"),
                                        document.getString("fecha"),
                                        document.getString("autor"),
                                        document.getString("editorial"),
                                        document.getString("paginas"),
                                        document.getString("numerCap"),
                                        document.getId()
                                ));

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });

        db.collection("Series")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                if(document.getId().equals("ebYpZ9550dyBH3ylZrb7")){

                                }else {
                                    lstBusqueda.add(new Busqueda(document.getString("caratula"),
                                            document.getString("cover"),
                                            document.getString("tipo"),
                                            document.getString("titulo"),
                                            document.getString("descripcion"),
                                            document.getString("link"),
                                            document.getString("IMDb"),
                                            document.getString("duracion"),
                                            document.getString("director"),
                                            document.getString("genero"),
                                            document.getString("clasificacion"),
                                            document.getString("fecha"),
                                            document.getString("autor"),
                                            document.getString("editorial"),
                                            document.getString("paginas"),
                                            document.getString("numerCap"),
                                            document.getId()
                                    ));
                                }

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });
        db.collection("Peliculas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                lstBusqueda.add(new Busqueda(document.getString("caratula"),
                                        document.getString("cover"),
                                        document.getString("tipo"),
                                        document.getString("titulo"),
                                        document.getString("descripcion"),
                                        document.getString("link"),
                                        document.getString("IMDb"),
                                        document.getString("duracion"),
                                        document.getString("director"),
                                        document.getString("genero"),
                                        document.getString("clasificacion"),
                                        document.getString("fecha"),
                                        document.getString("autor"),
                                        document.getString("editorial"),
                                        document.getString("paginas"),
                                        document.getString("numerCap"),
                                        document.getId()
                                ));

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });

        db.collection("Cortos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                lstBusqueda.add(new Busqueda(document.getString("caratula"),
                                        document.getString("cover"),
                                        document.getString("tipo"),
                                        document.getString("titulo"),
                                        document.getString("sinopsis"),
                                        document.getString("link"),
                                        document.getString("IMDb"),
                                        document.getString("duracion"),
                                        document.getString("director"),
                                        document.getString("genero"),
                                        document.getString("clasificacion"),
                                        document.getString("fecha"),
                                        document.getString("autor"),
                                        document.getString("editorial"),
                                        document.getString("paginas"),
                                        document.getString("numerCap"),
                                        document.getId()
                                ));

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }
                });
    }


    @Override
    public void onMovieClick(Busqueda movie, ImageView movieImageView) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("imgURL",movie.getCaratula());
        intent.putExtra("titulo",movie.getTitulo());
        intent.putExtra("cover",movie.getCover());
        intent.putExtra("tipo",movie.getTipo());
        intent.putExtra("descripcion",movie.getDescripcion());
        intent.putExtra("link",movie.getLink());
        intent.putExtra("imdb",movie.getImdb());
        intent.putExtra("duracion",movie.getDuracion());
        intent.putExtra("director",movie.getDirector());
        intent.putExtra("genero",movie.getGenero());
        intent.putExtra("clasificacion",movie.getClasificacion());
        intent.putExtra("fecha",movie.getFecha());
        intent.putExtra("autor",movie.getAutor());
        intent.putExtra("editorial",movie.getEditorial());
        intent.putExtra("paginas",movie.getPaginas());
        intent.putExtra("numerCap",movie.getNumerCap());
        intent.putExtra("id",movie.getId().toString());

        //tambien creamos la animacion de cambio
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(BusquedaActivity.this,
                movieImageView,"sharedName");
        startActivity(intent,options.toBundle());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        SpCategoria.setEnabled(false);
        SpClasificacion.setEnabled(false);
        btnX.setVisibility(View.VISIBLE);
        busquedaAdapter.filtTitulo(s);
        x=true;
        if(s.equals("") && x){
            finish(); startActivity(getIntent());
            x=false;
        }
        return false;
    }
}