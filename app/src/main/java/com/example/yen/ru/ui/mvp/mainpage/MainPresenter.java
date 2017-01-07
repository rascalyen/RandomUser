package com.example.yen.ru.ui.mvp.mainpage;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.ru.data.response.RandomUserResponse;
import com.example.yen.ru.ui.Presenter;
import com.example.yen.ru.web.RUClient;
import java.util.Properties;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements Presenter<MainViewMVP> {

    private boolean isLoadMore;
    private int total;
    private int page;
    private String gender;
    private RUClient RUClient;
    private Properties properties;
    private MainViewMVP mainView;
    private Call<RandomUserResponse> call;

    @Inject public MainPresenter(RUClient RUClient, Properties properties) {
        this.RUClient = RUClient;
        this.properties = properties;
    }

    public void initialize() {
        isLoadMore = false;
        page = 1;
        total = 0;
        mainView.clearResults();
        mainView.hideRetry();
        mainView.showProgress();
        getRandomResults(page, gender);
    }

    public void getMale() {
        isLoadMore = false;
        page = 1;
        total = 0;
        gender = "male";
        mainView.clearResults();
        mainView.hideRetry();
        mainView.showProgress();
        getRandomResults(page, gender);
    }

    public void getFemale() {
        isLoadMore = false;
        page = 1;
        total = 0;
        gender = "female";
        mainView.clearResults();
        mainView.hideRetry();
        mainView.showProgress();
        getRandomResults(page, gender);
    }

    public void loadMore() {

        if (getSizeOfResults() == total) {
            isLoadMore = true;
            page++;
            mainView.hideRetry();
            mainView.showFooter();
            getRandomResults(page, gender);
        }
    }

    protected int getSizeOfResults() {
        return mainView.sizeOfResult();
    }

    public void setTotalAndPage(int i) {
        total = i;
        page = total / Integer.parseInt(properties.getProperty("results"));
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    protected void getRandomResults(int page, String gender) {
        mainView.storeGender(gender);

        call = RUClient.getRUService().getResults(properties.getProperty("results"), Integer.toString(page), gender);

        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {

                if (response.isSuccessful() && response.body().getError() == null)
                    onSuccess(response.body());
                else
                    onError(response.body());
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                if (isLoadMore) {
                    Log.i(MainPresenter.class.getSimpleName(), "###  MOM I failed on loadMore...");
                    decreasePage();
                    mainView.removeFooter();
                }
                else {
                    Log.i(MainPresenter.class.getSimpleName(), "###  MOM I failed on regularCall.");
                    mainView.showRetry();
                    mainView.hideProgress();
                }
            }
        });
    }

    private void decreasePage() {
        page--;
    }

    protected void onSuccess(RandomUserResponse randomUserResponse) {
        if (mainView != null) {

            if (isLoadMore)
                mainView.removeFooter();
            else
                mainView.hideProgress();

            mainView.viewResults(randomUserResponse.getResults());
            total += randomUserResponse.getResults().size();
        }
    }

    protected void onError(RandomUserResponse randomUserResponse) {
        if (mainView != null) {

            if (isLoadMore) {
                Log.i(MainPresenter.class.getSimpleName(), "###  MOM I errored on loadMore...");
                decreasePage();
                mainView.removeFooter();
            }
            else {
                Log.i(MainPresenter.class.getSimpleName(), "###  MOM I errored on regularCall.");
                mainView.showRetry();
                mainView.hideProgress();
            }
        }
    }

    public void cancelCall() {

        if (call != null && !call.isCanceled())
            call.cancel();
    }

    @Override
    public void attachViewMVP(@NonNull MainViewMVP mainView) {
        this.mainView = mainView;
    }

    @Override
    public void detachViewMVP() {
        this.mainView = null;
    }

}