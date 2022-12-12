package com.example.Foreigner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Foreigner.Listeners.RecipeClickListener;
import com.example.Foreigner.Listeners.RecipeDetailsListener;
import com.example.Foreigner.Models.SimilarRecipeResponse;
import com.example.Foreigner.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeAdapter.SimilarRecipeViewHolder>{
    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeDetailsListener recipeDetailsListener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe,parent,false));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
    holder.textView_similar_title.setText(list.get(position).title);
    holder.textView_similar_title.setSelected(true);
    holder.getTextView_similar_serving.setText(list.get(position).servings+" Persons");
        Picasso.get().load(" https://spoonacular.com/recipeImages/"+list.get(position).id+"-90x90."+list.get(position).imageType).into(holder.imageView_similar);
        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            listener.onRecipeClick(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {
        CardView similar_recipe_holder;
        TextView textView_similar_title, getTextView_similar_serving;
        ImageView imageView_similar;
        public SimilarRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
            textView_similar_title = itemView.findViewById(R.id.textView_similar_title);
            getTextView_similar_serving = itemView.findViewById(R.id.textView_similar_serving);
            imageView_similar = itemView.findViewById(R.id.imageview_similar);
        }
    }
}
