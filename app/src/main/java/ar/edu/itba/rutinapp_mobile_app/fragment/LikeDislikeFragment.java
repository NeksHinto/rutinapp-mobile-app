package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.edu.itba.rutinapp_mobile_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikeDislikeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikeDislikeFragment extends Fragment {

    View likeDislikeView;

    public LikeDislikeFragment() {
        // Required empty public constructor
    }

    public static LikeDislikeFragment newInstance(String param1, String param2) {
        LikeDislikeFragment likeDislike = new LikeDislikeFragment();

        return likeDislike;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        likeDislikeView = inflater.inflate(R.layout.fragment_like_dislike, container, false);

        return likeDislikeView;
    }
}