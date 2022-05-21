package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.ListController;
import com.example.lafamilledesanimaux.models.Animal;
import com.example.lafamilledesanimaux.models.Categorie;
import com.example.lafamilledesanimaux.models.Pays;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    // properties
    private EditText editTextName;
    private Spinner spinner;
    private Spinner spinner2;
    private int iSelectedPays = 0;
    private int iSelectedCat = 0;
    ArrayList<Animal> animallist;
    ArrayList<Pays> payslist;
    int[] iPays;
    ArrayList<Categorie> categorielist;
    int[] iCategorie;
    private ListController controlAnimal;
    private static int splash_time_out = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressDialog progressDialog = new ProgressDialog(List.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("A la découverte des animaux"); // set message
        progressDialog.show();
        controlAnimal.getInstance();
        animallist = controlAnimal.animalList;
        payslist = controlAnimal.paysList;
        categorielist = controlAnimal.categoryList;
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){
                setContentView(R.layout.activity_list);
                /*controlAnimal.getInstance();
                animallist = controlAnimal.animalList;*/
                init();
                createList();
                progressDialog.dismiss();
            }
        }, splash_time_out);

        /*setContentView(R.layout.activity_list);
        controlAnimal.getInstance();
        animallist = controlAnimal.animalList;
        init();
        createList();*/
    }

    private String[] getBankNamePays(){
        String[] bankNames = new String[payslist.size()];
        iPays = new int[payslist.size()];
        for(int i=0; i<payslist.size(); i++){
            iPays[i] = payslist.get(i).getId_pays();
            bankNames[i] = payslist.get(i).getNom_pays();
        }
        return bankNames;
    }

    private String[] getBankNameCategory(){
        String[] bankNames = new String[categorielist.size()];
        iCategorie = new int[categorielist.size()];
        for(int i=0; i<categorielist.size(); i++){
            iCategorie[i] = categorielist.get(i).getId_categorie();
            bankNames[i] = categorielist.get(i).getNom_categorie();
        }
        return bankNames;
    }

    private void init(){
        editTextName = (EditText)findViewById(R.id.editTextName);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String[] bankNamePays = getBankNamePays();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNamePays);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        String[] bankNameCategorie = getBankNameCategory();
        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNameCategorie);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(aa2);
        listener();
    }

    /**
     * creer la liste adapter
     */
    private void createList(){
        if(animallist != null){
            ListView list = (ListView) findViewById(R.id.listeview);
            ListAdapter listeadapter = new ListAdapter(this, animallist);
            list.setAdapter(listeadapter);
        }
    }

    /**
     * Ecoute evenement sur bouton rechercher
     */
    private void listener(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iSelectedPays = iPays[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                iSelectedCat = iCategorie[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String nom = editTextName.getText().toString();
                final ProgressDialog progressDialogClick = new ProgressDialog(List.this);
                progressDialogClick.setCancelable(false); // set cancelable to false
                progressDialogClick.setMessage("A la découverte des animaux"); // set message
                progressDialogClick.show();
                animallist = new Animal().getListByCriteria(nom, iSelectedPays, iSelectedCat);

                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run(){
                        createList();
                        progressDialogClick.dismiss();
                    }
                }, splash_time_out);
                createList();
            }
        });
    }

    /**
     * envoi de l'indice de l'animal cliqué
     * @param id
     */
    public void setIndice(int id) {
        Intent homeIntent = new Intent(List.this, Fiche.class);
        homeIntent.putExtra("idanimal", id);
        Log.d("findAllAnimals", String.valueOf(homeIntent.getIntExtra("idanimal", 0)));
        startActivity(homeIntent);
        finish();
    }
}