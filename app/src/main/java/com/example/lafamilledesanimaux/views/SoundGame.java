package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.ListController;
import com.example.lafamilledesanimaux.models.Animal;
import com.example.lafamilledesanimaux.models.CardGVAdapter;
import com.example.lafamilledesanimaux.models.CardModel;

import java.util.ArrayList;
import java.util.Random;

public class SoundGame extends AppCompatActivity {

    private GridView choices;
    private VideoView video;
    private boolean videostate;
    ArrayList<Animal> animallist;
    private ListController controlAnimal;
    ArrayList<Integer> indices;
    TextView txtQuestion;
    int idReponse;

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
        txtQuestion=(TextView) findViewById(R.id.txtQuest);
        indices=new ArrayList<Integer>();
        setData();
        listener();
    }
    private boolean testerAppartenance(int atester){
        boolean ret= false;
        for(int i=0;i<indices.size();i++){
            if(atester==indices.get(i)){
                return true;
            }
        }
        return ret;
    }
    private void setData(){
        int randomfamille=new Random().nextInt(2);
        if(randomfamille==0){
            txtQuestion.setText("Quel est le nom de la femelle de l'animal qui porte ce cri?");
        }else{
            txtQuestion.setText("Comment appelle-t-on le nom de l'enfant de l'animal qui porte ce cri?");
        }
        int max = animallist.size();
        while(indices.size()!=4){
            int atester = new Random().nextInt(max);
            if(!testerAppartenance(atester)){
                indices.add(new Integer(atester));
            }
        }
        this.idReponse=indices.get(new Random().nextInt(4));

        ArrayList<CardModel> choicesImg = new ArrayList<CardModel>();
        for(int i=0;i<indices.size();i++){
            String fnm = animallist.get(indices.get(i)).getName().toLowerCase(); //  this is image file name
            String PACKAGE_NAME = getApplicationContext().getPackageName();
            int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
            if(randomfamille==0){
                choicesImg.add(new CardModel(animallist.get(indices.get(i)).getFemelle(),imgId ));
            }
            else{
                choicesImg.add(new CardModel(animallist.get(indices.get(i)).getEnfant(),imgId ));
            }
        }
        CardGVAdapter adapter = new CardGVAdapter(this, choicesImg);

        /*String fnm = animallist.get(0).getName().toLowerCase(); //  this is image file name
        String PACKAGE_NAME = getApplicationContext().getPackageName();
        int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
        // imgAnimal.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
        choicesImg.add(new CardModel("choix1", imgId));*/

        choices.setAdapter(adapter);
        displayVideo(animallist.get(idReponse).getName().toLowerCase());
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
                if(indices.get(position)!=idReponse)
                {
                    Toast.makeText(SoundGame.this, "Mauvaise reponse, reessayez!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SoundGame.this, "Bien joue", Toast.LENGTH_SHORT).show();
                    setData();
                }
            }
        });
    }
}