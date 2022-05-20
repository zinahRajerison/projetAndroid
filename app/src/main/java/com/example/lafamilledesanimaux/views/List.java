package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.controllers.AnimalController;
import com.example.lafamilledesanimaux.models.Animal;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    // properties
    private EditText editTextName;
    private Spinner spinner;
    String[] bankNames={"nom","pays","categorie"};
    private int iSelected = 0;
    ArrayList<Animal> animallist;
    private AnimalController controlAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        controlAnimal.getInstance();
        animallist = controlAnimal.animalList;
        init();
        createList();
    }

    private void init(){
        editTextName = (EditText)findViewById(R.id.editTextName);
        spinner = (Spinner) findViewById(R.id.spinner2);
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
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
                Toast.makeText(getApplicationContext(), bankNames[i], Toast.LENGTH_LONG).show();
                iSelected = i;
                Log.d("liste",bankNames[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name = editTextName.getText().toString();
                String criteria = bankNames[iSelected];
                Toast.makeText(getApplicationContext(), name + " " + criteria, Toast.LENGTH_LONG).show();
                animallist = new Animal().getListByCriteria(name, criteria);
                createList();
            }
        });
    }

    public void setIndice(int position) {
        Intent homeIntent = new Intent(List.this, Fiche.class);
        homeIntent.putExtra("idanimal", position);
        startActivity(homeIntent);
        finish();
    }
}