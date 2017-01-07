package com.example.yen.ru.dependency.component;

import android.app.Activity;
import com.example.yen.ru.dependency.module.ActivityModule;
import com.example.yen.ru.dependency.scope.PerActivity;
import com.example.yen.ru.ui.mvp.detailpage.DetailActivity;
import com.example.yen.ru.ui.mvp.mainpage.MainFragment;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainFragment resultFragment);
    void inject(DetailActivity activity);


    final class Initializer {

        public static ActivityComponent init(ApplicationComponent appComponent, Activity activity) {

            return DaggerActivityComponent.builder()
                    .applicationComponent(appComponent)
                    .activityModule(new ActivityModule(activity))
                    .build();
        }
    }

}