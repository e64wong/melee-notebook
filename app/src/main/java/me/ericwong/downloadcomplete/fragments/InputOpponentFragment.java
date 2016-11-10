package me.ericwong.downloadcomplete.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import me.ericwong.downloadcomplete.R;
import me.ericwong.downloadcomplete.interfaces.TournamentActivityInterface;

/**
 * Created by root on 08/11/16.
 */

public class InputOpponentFragment extends Fragment{
    TournamentActivityInterface mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        mListener = (TournamentActivityInterface) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tournament_input_opponent, container, false);

        Button submit = (Button) rootView.findViewById(R.id.opponent_tag_submit);
        submit.setStateListAnimator(null);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getView().findViewById(R.id.opponent_tag_input);
                if (editText != null) {
                    Log.e("", "Value is: " + editText.getText());
                } else {
                    Log.e("", "EditText not found!");
                }

                if (editText.getText().toString().length() > 0) {
                    mListener.addOpponentTag(editText.getText().toString());
                }
            }
        });

        return rootView;
    }
}
