package com.example.Foreigner.Listeners;

import com.example.Foreigner.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didfetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
