package com.example.zhanara.newsfeed.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.zhanara.newsfeed.Db.News;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Example {

    @SerializedName("source")
    @Expose
    private String source;

    @SerializedName("sortBy")
    @Expose
    private String sortBy;

    @SerializedName("articles")
    @Expose
    private List<News> news = null;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<News> getArticles() {
        return news;
    }

    public void setArticles(List<News> news) {
        this.news = news;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}