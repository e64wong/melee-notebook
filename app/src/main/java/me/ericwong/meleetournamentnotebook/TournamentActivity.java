package me.ericwong.meleetournamentnotebook;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class TournamentActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tournament_action_bar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        updateActionBarText(ab);  //example output:
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tournament_action_bar_menu, menu);
        return true;
    }

    public void updateActionBarText(ActionBar ab){
        String tournamentName = TournamentsTable.last(TournamentsTable.class).name;
        long setCount = 0;
        long setFormat = 0;
        if (MeleeGamesTable.count(MeleeGamesTable.class) != 0) {
            setCount = MeleeGamesTable.last(MeleeGamesTable.class).set_number;
            setFormat = MeleeGamesTable.last(MeleeGamesTable.class).set_format;
        }

        String[] falseArray = {"0"};
        String[] trueArray = {"1"};

        if (MeleeGamesTable.count(MeleeGamesTable.class) == 0 //no games played yet
                || MeleeGamesTable.count(MeleeGamesTable.class, "won = ?", falseArray) == setFormat //most recent game was set losing
                || MeleeGamesTable.count(MeleeGamesTable.class, "won = ?", trueArray) == setFormat) { //most recent game was set winning
            ab.setTitle(tournamentName + " Set " + String.valueOf(setCount + 1));
        } else {
            ab.setTitle(tournamentName + " Set " + String.valueOf(setCount));
        }
    }
}
