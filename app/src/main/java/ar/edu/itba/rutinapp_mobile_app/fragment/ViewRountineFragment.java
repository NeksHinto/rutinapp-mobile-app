package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ar.edu.itba.rutinapp_mobile_app.App;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.ApiRoutine;
import ar.edu.itba.rutinapp_mobile_app.api.ApiRoutineService;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentViewRoutineBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.RoutineViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ViewRountineFragment extends Fragment {

    private RoutineViewModel viewModel;
    private RatingBar ratingBar;

    private FragmentViewRoutineBinding binding;
    private RoutineModel routineModel;
    private FloatingActionButton favButton;

    private RecyclerView recyclerViewElong;
    private RecyclerView recyclerViewPrin;
    private RecyclerView recyclerViewEntrada;

    private TextView routineTitle;

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiRoutineService routinesService;
    private App app;

    View view;

    private FloatingActionButton favouriteBtn;
    private FloatingActionButton unFavouriteBtn;
    private FloatingActionButton shareBtn;
    private MaterialButton startBtn;

    int routineId;

    public ViewRountineFragment() {
        // Required empty public constructor
    }

//    public static ViewRountineFragment newInstance(String param1, String param2) {
//        ViewRountineFragment viewRoutine = new ViewRountineFragment();
//
//        return viewRoutine;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        viewRoutineView = inflater.inflate(R.layout.fragment_view_routine, container, false);
//
//        return viewRoutineView;
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentViewRoutineBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        this.routineTitle = binding.routTitle;
        this.ratingBar = binding.ratingBar;
        this.favButton = binding.floatingActionButtonFavorite;
        this.unFavouriteBtn = binding.floatingActionButtonUnFavorite;

        this.shareBtn = binding.floatingActionButtonShare;
        this.startBtn = binding.startRoutine;
        this.favouriteBtn = view.findViewById(R.id.floatingActionButtonFavorite);
        this.unFavouriteBtn = view.findViewById(R.id.floatingActionButtonUnFavorite);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        routinesService = new ApiRoutine(getActivity());
        app = (App) requireActivity().getApplication();

        int routineTemp = requireArguments().getInt("routineID");
        if (getArguments() != null) {
            routineId = getArguments().getInt("routineId");

            startBtn.setVisibility(View.VISIBLE);
        }
        if (routineTemp != 0){
            startBtn.setVisibility(View.GONE);
            routineId = requireArguments().getInt("routineID");
        }

    viewModel = new ViewModelProvider(getActivity()).get(RoutineViewModel.class);
        viewModel.getRoutineById(routineId);
        viewModel.getCurrentRoutine().observe(getViewLifecycleOwner(), routineModel -> {
        this.routineModel = routineModel;
        routineTitle.setText(routineModel.getName());
        viewModel.getFavouriteRoutines();
        viewModel.getUserFavouriteRoutines().observe(getViewLifecycleOwner(), favourites -> {
            boolean isFav = false;
            for (RoutineModel routine : favourites) {
                if (routine.getId() == routineId) {
                    isFav = true;
                    break;
                }
            }
            if (isFav) {
                favouriteBtn.setVisibility(View.GONE);
                unFavouriteBtn.setVisibility(View.VISIBLE);
                routineModel.setFavorite(true);
            } else {
                favouriteBtn.setVisibility(View.VISIBLE);
                unFavouriteBtn.setVisibility(View.GONE);
                routineModel.setFavorite(false);
            }
        });
        favouriteBtn.setOnClickListener(v -> {
            addFav(routineModel.getId());
            favouriteBtn.setVisibility(View.GONE);
            unFavouriteBtn.setVisibility(View.VISIBLE);
        });
        unFavouriteBtn.setOnClickListener(v -> {
            unFav(routineModel.getId());
            favouriteBtn.setVisibility(View.VISIBLE);
            unFavouriteBtn.setVisibility(View.GONE);
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                if(ratingBar.getRating()>0) {
                    viewModel.rateRoutine(routineId, (int) ratingBar.getRating());
                }
            }
        });
        shareBtn.setOnClickListener(v->{
            share();
        });

        startBtn.setOnClickListener(v->{
            NavDirections action = ViewRountineFragmentDirections.actionViewRountineFragmentToActiveRoutineFragment2();
            Navigation.findNavController(v).navigate(action);
        });

    });
    }

    private void addFav(int routId){
        viewModel.addFav(routId);
        routineModel.setFavorite(true);
    }

    private void unFav(int routId){
        viewModel.unFav(routId);
        routineModel.setFavorite(false);
    }

    private void share(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.rutinapp.com/Routines/" + routineId);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Mensaje");
        startActivity(shareIntent);
    }
}