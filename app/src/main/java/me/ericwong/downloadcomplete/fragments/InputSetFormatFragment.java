package me.ericwong.downloadcomplete.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import me.ericwong.downloadcomplete.R;
import me.ericwong.downloadcomplete.interfaces.TournamentActivityInterface;

/**
 * Created by root on 08/11/16.
 */

public class InputSetFormatFragment extends Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_tournament_input_set_format, container, false);

        Button submit = (Button) rootView.findViewById(R.id.set_format_submit);
        submit.setStateListAnimator(null);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
                if (spinner.getSelectedItem().toString().equals("Bo5")) {
                    mListener.addSetFormat(5);
                } else {
                    mListener.addSetFormat(3);
                }

            }
        });

        return rootView;
    }
}