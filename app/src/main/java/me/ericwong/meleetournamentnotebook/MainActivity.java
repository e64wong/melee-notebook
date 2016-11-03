package me.ericwong.meleetournamentnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.tables.MeleeGamesTable;
import me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.tables.TournamentsTable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TournamentsTable.count(TournamentsTable.class);
        updateGameCount();
        updateTournamentCount();
    }

    @Override
    public void onResume(){
        super.onResume();
        updateGameCount();
        updateTournamentCount();
    }

    public void updateGameCount(){
        long gameCount = MeleeGamesTable.count(MeleeGamesTable.class);
        TextView gameCountText = (TextView) findViewById(R.id.game_count);
        String countString = String.valueOf(gameCount) + " Games Played";
        gameCountText.setText(countString);
    }

    public void updateTournamentCount(){
        long gameCount = TournamentsTable.count(TournamentsTable.class);
        TextView tournamentCount = (TextView) findViewById(R.id.tournament_count);
        String countString = String.valueOf(gameCount) + " Tournaments Attended";
        tournamentCount.setText(countString);
    }

    public void tournamentsButtonClick(View view) {
        Intent intent = new Intent(this, TournamentActivity.class);
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

}

