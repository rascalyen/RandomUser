package com.example.yen.ru.ui.mvp.mainpage;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.yen.ru.R;
import com.example.yen.ru.dependency.HasComponent;
import com.example.yen.ru.dependency.component.ActivityComponent;
import com.example.yen.ru.ui.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private static final String MAIN_TAG = "MAIN";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        initializeActivity();
    }

    private void preSetViews() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initializeActivity() {
        setSupportActionBar(toolbar);

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);

        if (mainFragment == null)
            addFragment(R.id.fl_base, MainFragment.newInstance(), MAIN_TAG);
        else
            replaceFragment(R.id.fl_base, mainFragment);
    }

    @OnClick(R.id.text_refresh)
    public void onRefresh() {
        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);
        
        if (mainFragment != null && mainFragment.isVisible())
            mainFragment.getMainPresenter().initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.female:
                getFemale();
                return true;

            case R.id.male:
                getMale();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getMale() {
        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);

        if (mainFragment != null && mainFragment.isVisible())
            mainFragment.getMainPresenter().getMale();
    }

    private void getFemale() {
        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentByTag(MAIN_TAG);

        if (mainFragment != null && mainFragment.isVisible())
            mainFragment.getMainPresenter().getFemale();
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

}