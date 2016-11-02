package me.ericwong.meleetournamentnotebook;

import android.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TournamentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tournaments_action_bar);
        setSupportActionBar(toolbar);

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
        dialogFragment.show(fm, "Sample Fragment");
    }

}
