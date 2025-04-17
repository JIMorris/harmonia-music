package com.harmoniamusic;

import java.util.ArrayList;

import com.model.MusicFacade;
import com.model.SongList;
import com.model.UserList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import com.model.Song;

public class SongListController {
    private static MusicFacade musicFacade = MusicFacade.getInstance();

    public static void loadsongs(int type) {
        // ArrayList<Song> songs;
        ListView<Song> listView = null;
        ObservableList<Song> songs;
        switch (type) {
            case 1:
                songs = FXCollections.observableArrayList(musicFacade.openMySongs());
                listView = new ListView<>(songs);
                break;
            case 2:
                songs = FXCollections.observableArrayList(musicFacade.openFavorites());
                listView = new ListView<>(songs);
                break;
            case 3:
                songs = FXCollections.observableArrayList(musicFacade.openPublicSongs());
                listView = new ListView<>(songs);
                break;

            default:
                break;
        }
         VBox root = new VBox(listView);
        Scene scene = new Scene(root, 300, 200);
    }
}
