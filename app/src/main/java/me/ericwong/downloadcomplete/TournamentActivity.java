package me.ericwong.downloadcomplete;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import me.ericwong.downloadcomplete.fragments.InputOpponentFragment;
import me.ericwong.downloadcomplete.fragments.InputSetFormatFragment;
import me.ericwong.downloadcomplete.fragments.TournamentGameMeleeFragment;
import me.ericwong.downloadcomplete.interfaces.TournamentActivityInterface;

public class TournamentActivity extends AppCompatActivity implements TournamentActivityInterface {

    String opponentTag = "";
    int setFormat = 0;
    int currentGame = 0;

    public int getSetFormat(){
        return setFormat;
    }

    public int getCurrentGame(){
        return currentGame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tournament_action_bar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);  //example output: UofC weekly Oct 26 set 1
        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tournament_action_bar_menu, menu);
        return true;
    }

    public void updateActionBarText() {
        String tournamentName = TournamentsTable.last(TournamentsTable.class).name;

        getSupportActionBar().setTitle(tournamentName + " Set " + MeleeGamesTable.getCurrentSetCount());
    }

    public void promptForOpponentTag() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InputOpponentFragment fragment = new InputOpponentFragment();
        fragmentTransaction.add(R.id.input_opponent_container, fragment);
        fragmentTransaction.commit();
    }

    public void removeOpponentTagPrompt() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.input_opponent_container));
        fragmentTransaction.commit();
    }

    public void promptForSetFormat() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InputSetFormatFragment fragment = new InputSetFormatFragment();
        fragmentTransaction.add(R.id.input_set_format, fragment);
        fragmentTransaction.commit();
    }

    public void removeSetFormatPrompt() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.input_set_format));
        fragmentTransaction.commit();
    }

    @Override
    public void addOpponentTag(String tag) {
        opponentTag = tag;
        removeOpponentTagPrompt();
        updateOpponentTagText();
        addGameFragment();
    }

    @Override
    public void addSetFormat(int format) {
        setFormat = format;
        updateOpponentTagText();
        removeSetFormatPrompt();
        promptForOpponentTag();
    }

    public void updateOpponentTagText() {
        TextView textview = (TextView) findViewById(R.id.opponent_tag_view);
        textview.setText("Bo" + setFormat + " " + getString(R.string.vs) + " " + opponentTag);
    }

    public void addGameFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TournamentGameMeleeFragment fragment = new TournamentGameMeleeFragment();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    public void removeGameFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.main_container));
        fragmentTransaction.commit();
    }
    
    public void submitGame (String playerChar, String opponentChar, String strike, String stage, int won){
        insertGameToDatabase(playerChar, opponentChar, strike, stage, won);
        currentGame++;
        removeGameFragment();
        startGame();
    }

    public void startGame(){
        if (!MeleeGamesTable.isFirstGameOfSet()) {
            opponentTag = MeleeGamesTable.getOpponentTag();
            setFormat = MeleeGamesTable.getSetFormat();
            currentGame = MeleeGamesTable.getCurrentGame();
            updateActionBarText();
            updateOpponentTagText();
            addGameFragment();
        } else {

            currentGame = 1;
            updateActionBarText();
            promptForSetFormat();
        }
    }

    public void insertGameToDatabase(String playerChar, String opponentChar, String strike, String stage, int won){
        String player_strike = "";
        String opponent_strike = "";
        if (currentGame != 1){
            if (MeleeGamesTable.wonLastGame()){
                player_strike = strike;
            } else {
                opponent_strike = strike;
            }
        }

        MeleeGamesTable gameEntry = new MeleeGamesTable(
                TournamentsTable.getTournamentName(),
                MeleeGamesTable.getCurrentSetCount(),
                setFormat,
                opponentTag,
                currentGame,
                playerChar,
                opponentChar,
                player_strike,
                opponent_strike,
                stage,
                won,
                System.currentTimeMillis() / 1000L
                );

        gameEntry.save();
    }
}