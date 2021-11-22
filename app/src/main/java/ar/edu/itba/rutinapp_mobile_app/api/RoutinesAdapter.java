package ar.edu.itba.rutinapp_mobile_app.api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.RoutineCardBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.RoutineViewModel;

public class RoutinesAdapter implements AdapterView.OnItemSelectedListener {
    private List<RoutineModel> routinesList;
    private RoutineViewModel routinesViewModel;
    private View view;
    private int host;


    public RoutinesAdapter(List<RoutineModel> routinesList, int host, RoutineViewModel routinesViewModel) {
        this.routinesList = routinesList;
        this.host = host;
        this.routinesViewModel = routinesViewModel;
    }

/*
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
 */

    public void updateRoutines(List<RoutineModel> routineCards) {
        routinesList.clear();
        routinesList.addAll(routineCards);
    }

    public void resetRoutines() {
        routinesList.clear();
    }
/*
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

 */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    static class RoutineViewHolder extends RecyclerView.ViewHolder {
        public RoutineCardBinding itemView;

        public RoutineViewHolder(@NonNull RoutineCardBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
