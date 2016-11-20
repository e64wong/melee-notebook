package me.ericwong.downloadcomplete;

import com.orm.SugarRecord;

/**
 * Created by root on 29/10/16.
 */

public class TournamentsTable extends SugarRecord {

    String name;
    String game;
    int winCount;
    int lossCount;
    int placing;
    long start;
    long end;

    public TournamentsTable() {
    }

    public TournamentsTable(String name, String game, int winCount, int lossCount, int placing, long start, long end) {
        this.name = name;
        this.game = game;
        this.winCount = winCount;
        this.lossCount = lossCount;
        this.placing = placing;
        this.start = start;
        this.end = end;
    }

    public static boolean tableIsEmpty() {
        return (TournamentsTable.count(TournamentsTable.class) == 0);
    }

    public static String getTournamentName(){
        if (tableIsEmpty()){
            return "";
        } else {
            return TournamentsTable.last(TournamentsTable.class).name;
        }
    }

    public static void cancelTournament(){
        TournamentsTable tournament = TournamentsTable.last(TournamentsTable.class);
        tournament.delete();
    }

    public static void endTournament(){
        TournamentsTable tournament = TournamentsTable.last(TournamentsTable.class);
        tournament.end = System.currentTimeMillis() / 1000;
        tournament.save();
    }
}
