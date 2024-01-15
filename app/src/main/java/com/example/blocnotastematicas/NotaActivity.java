package com.example.blocnotastematicas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotaActivity extends AppCompatActivity {

    private String id;
    private EditText txtContenido;
    private String nombreFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        setSupportActionBar(findViewById(R.id.toolbar));

        txtContenido = findViewById(R.id.txtContenido);
        this.id = getResources().getResourceEntryName(getIntent().getIntExtra("id", -1));
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean res = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_ok) {
            String mensaje = "";
            try {
                File fichero = new File(this.nombreFichero);
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(fichero.getName(), Activity.MODE_PRIVATE));
                osw.write(txtContenido.getText().toString());
                osw.flush();
                osw.close();
                mensaje =  "Fichero guardado correctamente";
            } catch (IOException e) {
                mensaje = "No se ha podido guardar el fichero";
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
            finish();
            res = true;
        }

        return res;
    }

    private void init () {
        switch (this.id) {
            case "layUrgente":
                setTitle(R.string.lblUrgente);
                this.nombreFichero = "urgente.txt";
                break;
            case "layViajes":
                setTitle(R.string.lblViajes);
                this.nombreFichero = "viajes.txt";
                break;
            case "layConciertos":
                setTitle(R.string.lblConciertos);
                this.nombreFichero = "conciertos.txt";
                break;
            case "layFamilia":
                setTitle(R.string.lblFamilia);
                this.nombreFichero = "familia.txt";
                break;
            case "layAmigos":
                setTitle(R.string.lblAmigos);
                this.nombreFichero = "amigos.txt";
                break;
            case "layDeportes":
                setTitle(R.string.lblDeportes);
                this.nombreFichero = "deportes.txt";
                break;
            case "layCultura":
                setTitle(R.string.lblCultura);
                this.nombreFichero = "cultura.txt";
                break;
            case "layComida":
                setTitle(R.string.lblComida);
                this.nombreFichero = "comida.txt";
                break;
            case "layInternet":
                setTitle(R.string.lblInternet);
                this.nombreFichero = "internet.txt";
                break;
            case "layCine":
                setTitle(R.string.lblCine);
                this.nombreFichero = "cine.txt";
                break;
        }

        leerFichero();
    }

    private void leerFichero() {
        String mensaje = "";

        try {
            File f = new File(this.nombreFichero);
            InputStreamReader isr = new InputStreamReader(openFileInput(f.getName()));
            BufferedReader br = new BufferedReader(isr);
            String linea = br.readLine();
            StringBuilder textoLeido = new StringBuilder();
            while (linea != null) {
                textoLeido.append(linea).append("\n");
                linea = br.readLine();
            }
            br.close();
            isr.close();

            txtContenido.setText(textoLeido.toString());
            mensaje = "Fichero leído correctamente";
        } catch (IOException ex) {
            txtContenido.setText("");
            mensaje = "No se ha podido leer el fichero.";
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}