package me.ericwong.downloadcomplete;

import com.orm.SugarRecord;

/**
 * Created by root on 28/10/16.
 */

public class MeleeGamesTable extends SugarRecord {
    String tournament;
    int set_number;
    int set_format;
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


    public MeleeGamesTable() {
    }

    public MeleeGamesTable(String tournament, int set_number, int set_format, String opponent, int game_number, String player_char, String opponent_char, String player_strike, String opponent_strike, String stage, boolean won, String player_notes, String flubs, String questions, String video_link, int date) {
        this.tournament = tournament;
        this.set_number = set_number;
        this.set_format = set_format;
        this.opponent = opponent;
        this.game_number = game_number;
        this.player_char = player_char;
        this.opponent_char = opponent_char;
        this.player_strike = player_strike;
        this.opponent_strike = opponent_strike;
        this.stage = stage;
        this.won = won;
        this.player_notes = player_notes;
        this.flubs = flubs;
        this.questions = questions;
        this.video_link = video_link;
        this.date = date;
    }

    public static boolean tableIsEmpty() {
        return (MeleeGamesTable.count(MeleeGamesTable.class) == 0);
    }

    public static int getSetformat() {
        if (tableIsEmpty()) return -1;
        return MeleeGamesTable.last(MeleeGamesTable.class).set_format;
    }

    public static int getCurrentSetCount() {

        String[] falseArray = {"0"};
        String[] trueArray = {"1"};

        if (tableIsEmpty()) {
            return 1;
        }

        if (MeleeGamesTable.count(MeleeGamesTable.class, "won = ?", falseArray) == getSetformat() //most recent game was set losing
                || MeleeGamesTable.count(MeleeGamesTable.class, "won = ?", trueArray) == getSetformat()) { //most recent game was set winning
            return MeleeGamesTable.last(MeleeGamesTable.class).set_number + 1;
        } else {
            return MeleeGamesTable.last(MeleeGamesTable.class).set_number;
        }
    }

    public static boolean isFirstGameOfSet() {
        String[] falseArray = {"0"};
        String[] trueArray = {"1"};

        if (tableIsEmpty()) {
            return true;
        }

        if (MeleeGamesTable.count(MeleeGamesTable.class, "won = ?", falseArray) == getSetformat() //most recent game was set losing
                || MeleeGamesTable.count(MeleeGamesTable.class, "won = ?", trueArray) == getSetformat()) { //most recent game was set winning
            return true;
        }

        return false;
    }
}