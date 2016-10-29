package me.ericwong.meleetournamentnotebook;

import com.orm.SugarRecord;

/**
 * Created by root on 28/10/16.
 */

public class GamesTable extends SugarRecord {
    String tournament;
    int set_number;
    String set_format;
    String opponent;
    int game_number;
    String player_char;
    String opponent_char;
    String player_strike;
    String opponent_strike;
    String stage;
    boolean won;
    String player_notes;
    String flubs;
    String questions;
    String video_link;
    int date;


    public GamesTable(){
    }

    public GamesTable(String tournament, int set_number, String set_format, String opponent, int game_number, String player_char, String opponent_char, String player_strike, String opponent_strike, String stage, boolean won, String player_notes, String flubs, String questions, String video_link, int date ){
        this.tournament         = tournament;
        this.set_number         = set_number;
        this.opponent           = opponent;
        this.game_number        = game_number;
        this.player_char        = player_char;
        this.opponent_char      = opponent_char;
        this.player_strike      = player_strike;
        this.opponent_strike    = opponent_strike;
        this.stage              = stage;
        this.won                = won;
        this.player_notes       = player_notes;
        this.flubs              = flubs;
        this.questions          = questions;
        this.video_link         = video_link;
        this.date               = date;
    }
}