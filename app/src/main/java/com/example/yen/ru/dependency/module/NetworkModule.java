package com.example.yen.ru.dependency.module;

import android.app.Application;
import com.example.yen.ru.configs.Configuration;
import com.example.yen.ru.web.RUClient;
import com.squareup.picasso.Picasso;
import java.util.Properties;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class NetworkModule {

    public NetworkModule() {}


    @Provides @Singleton
    Configuration provideConfiguration(Application application) {
        return new Configuration(application);
    }

    @Provides
    Properties provideProperties(Configuration config) {
        return config.getProperties();
    }

    @Provides
    OkHttpClient provideOkHttpClient(Configuration config) {
        return config.getOkHttpClient();
    }

    @Provides
    Picasso providePicasso(Configuration config) {
        return config.getPicasso();
    }

    @Provides @Singleton
    RUClient provideTomatoClient(OkHttpClient okHttpClient, Properties properties) {
        return new RUClient(okHttpClient, properties);
    }

}