package com.example.yen.ru.dependency.module;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides Activity provideActivity() {
        return this.activity;
    }

}