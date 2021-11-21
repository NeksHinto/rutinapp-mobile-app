package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.view.View;
import android.widget.TextView;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.model.FavouritesModel;

public class FavouritesListener implements View.OnClickListener{

    private FavouritesModel favouritesModel;
    private boolean fav = false;


    public FavouritesListener(FavouritesModel favouritesModel) {
        this.favouritesModel = favouritesModel;
    }

    @Override
    public void onClick(View v) {
        int routineId = Integer.parseInt(((TextView) v.findViewById(R.id.routineTitle)).getText().toString());
        if(!fav) {
            favouritesModel.favRoutine(routineId);

            fav = true;
        }else{
            favouritesModel.unfavRoutine(routineId);
            fav = false;
        }
    }
}
