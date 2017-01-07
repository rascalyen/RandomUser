package com.example.yen.ru.ui.mvp.detailpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yen.ru.R;
import com.example.yen.ru.data.model.Result;
import com.example.yen.ru.ui.BaseActivity;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailActivity extends BaseActivity {

    private static final String EXTRA_RESULT = "EXTRA_RESULT";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.image_result)
    ImageView poster;
    @Bind(R.id.text_dob)
    TextView dob;
    @Bind(R.id.text_phone)
    TextView phone;
    @Bind(R.id.text_email)
    TextView email;
    @Bind(R.id.text_location)
    TextView location;
    @Inject Picasso picasso;
    private Result result;


    public static Intent getCalled(Context context, Result result) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_RESULT, result);
        return intent;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preSetViews();
        injectComponent();
        initializeActivity();
    }

    private void preSetViews() {
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    private void injectComponent() {
        activityComponent.inject(this);
    }

    private void initializeActivity() {
        result = getIntent().getParcelableExtra(EXTRA_RESULT);

        setupActionBar();
        setupResultDetail();
    }

    private void setupResultDetail() {

        picasso.load(result.getPicture().getLarge()).fit().into(poster);
        dob.setText(result.getDob());
        phone.setText(result.getPhone());
        email.setText(result.getEmail());

        StringBuilder sb = new StringBuilder();
        sb.append(result.getLocation().getStreet() + " | ")
                .append(result.getLocation().getCity() + ",  ")
                .append(result.getLocation().getState() + " ")
                .append(result.getLocation().getPostcode());

        location.setText(sb);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(result.getName().getFirst() + " " + result.getName().getLast());
    }
}