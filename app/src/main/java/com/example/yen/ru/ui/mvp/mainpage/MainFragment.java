package com.example.yen.ru.ui.mvp.mainpage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.yen.ru.R;
import com.example.yen.ru.data.model.Result;
import com.example.yen.ru.dependency.component.ActivityComponent;
import com.example.yen.ru.ui.BaseFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


public class MainFragment extends BaseFragment implements MainViewMVP {

    @Bind(R.id.rl_progress)
    RelativeLayout progressView;
    @Bind(R.id.rl_retry)
    RelativeLayout noResultView;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Inject MainPresenter mainPresenter;
    @Inject ResultAdapter resultAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int preLastPosition;
    private static final String RESULT_STATE = "RESULT_STATE";
    private static final String GENDER_STATE = "GENDER_STATE";
    private ArrayList<Result> results;
    private String gender;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        super();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RESULT_STATE, results);
        outState.putString(GENDER_STATE, gender);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            results = savedInstanceState.getParcelableArrayList(RESULT_STATE);
            gender = savedInstanceState.getString(GENDER_STATE);
        }
        else if (results == null) {
            results = new ArrayList<>();
            gender = null;
        }

        injectComponent();
        setRecyclerView();
        initialize();
    }

    private void injectComponent() {
        this.getComponent(ActivityComponent.class).inject(this);
    }

    private void setRecyclerView() {
        resultAdapter.setResults(results);
        recyclerView.setAdapter(resultAdapter);
        linearLayoutManager =  new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(recyclerOnScrollListener);
    }

    private RecyclerView.OnScrollListener recyclerOnScrollListener =
            new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (visibleItemCount > 0 && (progressView.getVisibility() != View.VISIBLE)) {
                        int vH = (recyclerView == null) ? 0 :
                                recyclerView.getHeight();
                        int bottomPos = (recyclerView == null) ? 0 :
                                recyclerView.getChildAt(visibleItemCount - 1)
                                        .getPaddingBottom();
                        int lastItemPosition = firstVisibleItem + visibleItemCount;

                        if (lastItemPosition == totalItemCount && vH >= bottomPos) {
                            if (preLastPosition != lastItemPosition) {
                                mainPresenter.loadMore();
                            }
                            preLastPosition = lastItemPosition;
                        }
                        else {
                            preLastPosition = lastItemPosition;
                        }
                    }
                }
            };


    private void initialize() {
        mainPresenter.attachViewMVP(this);
        mainPresenter.setTotalAndPage(results.size());
        mainPresenter.setGender(gender);

        if (results.isEmpty())
            mainPresenter.initialize();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mainPresenter.cancelCall();
    }

    @Override public void onDestroy() {
        mainPresenter.detachViewMVP();
        super.onDestroy();
    }


    @Override
    public void showProgress() {
        if (progressView != null) {
            progressView.setVisibility(View.VISIBLE);
            this.getActivity().setProgressBarIndeterminateVisibility(true);
        }
    }

    @Override
    public void hideProgress() {
        if (progressView != null) {
            progressView.setVisibility(View.GONE);
            this.getActivity().setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override
    public void showRetry() {
        noResultView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        noResultView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        this.showToastMessage(message);
    }

    @Override
    public void clearResults() {
        if (resultAdapter != null) {
            resultAdapter.clearAll();
            results.clear();
        }
    }

    @SuppressFBWarnings("BC_BAD_CAST_TO_CONCRETE_COLLECTION")
    @Override
    public void viewResults(List<Result> results) {
        if (resultAdapter != null) {
            resultAdapter.addAll(results);
        }
    }

    @Override
    public int sizeOfResult() {
        return resultAdapter.getItemCount();
    }

    @Override
    public void showFooter() {
        resultAdapter.insertFooter();
    }

    @Override
    public void removeFooter() {
        resultAdapter.removeFooter();
    }

    @Override
    public void storeGender(String gender) {
        this.gender = gender;
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

}