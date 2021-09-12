package com.example.utstream.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.utstream.R;
import com.example.utstream.models.Capitulo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnail,MovieCover;
    private String link;
    private String tipo;
    private String titulo;
    private String id;
    private String imageCoverId;
    private TextView Tv1,Tv2,Tv3,Tv4,Tv5,Tv6,Tv7,Tv8,Tv9,Tv10,Tv11,Tv12,Tv13,Tv14,Tv15,Tv16;
    private FloatingActionButton play_fab;
    FirebaseFirestore db;
    List<String> mData=new ArrayList<String>();
    List<Capitulo> lstCapitulos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniViws();

    }

    void iniViws(){
        //relacionamos las variables con id
        play_fab=findViewById(R.id.play_fab);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ver();
            }
        });
        MovieThumbnail=findViewById(R.id.detail_img);
        MovieCover=findViewById(R.id.detail_movie_cover);
        Tv1=findViewById(R.id.Tv1);
        Tv2=findViewById(R.id.Tv2);
        Tv3=findViewById(R.id.Tv3);
        Tv4=findViewById(R.id.Tv4);
        Tv5=findViewById(R.id.Tv5);
        Tv6=findViewById(R.id.Tv6);
        Tv7=findViewById(R.id.Tv7);
        Tv8=findViewById(R.id.Tv8);
        Tv9=findViewById(R.id.Tv9);
        Tv10=findViewById(R.id.Tv10);
        Tv11=findViewById(R.id.Tv11);
        Tv12=findViewById(R.id.Tv12);
        Tv13=findViewById(R.id.Tv13);
        Tv14=findViewById(R.id.Tv14);
        Tv15=findViewById(R.id.Tv15);
        Tv16=findViewById(R.id.Tv16);

        Tv2.setVisibility(View.VISIBLE);
        Tv3.setVisibility(View.VISIBLE);
        Tv12.setVisibility(View.VISIBLE);
        Tv13.setVisibility(View.VISIBLE);
        Tv15.setVisibility(View.VISIBLE);
        Tv16.setVisibility(View.VISIBLE);


        //traemos los datos Enviados

        String imageResourceId=getIntent().getExtras().getString("imgURL");
        imageCoverId=getIntent().getExtras().getString("cover");
        tipo=getIntent().getExtras().getString("tipo");
        titulo=getIntent().getExtras().getString("titulo");
        String desc=getIntent().getExtras().getString("descripcion");
        link=getIntent().getExtras().getString("link");
        String imdb=getIntent().getExtras().getString("imdb");
        String duracion=getIntent().getExtras().getString("duracion");
        String director=getIntent().getExtras().getString("director");
        String genero=getIntent().getExtras().getString("genero");
        String clasificacion=getIntent().getExtras().getString("clasificacion");
        String fecha=getIntent().getExtras().getString("fecha");
        String autor=getIntent().getExtras().getString("autor");
        String editorial=getIntent().getExtras().getString("editorial");
        String paginas=getIntent().getExtras().getString("paginas");
        String numerCap=getIntent().getExtras().getString("numerCap");
        id=getIntent().getExtras().getString("id");

        //cambiamos el titulo de la pantalla
        getSupportActionBar().setTitle(titulo);

        Picasso.get().load(imageResourceId).into(MovieThumbnail);
        Picasso.get().load(imageCoverId).into(MovieCover);
        Tv1.setText(titulo);
        Tv14.setText(desc);

        if(tipo.equals("pelicula")){

            //mandamos los datos a los componentes

            Tv3.setText(imdb);
            Tv4.setText("Clasificacion:");
            Tv5.setText(clasificacion);
            Tv6.setText("Duracion:");
            Tv7.setText(duracion);
            Tv8.setText("Director:");
            Tv9.setText(director);
            Tv10.setText("Genero:");
            Tv11.setText(genero);
            Tv12.setText("Fecha:");
            Tv13.setText(fecha);
            Tv15.setVisibility(View.INVISIBLE);
            Tv16.setVisibility(View.INVISIBLE);




        }else if(tipo.equals("serie")){
            Tv2.setVisibility(View.INVISIBLE);
            Tv3.setVisibility(View.INVISIBLE);
            Tv3.setText(imdb);
            Tv4.setText("Clasificacion:");
            Tv5.setText(clasificacion);
            Tv6.setText("Duracion:");
            Tv7.setText(duracion);
            Tv8.setText("Director:");
            Tv9.setText(director);
            Tv10.setText("Genero:");
            Tv11.setText(genero);
            Tv12.setText("Fecha:");
            Tv13.setText(fecha);
            Tv15.setText("N° de Capitulos:");
            Tv16.setText(numerCap);

        }else if(tipo.equals("libro")){
            Tv2.setVisibility(View.INVISIBLE);
            Tv3.setVisibility(View.INVISIBLE);
            Tv4.setText("Autor:");
            Tv5.setText(autor);
            Tv6.setText("# de Paginas:");
            Tv7.setText(paginas);
            Tv8.setText("Editorial:");
            Tv9.setText(editorial);
            Tv10.setText("Genero:");
            Tv11.setText(genero);
            Tv12.setText("Fecha:");
            Tv13.setText(fecha);
            play_fab.setImageResource(R.drawable.ic_baseline_menu_book_24);
            Tv15.setText("Clasificacion:");
            Tv16.setText(clasificacion);
        }else if(tipo.equals("corto")){
            Tv2.setVisibility(View.INVISIBLE);
            Tv3.setVisibility(View.INVISIBLE);
            Tv4.setText("Clasificacion:");
            Tv5.setText(clasificacion);
            Tv6.setText("Director:");
            Tv7.setText(director);
            Tv8.setText("Duracion:");
            Tv9.setText(duracion);
            Tv10.setText("Fecha");
            Tv11.setText(fecha);
            Tv12.setVisibility(View.INVISIBLE);
            Tv13.setVisibility(View.INVISIBLE);
            Tv15.setVisibility(View.INVISIBLE);
            Tv16.setVisibility(View.INVISIBLE);
        }

        //animacion
        MovieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));



    }
    void ver(){

        if(tipo.equals("libro")){
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(link), "application/pdf");
            startActivity(intent);
        }else if(tipo.equals("serie")){
            Intent intent=new Intent(this,CapitulosActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("lista",lstCapitulos.toString());
            intent.putExtra("titulo",titulo);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this,show.class);
            intent.putExtra("link",link);
            intent.putExtra("nombre",titulo);
            intent.putExtra("cover",imageCoverId);
            startActivity(intent);
        }

    }


    public void Fireba() {
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
    }

    public void getCapitulos(){
        Fireba();
        DocumentReference docRef = db.collection("Series").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        int p = 0;
                        HashMap<String,Object> lc = (HashMap<String,Object>)document.get("capitulos");
                        for(String s:lc.keySet()){
                            HashMap<String,Object> e = (HashMap<String,Object>) lc.get(s);
                            for(String w:e.keySet()){
                                String r = (String) e.get(w);
                                if (0 == p) {
                                    mData.add(r);
                                }
                                if (1 == p) {
                                    mData.add(r);
                                }
                                if (2 == p) {
                                    mData.add(r);
                                }
                                p++;
                            }

                            p=0;
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                    int j=1;
                    String C1="",C2="",C3="";
                    for (int i = 0; i <= mData.size() - 1; i++) {
                        if(j==1){
                            C1=mData.get(i);
                            j++;
                        }else if(j==2){
                            C2=mData.get(i);
                            j++;
                        }else if(j==3){
                            C3=mData.get(i);
                            System.out.println(C1+" "+C2+" "+C3);

                            lstCapitulos.add(new Capitulo(C1,C2,C3));
                            C1="";
                            C2="";
                            C3="";
                            j=1;
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }
}