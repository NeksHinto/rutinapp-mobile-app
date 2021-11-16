package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    private String arg1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle a = getArguments();
        if(a!=null){
            arg1 = a.getString("RoutineId");
        }else {
            arg1=null;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeFragmentBinding binding = HomeFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ((MainNavActivity) getActivity()).setNavigationVisibility(true);

        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        Button firstCategory = rootView.findViewById(R.id.category1);
        Button secondCategory = rootView.findViewById(R.id.category2);
        Button thirdCategory = rootView.findViewById(R.id.category3);

        firstCategory.setOnClickListener((v) -> {

            NavController navController = Navigation.findNavController(v);
            Intent firstCat = new Intent( getActivity() ,SearchRoutinesFragment.class);
            startActivity(firstCat);
            SearchRoutinesFragment firstC = new SearchRoutinesFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.searchRoutine_fragment, firstC)
                    .addToBackStack(null)
                    .commit();

        });


        return view;
        //return inflater.inflate(R.layout.home_fragment, container, false);
    }
}