package com.harmoniamusic;

import java.io.IOException;

public class SongEditPlayController {
    private void goToHome() throws IOException {
        App.setRoot("homeTemplate");
        App.setBar("homeBar");
        App.setData("homeData");
    }

    private void openSongInfo() throws IOException {
        // TODO
    }
    
}
