package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    private TextView txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txtNome = findViewById(R.id.txtNome);
        Bundle args = getIntent().getExtras();
        txtNome.setText("Bem vindo " + args.getString("ch_usuario"));
    }
}