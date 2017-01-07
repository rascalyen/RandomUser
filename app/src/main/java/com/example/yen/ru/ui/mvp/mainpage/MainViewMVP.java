package com.example.yen.ru.ui.mvp.mainpage;

import com.example.yen.ru.data.model.Result;
import com.example.yen.ru.ui.ProgressViewMVP;
import java.util.List;


public interface MainViewMVP extends ProgressViewMVP {

    void clearResults();
    void viewResults(List<Result> results);
    int sizeOfResult();
    void showFooter();
    void removeFooter();
    void storeGender(String gender);
}