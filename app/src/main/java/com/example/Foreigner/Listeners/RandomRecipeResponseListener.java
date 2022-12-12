package com.example.Foreigner.Listeners;

import com.example.Foreigner.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
void didFetch(RandomRecipeApiResponse response, String message);
void didError(String message);
}
