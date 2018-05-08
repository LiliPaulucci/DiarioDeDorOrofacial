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
import android.widget.RadioGroup;

public class Question7 extends Fragment {
    private String resposta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question7, container, false);
        resposta = "0";

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroupQ7);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButtonQ7N:
                        // Não
                        resposta = "N";
                        break;
                    case R.id.radioButtonQ7O:
                        // Outro
                        resposta = "O";
                        break;
                    case R.id.radioButtonQ7S:
                        // Sim
                        resposta = "S";
                        break;
                }
                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("P_MENSTRUAL", resposta);
            }
        });

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        FormularioActivity atividade = (FormularioActivity) getActivity();
                        atividade.SetaValores("P_MENSTRUAL", resposta);
                        return true;
                    }
                });

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });


        return rootView;
    }
}
