package com.example.anew.weather.Uutil;

/**
 * Created by new on 2019/11/25.
 */

public class NewsResult {
    private String reason;
    private News result;
    private String error_code;

    public NewsResult(String reason, News result, String error_code) {
        this.reason = reason;
        this.result=result;
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public News getNews() {
        return result;
    }

    public void setNews(News news) {
        this.result = news;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
