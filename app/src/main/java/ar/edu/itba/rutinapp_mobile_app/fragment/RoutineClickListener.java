package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.Navigation;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.activity.RoutineActivity;
import ar.edu.itba.rutinapp_mobile_app.view_model.RoutineViewModel;

public class RoutineClickListener implements View.OnClickListener {

    private RoutineViewModel routineViewModel;
    private int from;
    public static final int ROUTINES_ID = 1;
//    public static final int FAV_ID = 2;
    public static final int MY_ACTIVITY = 3;

    public RoutineClickListener(RoutineViewModel routineViewModel, int from) {
        this.routineViewModel = routineViewModel;
        this.from = from;
    }

    @Override
    public void onClick(View view) {
        int routineId = Integer.parseInt(((TextView) view.findViewById(R.id.routineTitle)).getText().toString());
        routineViewModel.getRoutineById(routineId);
        switch (from) {
            case ROUTINES_ID:
//                Intent intent = new Intent(view.getContext(), RoutineActivity.class);
//                break;
//            case FAV_ID:
//                ProfileFragmentDirections.ProfileFragmentToRoutineFragment action2 = ProfileFragmentDirections.actionProfileFragmentToRoutineFragment();
//                action2.setRoutineId(routineId);
//                Navigation.findNavController(view).navigate(action2);
//                break;
//            case MY_ACTIVITY:
//                MyActivityFragmentDirections.ActionMyActivityFragmentToRoutineFragment action3 = MyActivityFragmentDirections.actionMyActivityFragmentToRoutineFragment();
//                action3.setRoutineId(routineId);
//                Navigation.findNavController(view).navigate(action3);
//                break;
        }
    }
}