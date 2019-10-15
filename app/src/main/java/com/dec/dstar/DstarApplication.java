package com.dec.dstar;

import android.app.Application;

import com.dec.dstar.config.EnvironmentConfig;
import com.dec.dstar.utils.AppFilePath;

import io.realm.Realm;

public class DstarApplication extends Application {
    public static DstarApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Realm.init(this);
        EnvironmentConfig.getInstance().setBaseUrl(EnvironmentConfig.ENVIRONMENT.TEST);
        AppFilePath.init(this);
    }

    public static DstarApplication getInstance(){

        return INSTANCE;
    }

}
