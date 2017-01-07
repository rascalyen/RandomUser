package com.example.yen.ru.ui;


public interface Presenter<T extends ViewMVP> {

    void attachViewMVP(T t);

    void detachViewMVP();
}