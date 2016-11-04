package me.ericwong.meleetournamentnotebook;

import com.orm.SugarRecord;

/**
 * Created by root on 29/10/16.
 */

public class TournamentsTable extends SugarRecord {

    String name;
    String game;
    int win_count;
    int loss_count;
    int placing;
    long start;
    long end;

    public TournamentsTable(){
    }

    public TournamentsTable(String name, String game, int win_count, int loss_count, int placing, long start, long end){
        this.name = name;
        this.game = game;
        this.win_count = win_count;
        this.loss_count = loss_count;
        this.placing = placing;
        this.start = start;
        this.end = end;
    }
}
