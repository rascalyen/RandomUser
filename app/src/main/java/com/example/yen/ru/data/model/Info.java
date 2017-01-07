package com.example.yen.ru.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yenhuang on 1/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    @JsonProperty("seed")
    private String seed;
    @JsonProperty("results")
    private Integer results;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("version")
    private String version;


    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}