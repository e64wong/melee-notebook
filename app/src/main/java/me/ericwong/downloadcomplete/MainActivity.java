package me.ericwong.downloadcomplete;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.ericwong.downloadcomplete.fragments.AddTournamentDialogFragment;
import me.ericwong.downloadcomplete.interfaces.MainActivityInterface;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TournamentsTable.count(TournamentsTable.class);
        updateGameCount();
        updateTournamentCount();
        updateTournamentButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateGameCount();
        updateTournamentCount();
        updateTournamentButton();
    }

    private boolean isInTournament() {
        return (TournamentsTable.count(TournamentsTable.class) != 0 && TournamentsTable.last(TournamentsTable.class).end == 0);
    }

    public void updateGameCount() {
        long gameCount = MeleeGamesTable.count(MeleeGamesTable.class);
        TextView gameCountText = (TextView) findViewById(R.id.game_count);
        String countString;
        if (gameCount == 1) {
            countString = "1 Game Played";
        } else {
            countString = String.valueOf(gameCount) + " Games Played";
        }
        gameCountText.setText(countString);
    }

    public void updateTournamentCount() {
        long gameCount = TournamentsTable.count(TournamentsTable.class);
        TextView tournamentCount = (TextView) findViewById(R.id.tournament_count);
        String countString;
        if (gameCount == 1) {
            countString = "1 Tournament Saved";
        } else {
            countString = String.valueOf(gameCount) + " Tournaments Saved";
        }
        tournamentCount.setText(countString);
    }

    public void updateTournamentButton() {
        TextView tournamentButton = (TextView) findViewById(R.id.tournament_button);
        if (isInTournament()) {
            tournamentButton.setText(R.string.resume_tournament);
        } else {
            tournamentButton.setText(R.string.new_tournament);
        }
    }

    public void tournamentButtonClick(View view) {
        if (!isInTournament()) {
            addTournament();
        } else {
            Intent intent = new Intent(this, TournamentActivity.class);
            startActivity(intent);
        }
    }

    public void pastTournamentsButtonClick(View view) {
        Intent intent = new Intent(this, PastTournamentsActivity.class);
        startActivity(intent);
    }

    public void playerButtonClick(View view) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    public void statsButtonClick(View view) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);

    }

    public void insertTournamentToDatabase(String text) {
        if (text.length() > 0) {
            TournamentsTable tournament = new TournamentsTable(text, "MELEE", 0, 0, 0, (System.currentTimeMillis() / 1000), 0);
            tournament.save();
            Intent intent = new Intent(this, TournamentActivity.class);
            startActivity(intent);
        }
    }

    public void addTournament() {
        FragmentManager fm = getFragmentManager();
        AddTournamentDialogFragment dialogFragment = new AddTournamentDialogFragment();
        dialogFragment.show(fm, "newTournamentDialogFragment");
    }

}

