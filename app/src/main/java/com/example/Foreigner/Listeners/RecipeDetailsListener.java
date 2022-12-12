package com.example.Foreigner.Listeners;

import com.example.Foreigner.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);

}
