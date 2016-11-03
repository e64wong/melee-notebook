package me.ericwong.meleetournamentnotebook;

import android.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.fragments.AddTournamentDialogFragment;
import me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.interfaces.TournamentInterface;
import me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.tables.TournamentsTable;

public class TournamentActivity extends AppCompatActivity implements TournamentInterface {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tournaments_action_bar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.tournament_count);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(R.string.tournaments_action_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tournaments_action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_tournament:
                addTournament();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void addTournament(){
        FragmentManager fm = getFragmentManager();
        AddTournamentDialogFragment dialogFragment = new AddTournamentDialogFragment();
        dialogFragment.show(fm, "newTournamentDialogFragment");
    }

    public void instertTournamentToDatabase(String text){
        if (text.length() > 0) {
            TournamentsTable tournament = new TournamentsTable(text, 0, 0, -1);
            tournament.save();
        }
    }

}
