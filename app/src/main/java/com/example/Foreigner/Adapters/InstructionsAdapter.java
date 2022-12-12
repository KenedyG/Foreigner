package com.example.Foreigner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Foreigner.Models.InstructionsResponse;
import com.example.Foreigner.R;
import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstrutionsViewHolder>{

    Context context;
List<InstructionsResponse> list;

    public InstructionsAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstrutionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new InstrutionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull InstrutionsViewHolder holder, int position) {

        holder.textView_instructions_name.setText(list.get(position).name);
         holder.recycler_instructions_steps.setHasFixedSize(true);
         holder.recycler_instructions_steps.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
         InstructionStepAdapter stepAdapter = new InstructionStepAdapter(context,list.get(position).steps);
         holder.recycler_instructions_steps.setAdapter(stepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstrutionsViewHolder extends RecyclerView.ViewHolder {

        TextView textView_instructions_name;
        RecyclerView recycler_instructions_steps;
    public InstrutionsViewHolder (@NonNull View itemView){
        super(itemView);
        textView_instructions_name = itemView.findViewById(R.id.textView_instruction_name);
        recycler_instructions_steps = itemView.findViewById(R.id.recycler_instructions_steps);
    }

}
