package br.com.fischborn.classificados.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import br.com.fischborn.classificados.App;
import br.com.fischborn.classificados.R;

/**
 * Created by infra on 24/01/2018.
 */

public class ChronometerFragment extends Fragment {

    private Chronometer mChTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container);

        mChTimer = (Chronometer) view.findViewById(R.id.ch_time);

        return view;
    }

    private App getApp() {
        return (App) getActivity().getApplication();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChTimer.setBase(((App) getActivity().getApplication()).getCurrentTime() +
                SystemClock.elapsedRealtime());
        mChTimer.start();
    }

    @Override
    public void onPause() {
        super.onPause();

        getApp().setCurrentTime(mChTimer.getBase() -
                SystemClock.elapsedRealtime());
    }
}
