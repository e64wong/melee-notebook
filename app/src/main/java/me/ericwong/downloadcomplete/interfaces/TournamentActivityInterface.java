package me.ericwong.downloadcomplete.interfaces;

/**
 * Created by root on 08/11/16.
 */

public interface TournamentActivityInterface {
    void addOpponentTag(String tag);

    void addSetFormat(int format);

    int getSetFormat();

    int getCurrentGame();

    void submitGame(String playerChar, String opponentChar, String strike, String stage, int won);
}
