package com.a4tecnologia.diariodedororofacial;

/**
 * Created by LiliPaulucci on 20/03/18.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Question2a extends Fragment {

    private String localizacaoDor;
    private RadioButton m_CD, m_CE, m_RD, m_RE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question2a, container, false);
        localizacaoDor = " ";

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroupLocalizDor);

        m_CD = (RadioButton) rootView.findViewById(R.id.rbCabecaDireita);
        m_CE = (RadioButton) rootView.findViewById(R.id.rbCabecaEsquerda);
        m_RD = (RadioButton) rootView.findViewById(R.id.rbRostoDireito);
        m_RE = (RadioButton) rootView.findViewById(R.id.rbRostoEsquerdo);

        m_CD.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                m_CD.setChecked(true);
                m_CE.setChecked(false);
                m_RD.setChecked(false);
                m_RE.setChecked(false);
                localizacaoDor = "Cabeça Direita";
                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("LOCALIZACAO_DOR", localizacaoDor);
            }
        });

        m_CE.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                m_CD.setChecked(false);
                m_CE.setChecked(true);
                m_RD.setChecked(false);
                m_RE.setChecked(false);
                localizacaoDor = "Cabeça Esquerda";
                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("LOCALIZACAO_DOR", localizacaoDor);
            }
        });

        m_RD.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                m_CD.setChecked(false);
                m_CE.setChecked(false);
                m_RD.setChecked(true);
                m_RE.setChecked(false);
                localizacaoDor = "Rosto Direito";
                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("LOCALIZACAO_DOR", localizacaoDor);
            }
        });

        m_RE.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                m_CD.setChecked(false);
                m_CE.setChecked(false);
                m_RD.setChecked(false);
                m_RE.setChecked(true);
                localizacaoDor = "Rosto Esquerdo";
                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("LOCALIZACAO_DOR", localizacaoDor);
            }
        });

        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbCabecaDireita:
                        localizacaoDor = "Cabeça Direita";
                        break;
                    case R.id.rbCabecaEsquerda:
                        localizacaoDor = "Cabeça Esquerda";
                        break;
                    case R.id.rbRostoDireito:
                        localizacaoDor = "Rosto Direito";
                        break;
                    case R.id.rbRostoEsquerdo:
                        localizacaoDor = "Rosto Esquerdo";
                        break;
                }

                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("LOCALIZACAO_DOR", localizacaoDor);
            }
        });*/


        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        FormularioActivity atividade = (FormularioActivity) getActivity();
                        atividade.SetaValores("LOCALIZACAO_DOR", localizacaoDor);
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {

                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                //Log.i(Constants.APP_TAG, "Right to Left");
                                int i = 0;
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                //Log.i(Constants.APP_TAG, "Left to Right");
                                //titles.showDetails(getPosition() - 1);
                                int i = 0;
                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
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
