package com.harmoniamusic;

import org.jfugue.player.Player;

public class HarmoniaMusic {
    public static void playNote(String note) {
        Player player = new Player();
        player.play(note);
    }
}
