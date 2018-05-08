package com.a4tecnologia.diariodedororofacial;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();
        String retorno = intent.getStringExtra("retorno");

        TextView textView = (TextView) findViewById(R.id.txtViewRetornoNotific);
        textView.setText(retorno);
    }

    public void btnRetornoNotific(View view) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(134);
        finish();
    }
}
