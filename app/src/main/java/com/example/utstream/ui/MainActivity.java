package com.example.utstream.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.utstream.R;
import com.example.utstream.adapters.PagerControler;
import com.example.utstream.adapters.PopulateAdapter;
import com.example.utstream.adapters.ItemClick.PopulateItemClickListener;
import com.example.utstream.adapters.SliderPagerAdapter;
import com.example.utstream.models.Populate;
import com.example.utstream.models.slide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements PopulateItemClickListener {

    public List<slide> lstSlides;

    private ViewPager sliderpager,tabPager;
    private TabLayout indicator,categoria;
    private RecyclerView MoviesRV;
    private TabItem tabP,tabS,tabL;
    private FloatingActionButton BtnBuscar;
    PagerControler pagerControler;
    List<Populate> lsPopulate=new ArrayList<>();
    FirebaseFirestore db;
    boolean x=true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //relacionamos variables con el componente mediante id
        BtnBuscar=findViewById(R.id.BtnBuscar);
        sliderpager=findViewById(R.id.slider_pager);
        indicator=findViewById(R.id.indicator);
        MoviesRV=findViewById(R.id.Rv_movies);
        tabPager=findViewById(R.id.viewPagerTab);
        categoria=findViewById(R.id.tabCategoria);
        tabP=findViewById(R.id.tabpeliculas);
        tabL=findViewById(R.id.tabdocs);
        tabS=findViewById(R.id.tabseries);


        BtnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });



        //aqui agregamos las imagenes del slider

        lstSlides=new ArrayList<>();
        lstSlides.add(new slide("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/fondo.png?alt=media&token=7f992f4f-cc74-408f-a574-d7262021b8e1"));
        lstSlides.add(new slide("https://sm.ign.com/ign_latam/cover/l/loki-the-s/loki-the-series_r24j.jpg"));
        lstSlides.add(new slide("https://pelispedia.io/storage/8544/YLCfGdnyoocU8Uv3pAtytSQRFsLNP0Ieb8xNvbX4.jpg"));
        lstSlides.add(new slide("https://clientes.dibox.com.ar/img/peli_rect_3036.jpg"));
        lstSlides.add(new slide("https://latinoedge.files.wordpress.com/2021/04/f9-1.jpg?w=1920&h=768&crop=1"));
        lstSlides.add(new slide("https://i.pinimg.com/originals/8e/05/e1/8e05e166cc2a9488c8c046e9735be786.jpg"));

        SliderPagerAdapter adapter=new SliderPagerAdapter(this,lstSlides);
        sliderpager.setAdapter(adapter);

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);

        //iniciamos los datos del contenido
        getPopulateContent();
        PopulateAdapter populateAdapter=new PopulateAdapter(this,lsPopulate,this);
        MoviesRV.setAdapter(populateAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));





        //funciones del tablayout
        pagerControler = new PagerControler(getSupportFragmentManager(),categoria.getTabCount());
        tabPager.setAdapter(pagerControler);
        categoria.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pagerControler.notifyDataSetChanged();
                }
                if(tab.getPosition()==1){
                    pagerControler.notifyDataSetChanged();
                }
                if(tab.getPosition()==2){
                    pagerControler.notifyDataSetChanged();
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(categoria));
    }

    public void Fireba() {
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
    }

    public void getPopulateContent(){
        Fireba();
        lsPopulate.add(new Populate("https://firebasestorage.googleapis.com/v0/b/utstream-8e2ae.appspot.com/o/caratulas%2Fpm_c.jpg?alt=media&token=068fc93d-b407-44c4-9554-d0caab23ac6b",
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
        db.collection("Popular")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Datos", document.getId() + " => " + document.getData());
                                    lsPopulate.add(new Populate(document.getString("caratula"),
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
        
    }


    public void buscar(){
        Intent intent = new Intent(this, BusquedaActivity.class);
        startActivity(intent);
    }




    @Override
    public void onMovieClick(Populate movie, ImageView movieImageView) {
        //aqui mandamos la informacion de la pelicula a la siguiente ventana


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
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

        //Toast.makeText(this,"Pelicula:"+movie.getTitulo(),Toast.LENGTH_LONG).show();

    }

    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpager.getCurrentItem()<lstSlides.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }else{
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}