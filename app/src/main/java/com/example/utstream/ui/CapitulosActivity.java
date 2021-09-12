package com.example.utstream.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.utstream.R;
import com.example.utstream.adapters.CapituloAdapter;
import com.example.utstream.adapters.ItemClick.CapituloItemClickListener;
import com.example.utstream.models.Capitulo;
import com.example.utstream.models.Populate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class CapitulosActivity extends AppCompatActivity implements CapituloItemClickListener {

    private String id;
    FirebaseFirestore db;
    List<String> mData=new ArrayList<String>();
    List<Capitulo> lstCapitulos=new ArrayList<>();
    private RecyclerView RvCapitulos;
    private FloatingActionButton btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulos);
        mData.clear();
        lstCapitulos.clear();
        id=getIntent().getExtras().getString("id");
        String nombre=getIntent().getExtras().getString("titulo");
        getSupportActionBar().setTitle(nombre);
        RvCapitulos=findViewById(R.id.Rv_Capitulos);
        getCapitulos();
        lstCapitulos.add(new Capitulo("https://firebasestorage.googleapis.com/v0/b/servidorcortos.appspot.com/o/lista.png?alt=media&token=27b8bbad-2013-465b-9e69-9ae57a6f9101","",""));
        CapituloAdapter capituloAdapter=new CapituloAdapter(this,lstCapitulos,this);
        RvCapitulos.setAdapter(capituloAdapter);
        RvCapitulos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


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
                        int p = 0,c=0;
                        String C1="",C2="",C3="";
                        HashMap<String,Object> lc = (HashMap<String,Object>)document.get("capitulos");
                        for(String s:lc.keySet()){
                            HashMap<String,Object> e = (HashMap<String,Object>) lc.get(s);
                            for(String w:e.keySet()){
                                String r = (String) e.get(w);
                                if (0 == p) {
                                    mData.add(r);
                                    C1=r;
                                }
                                if (1 == p) {
                                    mData.add(r);
                                    C2=r;
                                }
                                if (2 == p) {
                                    mData.add(r);
                                    C3=r;
                                    c++;
                                }
                                p++;
                            }
                            System.out.println(C1+" "+C2+" "+C3);
                            lstCapitulos.add(new Capitulo(C1,C2,"Capitulo "+c+": "+C3));

                            p=0;
                        }

                    } else {
                        Log.d(TAG, "No such document");
                    }

                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }

            }
        });


    }

    public void Recargar(){
        finish(); startActivity(getIntent());
    }


    @Override
    public void onMovieClick(Capitulo capitulo, ImageView imageView) {
        if(capitulo.getLink().equals("")) {
        }
        else{
            Intent intent = new Intent(this, show.class);
            intent.putExtra("link", capitulo.getLink());
            startActivity(intent);
        }
    }
}