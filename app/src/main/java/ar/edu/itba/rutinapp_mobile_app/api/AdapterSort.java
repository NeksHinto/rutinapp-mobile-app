package ar.edu.itba.rutinapp_mobile_app.api;

import android.view.View;
import android.widget.AdapterView;

import ar.edu.itba.rutinapp_mobile_app.view_model.RoutineViewModel;

public class AdapterSort implements AdapterView.OnItemSelectedListener {

    private RoutineViewModel viewModel;

    public AdapterSort(RoutineViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        viewModel.sortRoutines(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
