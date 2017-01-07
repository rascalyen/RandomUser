package com.example.yen.ru;

import android.app.Application;
import com.example.yen.ru.dependency.component.ApplicationComponent;
import com.squareup.leakcanary.LeakCanary;


public class RandomUserApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        injectComponent();
    }

    private void injectComponent() {
        applicationComponent = ApplicationComponent.Initializer.init(this);
        applicationComponent.injectApplication(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}