package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.databinding.SearchRoutineFragmentBinding;

public class SearchRoutinesFragment extends Fragment {

    private SearchRoutineFragmentBinding binding;

    private NestedScrollView nestedScrollView;
    private RecyclerView recyclerView;
    private Spinner sortSpinner;
    private Spinner orderSpinner;

    boolean searching = false; //si estoy scrolleando

    public SearchRoutinesFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SearchRoutineFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        nestedScrollView = binding.scrollView;
        recyclerView = binding.recyclerView;

        ((MainNavActivity) getActivity()).setNavigationVisibility(true);

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
    }

    private void setSpinners(View view) {
        sortSpinner = (Spinner) view.findViewById(R.id.sortDiscoverSpinner);
        ArrayAdapter<CharSequence> adapterSort = ArrayAdapter.createFromResource(getActivity(), R.array.sort, android.R.layout.simple_spinner_item);
        adapterSort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapterSort);

    }


}
