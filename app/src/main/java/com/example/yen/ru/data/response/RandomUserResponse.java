package com.example.yen.ru.data.response;

import com.example.yen.ru.data.model.Info;
import com.example.yen.ru.data.model.Result;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomUserResponse {

    @JsonProperty("results")
    private List<Result> results = null;
    @JsonProperty("info")
    private Info info;
    @JsonProperty("error")
    private String error;


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}