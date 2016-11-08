package me.ericwong.meleetournamentnotebook;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import me.ericwong.meleetournamentnotebook.fragments.InputOpponentFragment;
import me.ericwong.meleetournamentnotebook.interfaces.TournamentActivityInterface;

public class TournamentActivity extends AppCompatActivity implements TournamentActivityInterface{

    String opponentTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tournament_action_bar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        updateActionBarText(ab);  //example output: UofC weekly Oct 26 set 1
        if (MeleeGamesTable.isFirstGameOfSet()){
            promptForOpponentTag();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tournament_action_bar_menu, menu);
        return true;
    }

    public void updateActionBarText(ActionBar ab){
        String tournamentName = TournamentsTable.last(TournamentsTable.class).name;

        ab.setTitle(tournamentName + " Set " + MeleeGamesTable.getCurrentSetCount());
    }

    public void promptForOpponentTag(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InputOpponentFragment fragment = new InputOpponentFragment();
        fragmentTransaction.add(R.id.input_opponent_container, fragment);
        fragmentTransaction.commit();
    }

    public void removeOpponentTagPrompt(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.input_opponent_container));
        fragmentTransaction.commit();
    }

    @Override
    public void addOpponentTag(String tag) {
        opponentTag = tag;
        updateOpponentTagText();
    }

    public void updateOpponentTagText(){
        TextView textview = (TextView) findViewById(R.id.opponent_tag_view);
        textview.setText(getString(R.string.you_vs) + " " + opponentTag);
        removeOpponentTagPrompt();
    }
}
