package me.ericwong.downloadcomplete.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import junit.framework.Test;

import java.util.Objects;

import me.ericwong.downloadcomplete.MeleeGamesTable;
import me.ericwong.downloadcomplete.R;
import me.ericwong.downloadcomplete.TournamentActivity;
import me.ericwong.downloadcomplete.interfaces.TournamentActivityInterface;

/**
 * Created by root on 13/11/16.
 */

public class TournamentGameMeleeFragment extends Fragment {
    TournamentActivityInterface mListener;

    TextView gameCounter;

    RadioGroup strike1;
    RadioGroup strike2;
    RadioGroup stage1;
    RadioGroup stage2;

    int prevStrike1;
    int prevStrike2;
    int prevStage1;
    int prevStage2;

    Button winButton;
    Button looseButton;
    
    String strike = "";
    String stage = "";

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

        gameCounter = (TextView) rootView.findViewById(R.id.game_counter_bar);
        gameCounter.setText("Game " + mListener.getCurrentGame());

        winButton = (Button) rootView.findViewById(R.id.win_button);
        looseButton = (Button) rootView.findViewById(R.id.loose_button);
        //Buttons should not have height because they are on material already
        winButton.setStateListAnimator(null);
        looseButton.setStateListAnimator(null);
        looseButton.setEnabled(false);
        winButton.setEnabled(false);


        final Spinner playerSpinner = (Spinner) rootView.findViewById(R.id.player_character_spinner);
        final Spinner opponentSpinner = (Spinner) rootView.findViewById(R.id.opponent_character_spinner);

        ArrayAdapter<CharSequence> playerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.melee_characters, android.R.layout.simple_spinner_item);
        playerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerSpinner.setAdapter(playerAdapter);

        ArrayAdapter<CharSequence> opponentAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.melee_characters, android.R.layout.simple_spinner_item);
        opponentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opponentSpinner.setAdapter(opponentAdapter);

        if(!MeleeGamesTable.isFirstGameOfSet()){ //For convenience, reselect the characters from the last game
            String[] recentChars = MeleeGamesTable.getMostRecentMatchup();
            playerSpinner.setSelection(playerAdapter.getPosition(recentChars[0]));
            opponentSpinner.setSelection(opponentAdapter.getPosition(recentChars[1]));
        }

        winButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.submitGame(playerSpinner.getSelectedItem().toString(), opponentSpinner.getSelectedItem().toString(), strike, stage, 1);
            }
        });
        looseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.submitGame(playerSpinner.getSelectedItem().toString(), opponentSpinner.getSelectedItem().toString(), strike, stage, 0);
            }
        });

        //Selecting one row should uncheck the other and vice versa
        strike1 = (RadioGroup) rootView.findViewById(R.id.strike_row_1);
        strike2 = (RadioGroup) rootView.findViewById(R.id.strike_row_2);
        stage1 = (RadioGroup) rootView.findViewById(R.id.stage_row_1);
        stage2 = (RadioGroup) rootView.findViewById(R.id.stage_row_2);
        setRadioListeners();

        if (!requiresStrikes()){
            TextView strikeHeader = (TextView) rootView.findViewById(R.id.strike_header);
            strikeHeader.setVisibility(View.GONE) ;
            strike1.removeAllViews();
            strike2.removeAllViews();
        }


        return rootView;
    }

    public boolean requiresStrikes(){
        if (mListener.getSetFormat() == 5 || mListener.getCurrentGame() == 1){
            return false;
        }
        return true;
    }

    public void setRadioListeners(){
        strike1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && checkedId != prevStrike1) { //If this change is caused because the user clicked a different radio button
                    prevStrike1 = checkedId;
                    strike2.clearCheck();
                    prevStrike2 = -1;
                    updateStrikeHeader(checkedId);
                    updateButtons();
                }
            }
        });
        strike2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && checkedId != prevStrike2) {
                    prevStrike2 = checkedId;
                    strike1.clearCheck();
                    prevStrike1 = -1;
                    updateStrikeHeader(checkedId);
                    updateButtons();
                }
            }
        });
        stage1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && checkedId != prevStage1) {
                    prevStage1 = checkedId;
                    stage2.clearCheck();
                    prevStage2 = -1;
                    updateStageHeader(checkedId);
                    updateButtons();
                }
            }
        });
        stage2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && checkedId != prevStage2) {
                    prevStage2 = checkedId;
                    stage1.clearCheck();
                    prevStage1 = -1;
                    updateStageHeader(checkedId);
                    updateButtons();
                }
            }
        });
    }

    public void updateStrikeHeader(int radioID){
        TextView header = (TextView) getView().findViewById(R.id.strike_header);
        String headerText = getString(R.string.previous_strike);
        switch (radioID){
            case R.id.bf_strike:
                header.setText(headerText + ": BattleField");
                strike = "bf";
                break;
            case R.id.ys_strike:
                header.setText(headerText + ": Yoshi's");
                strike = "ys";
                break;
            case R.id.fod_strike:
                header.setText(headerText + ": Fountain");
                strike = "fod";
                break;
            case R.id.dl_strike:
                header.setText(headerText + ": Dreamland");
                strike = "dl";
                break;
            case R.id.fd_strike:
                header.setText(headerText + ": Final");
                strike = "fd";
                break;
            case R.id.ps_strike:
                header.setText(headerText + ": Stadium");
                strike = "ps";
                break;
        }
    }

    public void updateStageHeader(int radioID){
        TextView header = (TextView) getView().findViewById(R.id.stage_header);
        String headerText = getString(R.string.stage);
        switch (radioID){
            case R.id.bf_stage:
                header.setText(headerText + ": BattleField");
                stage = "bf";
                break;
            case R.id.ys_stage:
                header.setText(headerText + ": Yoshi's");
                stage = "ys";
                break;
            case R.id.fod_stage:
                header.setText(headerText + ": Fountain");
                stage = "fod";
                break;
            case R.id.dl_stage:
                header.setText(headerText + ": Dreamland");
                stage = "dl";
                break;
            case R.id.fd_stage:
                header.setText(headerText + ": Final");
                stage = "fd";
                break;
            case R.id.ps_stage:
                header.setText(headerText + ": Stadium");
                stage = "ps";
                break;
        }
    }

    public void updateButtons(){
        if ((strike.length() > 0 || !requiresStrikes()) && stage.length() > 0){
            if (strike.equals(stage)){
                looseButton.setEnabled(false);
                winButton.setEnabled(false);
                mListener.sendToast("Strike and Stage cannot be identical");
            } else {
                looseButton.setEnabled(true);
                winButton.setEnabled(true);
            }
        }
    }

}