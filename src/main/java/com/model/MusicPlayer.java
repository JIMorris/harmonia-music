package com.model;

import com.harmoniamusic.*;
import java.lang.Thread;

public class MusicPlayer {

    public void playSong() {
        try {
            playLine1();
            Thread.sleep(300);
            playLine2();
            Thread.sleep(300);
            playLine3();
            Thread.sleep(300);
            playLine4();
            Thread.sleep(300);
            playLine5();
            Thread.sleep(300);
            playLine6();
            Thread.sleep(300);
            playLine7();
            Thread.sleep(300);
            playLine8();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playLine1() {
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("D");
        HarmoniaMusic.playNote("C");
        HarmoniaMusic.playNote("D");
    }
    public void playLine2() {
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("E");
    }
    public void playLine3() {
        HarmoniaMusic.playNote("D");
        HarmoniaMusic.playNote("D");
        HarmoniaMusic.playNote("D");
    }
    public void playLine4() {
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("G");
        HarmoniaMusic.playNote("G");
    }
    public void playLine5() {
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("D");
        HarmoniaMusic.playNote("C");
        HarmoniaMusic.playNote("D");
    }
    public void playLine6() {
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("E");
    }
    public void playLine7() {
        HarmoniaMusic.playNote("D");
        HarmoniaMusic.playNote("D");
        HarmoniaMusic.playNote("E");
        HarmoniaMusic.playNote("D");
    }
    public void playLine8() {
        HarmoniaMusic.playNote("C");
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.playSong();
    }
}
