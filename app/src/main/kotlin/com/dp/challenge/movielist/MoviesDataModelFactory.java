package com.dp.challenge.movielist;

import com.dp.challenge.movielist.item.MovieListItemDataModel;
import com.dp.movies.service.MoviesResult.MovieDetails;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoviesDataModelFactory {

    @Inject
    public MoviesDataModelFactory() {
    }

    public List<MovieListItemDataModel> generateDataModels(List<MovieDetails> movieDetailsList) {

        List<MovieListItemDataModel> dataModels = new ArrayList<>();

        for (MovieDetails movieDetails : movieDetailsList) {
            MovieListItemDataModel dataModel = new MovieListItemDataModel(
                    movieDetails.getId(),
                    movieDetails.getPosterPath(),
                    movieDetails.getTitle(),
                    movieDetails.getReleaseDate(),
                    movieDetails.getOverview());
            dataModels.add(dataModel);
        }

        return dataModels;
    }
}
