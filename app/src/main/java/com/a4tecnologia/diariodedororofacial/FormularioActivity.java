package com.a4tecnologia.diariodedororofacial;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class FormularioActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    String _codigo;
    FormularioDorModel formulario;
    EditText inputTextQ9;
    String _response;
    String _retornoFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Intent intent = getIntent();
        _codigo = intent.getStringExtra("codigo");
        inputTextQ9 = (EditText) findViewById(R.id.editTextQ9);

        _retornoFeedback = "";

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        formulario = new FormularioDorModel();
    }

    public void SetaValores(String nomeProp, String valorProp){
        formulario.setValue(nomeProp, valorProp);
    }

    public void btnFinalizar(View view) {
        inputTextQ9 = (EditText) findViewById(R.id.editTextQ9);
        SetaValores("INFORMACAO", inputTextQ9.getText().toString());

        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String datetime = dateformat.format(c.getTime());
            SetaValores("DATAHORA", datetime);
        } catch (Exception ex) {}

        WSEnviaFormulario task = new WSEnviaFormulario();
        task.execute();
    }

    public void setCurrentItem (int item, boolean smoothScroll) {
        mViewPager.setCurrentItem(item, smoothScroll);
    }

    public void btnProximo(View view) {
        int current = mViewPager.getCurrentItem();
        if (current == 4){
            SetaSentiuAntes();
        } else if (current == 5) {
            SetaSentiuDurante();
        }
        setCurrentItem (current + 1, true);
    }

    public void btnAnterior(View view) {
        int current = mViewPager.getCurrentItem();
        setCurrentItem (current - 1, true);
    }

    private void SetaSentiuAntes(){
        EditText inputText = (EditText) findViewById(R.id.editTextQ3);
        SetaValores("SENTIU_ANTES", inputText.getText().toString());
    }

    private void SetaSentiuDurante(){
        EditText inputText = (EditText) findViewById(R.id.editTextQ4);
        SetaValores("SENTIU_DURANTE", inputText.getText().toString());
    }

    class WSEnviaFormulario extends AsyncTask<String, Void, Void> {
        boolean running;
        ProgressDialog progressDialog;

        protected Void doInBackground(String... params) {
            try {
                _response = WebService.dtmWSEnviaFormularioDor(_codigo, formulario);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;

            progressDialog = ProgressDialog.show(FormularioActivity.this,
                    "Enviando formulário...",
                    "Aguarde enquanto envio seu formulário!");

            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    running = false;
                }
            });
        }

        @Override
        protected void onPostExecute(Void result) {
            if (_response.equals("SIM")) {
                _retornoFeedback = formulario.montaFeedBackFormulario();
                WSEnviaNotificacao taskNotif = new WSEnviaNotificacao();
                taskNotif.execute();
                CallNextActivity();
            }
            else if (_response.equals("NAO")){
                CallNextActivity();
            }
            else {
                Toast.makeText(getApplicationContext(), "Erro ao enviar o Formulário", Toast.LENGTH_LONG).show();
                CallNextActivityError();
            }



            super.onPostExecute(result);

            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Question2a tab2a = new Question2a();
                    return tab2a;
                case 1:
                    Question2 tab2 = new Question2();
                    return tab2;
                case 2:
                    Question1 tab1 = new Question1();
                    return tab1;
                case 3:
                    Question2b tab2b = new Question2b();
                    return tab2b;
                case 4:
                    Question3 tab3 = new Question3();
                    return tab3;
                case 5:
                    Question4 tab4 = new Question4();
                    return tab4;
                case 6:
                    Question5 tab5 = new Question5();
                    return tab5;
                case 7:
                    Question6 tab6 = new Question6();
                    return tab6;
                case 8:
                    Question7 tab7 = new Question7();
                    return tab7;
                case 9:
                    Question8 tab8 = new Question8();
                    return tab8;
                case 10:
                    Question9 tab9 = new Question9();
                    return tab9;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 11;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    public void CallNextActivityError (){
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
        finish();
    }

    public void CallNextActivity (){
        Intent intent = new Intent(this, FimActivity.class);
        startActivity(intent);
        finish();
    }

    public void EnviaNotificacao(String retorno){
        if (retorno != "") {
            android.support.v4.app.NotificationCompat.Builder mBuilder = new android.support.v4.app.NotificationCompat.Builder(this);
            //int color = 0x008000;
            //mBuilder.setColor(color);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_old);
            mBuilder.setContentTitle("Diário de Dor Orofacial");
            mBuilder.setContentText("Clique aqui para ler o feedback do seu formulário de dor.");
            Intent resultIntent = new Intent(this, NotificationActivity.class);

            resultIntent.putExtra("retorno", retorno);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(NotificationActivity.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            try {
                Thread.sleep(60);
            } catch (Exception e) {

            }

            mNotificationManager.notify(134, mBuilder.build());
        }
    }

    class WSEnviaNotificacao extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... params) {
            try {
                if (_retornoFeedback != "")
                    EnviaNotificacao(_retornoFeedback);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }
}
