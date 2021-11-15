package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.databinding.SearchRoutineFragmentBinding;

public class SearchRoutinesFragment extends Fragment {

    private SearchRoutineFragmentBinding binding;

    private NestedScrollView nestedScrollView;
    private RecyclerView recyclerView;
    private Spinner sortSpinner;
    private Spinner orderSpinner;

    public SearchRoutinesFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SearchRoutineFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        nestedScrollView = binding.scrollView;
        recyclerView = binding.recyclerView;

        ((MainNavActivity) getActivity()).setNavigationVisibility(true);

        return view;
    }


}
