package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.edu.itba.rutinapp_mobile_app.R;

public class ViewRountineFragment extends Fragment {

    View viewRoutineView;

    public ViewRountineFragment() {
        // Required empty public constructor
    }

    public static ViewRountineFragment newInstance(String param1, String param2) {
        ViewRountineFragment viewRoutine = new ViewRountineFragment();

        return viewRoutine;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewRoutineView = inflater.inflate(R.layout.fragment_view_routine, container, false);

        return viewRoutineView;
    }
}