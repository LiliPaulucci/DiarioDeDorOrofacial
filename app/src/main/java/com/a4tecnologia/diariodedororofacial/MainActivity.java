package com.a4tecnologia.diariodedororofacial;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.io.File;

public class MainActivity extends Activity {


    private EditText editTxtCodigo;
    String _codigo;
    String _idPaciente;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        editTxtCodigo = (EditText) findViewById(R.id.txtCodigoPaciente);
        context = this.getBaseContext();
        String codigoPaciente = FileHelper.VerificaArquivoInfo(context.getFilesDir());
        if (codigoPaciente != null) {
            editTxtCodigo.setText(codigoPaciente);
        }
    }

    public void btnEntrar(View view) {
        _codigo = editTxtCodigo.getText().toString();
        WSValidarPaciente task = new WSValidarPaciente();
        task.execute();
    }

    class WSValidarPaciente extends AsyncTask<String, Integer, Void> {
        boolean running;
        ProgressDialog progressDialog;

        protected Void doInBackground(String... params) {
            _idPaciente = WebService.dtmWSValidarPaciente(_codigo);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Localizando paciente...",
                    "Aguarde enquanto localizo seu código!");

            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    running = false;
                }
            });
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //progressDialog.setMessage(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void result) {
            if (_idPaciente.equals("0")) {
                Toast.makeText(getApplicationContext(), "Paciente não encontrado", Toast.LENGTH_LONG).show();
            }
            else{
                FileHelper.gravaInformacoes(_codigo, context.getFilesDir());
                CallNextActivity (_idPaciente);
            }

            super.onPostExecute(result);

            progressDialog.dismiss();
        }
    }

    public void CallNextActivity (String codigoPaciente){
        Intent intent = new Intent(this, InicioActivity.class);
        intent.putExtra("codigo", codigoPaciente);
        startActivity(intent);
        finish();
    }
}
