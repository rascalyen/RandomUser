package com.example.yen.ru.web;

import com.example.yen.ru.web.configuration.RestClientGenerator;
import com.example.yen.ru.web.service.RUService;
import java.util.Properties;
import javax.inject.Inject;
import okhttp3.OkHttpClient;


public class RUClient {

    private final String baseURL;
    private final RUService RUService;


    @Inject
    public RUClient(OkHttpClient okHttpClient, Properties properties) {
        this.baseURL = properties.getProperty("baseURL");
        RUService = RestClientGenerator.createService(RUService.class, okHttpClient, baseURL);
    }

    public String getBaseURL() {
        return baseURL;
    }

    public RUService getRUService() {
        return RUService;
    }

}