package com.example.listilla;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record implements Comparable<Record> {
        public int intents;
        public String nom;
        public int profile;

        public Record(int _intents, String _nom, int _profile ) {
            intents = _intents;
            nom = _nom;
            profile = _profile;
        }


        @Override
        public int compareTo(Record r) {
            if (intents > r.intents) {
                return 1;
            } else if (intents == r.intents) {
              return 0;
            } else {
                return -1;
            }
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemple
        records.add( new Record(33,"Manolo", R.drawable.profile1) );
        records.add( new Record(12,"Pepe", R.drawable.profile2 ) );
        records.add( new Record(42,"Laura", R.drawable.profile3) );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                ImageView foto = convertView.findViewById(R.id.imageView);
                int profile = getItem(pos).profile;
                foto.setImageResource(profile);


                //((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(R.drawable.);
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        ArrayList<String> listaNombres = new ArrayList<String>();
        listaNombres.add("Joel");
        listaNombres.add("Juan");
        listaNombres.add("María");
        listaNombres.add("Luis");
        listaNombres.add("Ana");
        listaNombres.add("Pedro");
        listaNombres.add("Laura");
        listaNombres.add("Carlos");
        listaNombres.add("Sofía");
        listaNombres.add("Miguel");
        listaNombres.add("Isabel");
        listaNombres.add("Antonio");
        listaNombres.add("Carmen");
        listaNombres.add("Javier");
        listaNombres.add("Francisco");
        listaNombres.add("Paula");
        listaNombres.add("David");
        listaNombres.add("Beatriz");
        listaNombres.add("Manuel");
        listaNombres.add("Daniel");
        listaNombres.add("Victoria");

        ArrayList<Integer> listaFoto = new ArrayList<Integer>();
        listaFoto.add(R.drawable.profile1);
        listaFoto.add(R.drawable.profile2);
        listaFoto.add(R.drawable.profile3);
        listaFoto.add(R.drawable.profile4);

        Random rand = new Random();

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<5;i++) {
                    int num = rand.nextInt(99) + 1 ;
                    int nom = rand.nextInt(listaNombres.size());
                    int foto = rand.nextInt(listaFoto.size());
                    records.add(new Record(num, listaNombres.get(nom), listaFoto.get(foto)));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });

        Button b2 = (Button) findViewById(R.id.sortButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(records);
                adapter.notifyDataSetChanged();
            }
        });
    }
}