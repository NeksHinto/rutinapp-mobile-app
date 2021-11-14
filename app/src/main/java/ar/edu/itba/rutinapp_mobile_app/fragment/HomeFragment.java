package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.HomeFragmentBinding;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
        super(R.layout.home_fragment);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeFragmentBinding binding = HomeFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ((MainNavActivity) getActivity()).setNavigationVisibility(true);

        return view;
        //return inflater.inflate(R.layout.home_fragment, container, false);
    }
}