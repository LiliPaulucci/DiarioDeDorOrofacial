package com.a4tecnologia.diariodedororofacial;

/**
 * Created by LiliPaulucci on 23/12/16.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Question2 extends Fragment {

    private String tipoDor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question2, container, false);
        tipoDor = "0";

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroupTipoDor);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton1:
                        // Cansada
                        tipoDor = "1";
                        break;
                    case R.id.radioButton2:
                        // Pontada
                        tipoDor = "2";
                        break;
                    case R.id.radioButton3:
                        // Pesada
                        tipoDor = "3";
                        break;
                    case R.id.radioButton4:
                        // Fisgada
                        tipoDor = "4";
                        break;
                    case R.id.radioButton5:
                        // Aperto
                        tipoDor = "5";
                        break;
                    case R.id.radioButton6:
                        // Choque
                        tipoDor = "6";
                        break;
                    case R.id.radioButton7:
                        // Pulsada
                        tipoDor = "7";
                        break;
                }

                FormularioActivity atividade = (FormularioActivity) getActivity();
                atividade.SetaValores("TIPO", tipoDor);
            }
        });


        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        FormularioActivity atividade = (FormularioActivity) getActivity();
                        atividade.SetaValores("TIPO", tipoDor);
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