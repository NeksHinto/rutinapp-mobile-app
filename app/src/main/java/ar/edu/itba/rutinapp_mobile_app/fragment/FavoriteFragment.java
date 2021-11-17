package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.model.Routine;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private RecyclerView recyclerView;
    //private FavoritesViewModel favoritesViewModel;


    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        // Inflate the layout for this fragment
        binding = binding.inflate(getLayoutInflater());
        recyclerView = binding.favoriteRecyclerView;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View.OnClickListener favoriteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routine routine = (Routine) v.getTag();
                if(routine.getFavorite()) {
                    app.getRoutineRepository().addFavorite(routine.getId()).observe(getViewLifecycleOwner(), r->{});
                    Snackbar.make(v, getResources().getString(R.string.fav_added), Snackbar.LENGTH_LONG).show();
                } else {
                    app.getRoutineRepository().deleteFavorite(routine.getId()).observe(getViewLifecycleOwner(), r->{});
                    Snackbar.make(v, getResources().getString(R.string.fav_removed), Snackbar.LENGTH_LONG).show();
                }
            }
        };

        //movimiento del recycleView
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        recyclerView.setHasFixedSize(true);

    }
}