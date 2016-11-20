package me.ericwong.downloadcomplete.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import me.ericwong.downloadcomplete.MeleeGamesTable;
import me.ericwong.downloadcomplete.R;
import me.ericwong.downloadcomplete.TournamentActivity;
import me.ericwong.downloadcomplete.TournamentsTable;
import me.ericwong.downloadcomplete.interfaces.TournamentActivityInterface;

/**
 * Created by root on 19/11/16.
 */

public class TournamentChooseActionFragment extends Fragment{
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
        View rootView = inflater.inflate(R.layout.fragment_tournament_choose_action, container, false);

        Button nextSet = (Button) rootView.findViewById(R.id.next_set);
        nextSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.startGame();
            }
        });

        Button endTournament = (Button) rootView.findViewById(R.id.end_tournament);
        if(MeleeGamesTable.isFirstGameOfTournament()){
            endTournament.setText("cancel tournament");
        }
        endTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MeleeGamesTable.isFirstGameOfTournament()){
                    TournamentsTable.cancelTournament();
                } else {
                    TournamentsTable.endTournament();
                }
                NavUtils.navigateUpFromSameTask(getActivity());
            }
        });

        return rootView;
    }
}
