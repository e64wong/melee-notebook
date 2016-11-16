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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tournament_action_bar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        updateActionBarText(ab);  //example output: UofC weekly Oct 26 set 1
        if (MeleeGamesTable.isFirstGameOfSet() && opponentTag.equals("")) {
            promptForSetFormat();
        } else {
            //TODO set the proper value of opponentTag and setFormat
            updateOpponentTagText();
            addGame();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tournament_action_bar_menu, menu);
        return true;
    }

    public void updateActionBarText(ActionBar ab) {
        String tournamentName = TournamentsTable.last(TournamentsTable.class).name;

        ab.setTitle(tournamentName + " Set " + MeleeGamesTable.getCurrentSetCount());
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
        updateOpponentTagText();
        removeOpponentTagPrompt();
        addGame();
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

    public void addGame() {
        //TODO: take the set format and game format
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TournamentGameMeleeFragment fragment = new TournamentGameMeleeFragment();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }
}
