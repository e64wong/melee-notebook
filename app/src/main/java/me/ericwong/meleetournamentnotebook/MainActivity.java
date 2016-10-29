package me.ericwong.meleetournamentnotebook;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TournamentsTable.count(TournamentsTable.class);
        updateGameCount();
    }

    public void updateGameCount(){
        long gameCount = GamesTable.count(GamesTable.class);
        TextView gameCountText = (TextView) findViewById(R.id.game_count);
        String countString = String.valueOf(gameCount) + " Games Played and Counting";
        gameCountText.setText(countString);
    }

    public void tournamentButtonClick(View view) {
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

