package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.model.ExerciseModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentActiveRoutineBinding;

public class ActiveRoutineFragment extends Fragment {

    private FragmentActiveRoutineBinding binding;
    private ExerciseModel viewModel;

    private View view;
    private TextView title;
    private TextView timeExercise;
    private ProgressBar progressBar;

    private int currentCycle;
    private String cycleTitle;
    private ArrayList<ExerciseModel> currCycleIndex;
    private int currentExerciseIndex;
    private boolean finished;
    private ExerciseModel currentExercise;
    private boolean executed;
    private int status;

    private static final int WARMUP_CYCLE = 0;
    private static final int MAIN_CYCLE = 1;
    private static final int COOLDOWN_CYCLE = 2;
    private static final int PLAYING = 0;
    private static final int PAUSED = 1;
    private static final int NOTRUNNING = 2;
    private static final int REPS_TIME= 10;

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
        binding = FragmentActiveRoutineBinding.inflate(getLayoutInflater());
        title = binding.NameExercise1;
        //timeExercise = binding.timeExercise;
        view = binding.getRoot();

        binding.play.setOnClickListener(v -> playExecution());
        binding.pause.setOnClickListener(v -> pauseExecution());
        binding.nextExercise.setOnClickListener(v -> nextExecution());
        binding.previousExercise.setOnClickListener(v -> previousExecution());
        binding.previousExercise.setVisibility(View.GONE);
        binding.nextExercise.setVisibility(View.GONE);
        //return inflater.inflate(R.layout.fragment_active_routine, container, false);

        return view;
    }

    private void playExecution() {
        binding.play.setVisibility(View.INVISIBLE);
        binding.pause.setVisibility(View.VISIBLE);
        finished = false;
        status = PLAYING;

    }

    private void pauseExecution() {
        binding.play.setVisibility(View.VISIBLE);
        binding.pause.setVisibility(View.INVISIBLE);
        status = PAUSED;
    }

    private void nextExecution() {
        currentExerciseIndex++;

    }

    private void previousExecution() {
        currentExerciseIndex--;

    }
}