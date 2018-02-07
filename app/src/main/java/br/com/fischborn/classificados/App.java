package br.com.fischborn.classificados;

import android.app.Application;

/**
 * Created by Karen on 25/01/2018.
 */

public class App extends Application {
    private Long mCurrentTime;

    public Long getCurrentTime() {
        if (mCurrentTime == null) {
            return 0L;
        } else {
            return mCurrentTime;

        }
    }

    public void setCurrentTime(Long currentTime) {
        mCurrentTime = currentTime;
    }
}
