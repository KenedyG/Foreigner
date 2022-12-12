package com.example.Foreigner.Listeners;

import com.example.Foreigner.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response,String message);
    void didError(String message);

}
