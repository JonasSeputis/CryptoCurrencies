package com.example.tribecrypto.java;

import android.app.Application;
import android.content.Context;

import com.example.tribecrypto.di.component.ApplicationComponent;
import com.example.tribecrypto.di.component.DaggerApplicationComponent;
import com.example.tribecrypto.di.module.AppModule;
import com.example.tribecrypto.di.module.DatabaseModule;

import timber.log.Timber;

public class ApplicationClass extends Application {

    private ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent(Context context) {
        ApplicationClass app = (ApplicationClass) context.getApplicationContext();
        app.applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(app))
                .databaseModule(new DatabaseModule(app))
                .build();
        return app.applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        getApplicationComponent(this).inject(this);
    }
}
