package ar.edu.itba.rutinapp_mobile_app.api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.RoutineCardBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.RoutineViewModel;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.RoutineViewHolder> {
    private List<RoutineModel> routinesList;
    private RoutineViewModel routinesViewModel;
    private View view;
    private int host;


    public RoutinesAdapter(List<RoutineModel> routinesList, int host, RoutineViewModel routinesViewModel) {
        this.routinesList = routinesList;
        this.host = host;
        this.routinesViewModel = routinesViewModel;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RoutineCardBinding binding = DataBindingUtil.inflate(inflater, R.layout.routine_card, parent, false);
        view = binding.getRoot();
        return new RoutineViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        RoutineModel routine = routinesList.get(position);
        int id = routine.getCategory().getId();

        //holder.itemView.setRoutineData(routine);
        //holder.itemView.setClickListener(new RoutineClickListener(routinesViewModel, host));
    }

    @Override
    public int getItemCount() {
        return routinesList.size();
    }

    public void updateRoutines(List<RoutineModel> routineCards) {
        routinesList.clear();
        routinesList.addAll(routineCards);
        notifyDataSetChanged();
    }

    public void resetRoutines() {
        routinesList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class RoutineViewHolder extends RecyclerView.ViewHolder {
        public RoutineCardBinding itemView;

        public RoutineViewHolder(@NonNull RoutineCardBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
