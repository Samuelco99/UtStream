package com.example.utstream.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.utstream.R;
import com.squareup.picasso.Picasso;

public class show extends AppCompatActivity {
    VideoView video;
    WebView web;
    String vidAddress;
    Uri vidUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show2);
        getSupportActionBar().hide();
        video=(VideoView)findViewById(R.id.video);
        Bundle datos = this.getIntent().getExtras();
        String tipo=datos.getString("tipo");
        vidAddress =datos.getString("link");
        String titulo=datos.getString("nombre");
        setTitle(titulo);
        playVideo(vidAddress.replace(" ",""));

    }
    void playVideo(String vidAddress ){
        vidUri = Uri.parse(vidAddress);
        video.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        vidControl.show();
        vidControl.setAnchorView(video);
        video.setMediaController(vidControl);
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "GRACIAS POR VER", Toast.LENGTH_LONG).show(); // display a toast when an video is completed

            }
        });
        video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(getApplicationContext(), "OCURRIO UN ERROR AL CARGAR EL VIDEO", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }
}