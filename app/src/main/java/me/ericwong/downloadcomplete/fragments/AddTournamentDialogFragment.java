package me.ericwong.downloadcomplete.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import me.ericwong.downloadcomplete.R;
import me.ericwong.downloadcomplete.interfaces.MainActivityInterface;

/**
 * Created by root on 02/11/16.
 */

public class AddTournamentDialogFragment extends DialogFragment {
    MainActivityInterface mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (MainActivityInterface) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogfragment_add_tournament, container, false);
        getDialog().setTitle("Simple Dialog");

        Button dismiss = (Button) rootView.findViewById(R.id.submit);
        dismiss.setStateListAnimator(null);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getDialog().findViewById(R.id.tournament_name_edit_text);
                if (editText != null) {
                    Log.e("", "Value is: " + editText.getText());
                } else {
                    Log.e("", "EditText not found!");
                }

                mListener.insertTournamentToDatabase(editText.getText().toString());
                dismiss();

            }
        });

        return rootView;
    }

}