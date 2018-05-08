package com.a4tecnologia.diariodedororofacial;

/**
 * Created by LiliPaulucci on 23/12/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Question9 extends Fragment {
    EditText inputText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question9, container, false);
        inputText = (EditText) rootView.findViewById(R.id.editTextQ9);

        /*final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        FormularioActivity atividade = (FormularioActivity) getActivity();
                        atividade.SetaValores("INFORMACAO", inputText.getText().toString());
                        return true;
                    }
                });

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });*/
        return rootView;
    }


}
