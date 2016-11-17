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
    int won;
    long date;


    public MeleeGamesTable() {
    }

    public MeleeGamesTable(String tournament, int set_number, int set_format, String opponent, int game_number, String player_char, String opponent_char, String player_strike, String opponent_strike, String stage, int won, long date) {
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

        if (tableIsEmpty()) {
            return 1;
        }

        if (MeleeGamesTable.isFirstGameOfSet()){
            return MeleeGamesTable.last(MeleeGamesTable.class).set_number + 1;
        } else {
            return MeleeGamesTable.last(MeleeGamesTable.class).set_number;
        }
    }

    public static boolean isFirstGameOfSet() {

        if (tableIsEmpty()) {
            return true;
        }

        if ((MeleeGamesTable.find(MeleeGamesTable.class, "tournament = ? and setNumber = ? and won = ?", TournamentsTable.getTournamentName(), String.valueOf(MeleeGamesTable.last(MeleeGamesTable.class).set_number), "1")).size() == (getSetformat() / 2 + 1)
                || (MeleeGamesTable.find(MeleeGamesTable.class, "tournament = ? and setNumber = ? and won = ?", TournamentsTable.getTournamentName(), String.valueOf(MeleeGamesTable.last(MeleeGamesTable.class).set_number), "0")).size() == (getSetformat() / 2 + 1)){
            return true;
        }

        return false;
    }

    public static boolean wonLastGame() {
        return (MeleeGamesTable.last(MeleeGamesTable.class).won == 1);
    }

    public static String getOpponentTag() {
        return MeleeGamesTable.last(MeleeGamesTable.class).opponent;
    }

    public static int getSetFormat() {
        return MeleeGamesTable.last(MeleeGamesTable.class).set_format;
    }

    public static int getCurrentGame() {
        return MeleeGamesTable.last(MeleeGamesTable.class).game_number + 1;
    }
}