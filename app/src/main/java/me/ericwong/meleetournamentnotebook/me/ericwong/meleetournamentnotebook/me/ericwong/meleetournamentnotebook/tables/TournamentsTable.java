package me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.me.ericwong.meleetournamentnotebook.tables;

import com.orm.SugarRecord;

/**
 * Created by root on 29/10/16.
 */

public class TournamentsTable extends SugarRecord {

    String name;
    int win_count;
    int loss_count;
    int placing;

    public TournamentsTable(){
    }

    public TournamentsTable(String name, int win_count, int loss_count, int placing){
        this.name = name;
        this.win_count = win_count;
        this.loss_count = loss_count;
        this.placing = placing;
    }
}
