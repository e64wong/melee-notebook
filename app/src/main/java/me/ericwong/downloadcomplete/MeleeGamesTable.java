package me.ericwong.downloadcomplete;

import com.orm.SugarRecord;

/**
 * Created by root on 28/10/16.
 */

public class MeleeGamesTable extends SugarRecord {
    String tournament;
    int setNumber;
    int setFormat;
    String opponent;
    int gameNumber;
    String playerChar;
    String opponentChar;
    String playerStrike;
    String opponentStrike;
    String stage;
    int won;
    long date;


    public MeleeGamesTable() {
    }

    public MeleeGamesTable(String tournament, int setNumber, int setFormat, String opponent, int gameNumber, String playerChar, String opponentChar, String playerStrike, String opponentStrike, String stage, int won, long date) {
        this.tournament = tournament;
        this.setNumber = setNumber;
        this.setFormat = setFormat;
        this.opponent = opponent;
        this.gameNumber = gameNumber;
        this.playerChar = playerChar;
        this.opponentChar = opponentChar;
        this.playerStrike = playerStrike;
        this.opponentStrike = opponentStrike;
        this.stage = stage;
        this.won = won;
        this.date = date;
    }

    public static boolean tableIsEmpty() {
        return (MeleeGamesTable.count(MeleeGamesTable.class) == 0);
    }

    public static int getSetformat() {
        if (tableIsEmpty()) return -1;
        return MeleeGamesTable.last(MeleeGamesTable.class).setFormat;
    }

    public static int getCurrentSetCount() {

        if (tableIsEmpty()) {
            return 1;
        }

        if (MeleeGamesTable.isFirstGameOfSet()){
            return MeleeGamesTable.last(MeleeGamesTable.class).setNumber + 1;
        } else {
            return MeleeGamesTable.last(MeleeGamesTable.class).setNumber;
        }
    }

    public static boolean isFirstGameOfSet() {

        if (tableIsEmpty()) {
            return true;
        }

        if ((MeleeGamesTable.find(MeleeGamesTable.class, "tournament = ? and set_number = ? and won = ?", TournamentsTable.getTournamentName(), String.valueOf(MeleeGamesTable.last(MeleeGamesTable.class).setNumber), "1")).size() == (getSetformat() / 2 + 1)
                || (MeleeGamesTable.find(MeleeGamesTable.class, "tournament = ? and set_number = ? and won = ?", TournamentsTable.getTournamentName(), String.valueOf(MeleeGamesTable.last(MeleeGamesTable.class).setNumber), "0")).size() == (getSetformat() / 2 + 1)){
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
        return MeleeGamesTable.last(MeleeGamesTable.class).setFormat;
    }

    public static int getCurrentGame() {
        return MeleeGamesTable.last(MeleeGamesTable.class).gameNumber + 1;
    }

    public static String[] getMostRecentMatchup() {
        String[] returnValue = {"", ""};
        returnValue[0] = MeleeGamesTable.last(MeleeGamesTable.class).playerChar;
        returnValue[1] = MeleeGamesTable.last(MeleeGamesTable.class).opponentChar;
        return returnValue;
    }
}