package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

public class SettingsController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    private void goToHome() throws IOException {
        App.setBar("homeBar");
        App.setData("homeData");
    }
}
