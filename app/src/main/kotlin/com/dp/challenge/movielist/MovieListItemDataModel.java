package com.dp.challenge.movielist;

import androidx.annotation.Nullable;

public class MovieListItemDataModel {

    private static final String BANNER_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w92";
    private int id;
    private String bannerUrl;
    private String title;
    private String releaseDate;
    private String overView;

    public MovieListItemDataModel(
            int id,
            @Nullable String bannerUrl,
            String title,
            String releaseDate,
            String overView) {
        this.id = id;
        this.bannerUrl = bannerUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overView = overView;
    }

    public int getId() {
        return id;
    }

    public String getBannerUrl() {
        return BANNER_IMAGE_BASE_URL + bannerUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverView() {
        return overView;
    }
}
