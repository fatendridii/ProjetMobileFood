package com.example.reclamationcrud.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reclamationcrud.R;
import com.example.reclamationcrud.activities.DetailReclamationActivity;
import com.example.reclamationcrud.entities.Reclamation;

import java.util.List;

public class ReclamationAdapter extends RecyclerView.Adapter<ReclamationAdapter.RecViewHolder> {

    private List<Reclamation> reclamations;
    private Context context;

    public ReclamationAdapter(Context context, List<Reclamation> reclamations) {
        this.reclamations = reclamations;
        this.context = context;
    }
    public List<Reclamation> getReclamations(){
        return reclamations;
    }


    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reclamation, parent,false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        Reclamation reclamation = reclamations.get(position);
        holder.tvNom.setText(reclamation.getNumeroReclamation());
        holder.tvDate.setText(reclamation.getDateSoumission().toString());
        if(reclamation.isTraitee())
            holder.tv_state.setTextColor(ContextCompat.getColor(context, R.color.greenState)) ;
        else holder.tv_state.setTextColor(ContextCompat.getColor(context, R.color.red));
        holder.tv_state.setText(reclamation.isTraitee() ? "Traitée" : "En attente");
        holder.tvDescription.setText(reclamation.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent i =new Intent(context, DetailReclamationActivity.class);
            i.putExtra("id", reclamation.getId_reclamation());
            context.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return reclamations.size();
    }
public void setItem(List<Reclamation> reclamations1){
        reclamations.clear();
        reclamations =reclamations1;
        notifyDataSetChanged();

}
    public static class RecViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom;
        TextView tvDate;
        TextView tv_state;
        TextView tvDescription;


        public RecViewHolder(View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.tv_nom);
            tvDate = itemView.findViewById(R.id.tv_dateRecl);
            tv_state = itemView.findViewById(R.id.tv_state);
            tvDescription = itemView.findViewById(R.id.tv_descrip);
        }
    }

}
