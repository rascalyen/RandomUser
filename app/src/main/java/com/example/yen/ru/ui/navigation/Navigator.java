package com.example.yen.ru.ui.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.example.yen.ru.data.model.Result;
import com.example.yen.ru.ui.mvp.detailpage.DetailActivity;
import javax.inject.Inject;


public class Navigator {


    @Inject public Navigator() {}

    public void navigateToDetail(@NonNull Context context, Result result) {
        context.startActivity(DetailActivity.getCalled(context, result));
    }

}