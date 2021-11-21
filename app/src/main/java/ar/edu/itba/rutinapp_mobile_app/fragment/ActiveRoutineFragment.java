package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.edu.itba.rutinapp_mobile_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActiveRoutineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiveRoutineFragment extends Fragment {

    public ActiveRoutineFragment() {
        // Required empty public constructor
    }

    public static ActiveRoutineFragment newInstance(String param1, String param2) {
        ActiveRoutineFragment fragment = new ActiveRoutineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active_routine, container, false);
    }
}