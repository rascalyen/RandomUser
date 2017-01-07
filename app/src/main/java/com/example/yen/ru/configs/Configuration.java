package com.example.yen.ru.configs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.yen.ru.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


public class Configuration {

    private Properties properties;
    private OkHttpClient okHttpClient;
    private Picasso picasso;


    @Inject public Configuration(@NonNull Context context) {
        this.properties = setProperties(context);
        this.okHttpClient = setOkHttpClient(context);
        this.picasso = setPicasso(context);
    }


    private Picasso setPicasso(@NonNull Context context) {

        return new Picasso.Builder(context) //.indicatorsEnabled(true)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e("com.example.yen.ru", "Can't load image: " + uri);
                    }
                }).build();
    }

    public Picasso getPicasso() {
        return picasso;
    }


    @SuppressFBWarnings("ICAST_INTEGER_MULTIPLY_CAST_TO_LONG")
    private OkHttpClient setOkHttpClient(@NonNull Context context) {

        return new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getCacheDir(),
                        properties.getProperty("diskCachePath")),
                        Integer.parseInt(properties.getProperty("diskCacheSizeMB")) * 1024 * 1024))
                .connectTimeout(Integer.parseInt(properties.getProperty("connectTimeoutSec")),
                        TimeUnit.SECONDS)
                .readTimeout(Integer.parseInt(properties.getProperty("readTimeoutSec")),
                        TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }


    private Properties setProperties(@NonNull Context context) {

        InputStream rawResource = context.getResources().openRawResource(R.raw.randomuser);
        Properties properties = new Properties();
        try {
            properties.load(rawResource);
        }
        catch (IOException e) {
            Log.e(Configuration.class.getSimpleName(), "Can't load properties: " + e.toString());
        }

        return properties;
    }

    public Properties getProperties() {
        return properties;
    }

}