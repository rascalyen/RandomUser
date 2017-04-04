package com.example.yen.ru.dependency.component;

import android.app.Application;
import com.example.yen.ru.RandomUserApplication;
import com.example.yen.ru.dependency.module.ApplicationModule;
import com.example.yen.ru.dependency.module.NetworkModule;
import com.example.yen.ru.web.RUClient;
import com.squareup.picasso.Picasso;
import java.util.Properties;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void injectApplication(RandomUserApplication application);

    Properties properties();
    Picasso picasso();
    RUClient ruClient();


    final class Initializer {

        public static ApplicationComponent init(Application application) {

            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .networkModule(new NetworkModule())
                    .build();
        }
    }

}
