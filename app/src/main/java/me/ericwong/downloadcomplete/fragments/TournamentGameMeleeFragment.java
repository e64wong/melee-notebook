package me.ericwong.downloadcomplete.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ericwong.downloadcomplete.R;
import me.ericwong.downloadcomplete.interfaces.TournamentActivityInterface;

/**
 * Created by root on 13/11/16.
 */

//TODO: link to db
public class TournamentGameMeleeFragment extends Fragment {
    TournamentActivityInterface mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (TournamentActivityInterface) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tournament_game_melee, container, false);


        return rootView;
    }
}