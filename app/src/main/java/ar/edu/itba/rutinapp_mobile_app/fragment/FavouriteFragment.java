package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentFavoriteBinding;

public class FavouriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private RecyclerView recyclerView;
    //private FavoritesViewModel favoritesViewModel;


    public FavouriteFragment() {
        // Required empty public constructor
    }

    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
                RoutineModel routine = (RoutineModel) v.getTag();
                //hacer get de favoritos en las rutinas
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