package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.ListController;
import com.example.lafamilledesanimaux.models.Animal;
import com.example.lafamilledesanimaux.models.CardGVAdapter;
import com.example.lafamilledesanimaux.models.CardModel;

import java.util.ArrayList;

public class SoundGame extends AppCompatActivity {

    private GridView choices;
    private VideoView video;
    private boolean videostate;
    ArrayList<Animal> animallist;
    private ListController controlAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_game);
        init();
    }

    private void init(){
        choices = (GridView) findViewById(R.id.choices);
        video = (VideoView) findViewById(R.id.videoView);
        videostate = false;
        controlAnimal.getInstance();
        animallist = controlAnimal.animalList;
        setData();
        listener();
    }
    
    private void setData(){
        ArrayList<CardModel> choicesImg = new ArrayList<CardModel>();
        choicesImg.add(new CardModel("choix1", R.drawable.img4));
        choicesImg.add(new CardModel("choix2", R.drawable.img17));
        choicesImg.add(new CardModel("choix3", R.drawable.img6));
        choicesImg.add(new CardModel("choix4", R.drawable.img20));
        CardGVAdapter adapter = new CardGVAdapter(this, choicesImg);

        /*String fnm = animallist.get(0).getName().toLowerCase(); //  this is image file name
        String PACKAGE_NAME = getApplicationContext().getPackageName();
        int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
        // imgAnimal.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
        choicesImg.add(new CardModel("choix1", imgId));*/

        choices.setAdapter(adapter);
        displayVideo("lion");
    }

    public void displayVideo(String fnm){
        getResources().getIdentifier("FILENAME_WITHOUT_EXTENSION", "raw", getPackageName());
        String path = "android.resource://" + getPackageName() + "/" + "raw/"+fnm;
        video.setVideoURI(Uri.parse(path));
    }

    private void listener() {
        this.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videostate){
                    video.stopPlayback();
                    videostate = false;
                }else{
                    video.start();
                    videostate = true;
                }
            }
        });
        this.choices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(SoundGame.this, "choice1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(SoundGame.this, "choice2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(SoundGame.this, "choice3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(SoundGame.this, "choice4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}