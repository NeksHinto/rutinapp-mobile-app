package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.SearchRoutineFragmentBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.FavouriteViewModel;

public class SearchRoutinesFragment extends Fragment {

    private SearchRoutineFragmentBinding binding;

    private NestedScrollView nestedScrollView;
    private RecyclerView recyclerView;
    private Spinner sortSpinner;
    private Spinner orderSpinner;

    boolean searching = false; //si estoy scrolleando
    private String orderBy = "categoryId";
    private String direction = "asc";

    public SearchRoutinesFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SearchRoutineFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        nestedScrollView = binding.scrollView;
        recyclerView = binding.recyclerView;

//        ((MainNavActivity) getActivity()).setNavigationVisibility(true);

        nestedScrollView.setOnScrollChangeListener(
                (NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (!searching && !nestedScrollView.canScrollVertically(1)) {
                        searching = true;
                        //viewModel.updateData();
                    }
                });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //viewModel = new ViewModelProvider(getActivity()).get(RoutinesViewModel.class);
        setSpinners(view);
        if(savedInstanceState != null) {
            direction = savedInstanceState.getString("direction");
        }
        if(direction.equals("asc")) {
            binding.orderButton.setImageResource(R.drawable.ic_sort_asc);
        } else {
            binding.orderButton.setImageResource(R.drawable.ic_sort_desc);
        }

        getRoutines();

        binding.orderButton.setOnClickListener(v -> {
            if(direction.equals("asc")) {
                direction = "desc";
                binding.orderButton.setImageResource(R.drawable.ic_sort_desc);
            } else {
                direction = "asc";
                binding.orderButton.setImageResource(R.drawable.ic_sort_asc);
            }
            getRoutines();
        });

        FavouriteViewModel favouriteViewModel = new FavouriteViewModel(getActivity().getApplication());
        View.OnClickListener favouriteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoutineModel routine = (RoutineModel) v.getTag();
                if (routine.getFavorite()) {
                    favouriteViewModel.favRoutine(routine.getId());
                } else {
                    favouriteViewModel.unfavRoutine(routine.getId());
                }
            }
        };
    }

    private void setSpinners(View view) {
        sortSpinner = (Spinner) view.findViewById(R.id.sortDiscoverSpinner);
        ArrayAdapter<CharSequence> adapterSort = ArrayAdapter.createFromResource(this.getContext(), R.array.sort, android.R.layout.simple_spinner_item);
        adapterSort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapterSort);
        sortSpinner.setSelection(0, false);

    }

    public void getRoutines() {
    }

}
