package com.a4tecnologia.diariodedororofacial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    String _codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent intent = getIntent();
        _codigo = intent.getStringExtra("codigo");

    }

    public void btnIniciar(View view) {
        CallNextActivity();
    }

    public void CallNextActivity (){
        Intent intent = new Intent(this, FormularioActivity.class);
        intent.putExtra("codigo", _codigo);
        startActivity(intent);
        finish();
    }
}
