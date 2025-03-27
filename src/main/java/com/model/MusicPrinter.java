package com.model;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Prints music to a txt file
 * 
 * @author James Morris
 */
public class MusicPrinter {
    public static final String OUTPUT_FOLDER = "output/";
    public static final String[] BAR_LINE = new String[] {"|", "|", "|", "|", "|", "|", "|", "|", "|"};
    public static final String[] BLANK_SPACE = new String[] {"-", " ", "-", " ", "-", " ", "-", " ", "-"};

    /**
     * Prints the given Instruments part of the given Song to a txt file
     * 
     * @param song Song to print from
     * @param instrument Instrument to get music from
     * @throws Exception If there is a file error
     */
    public static void printSong(Song song, Instrument instrument) throws Exception{
        String title = song.getTitle();
        String author = song.getAuthor().getUsername();
        String key = "Quarter Note : q\n"+
                        "Eighth Note : e\n" +
                        "Triplet : t\n" +
                        "Sixteenth Note: s";
        String sheetMusic = getSheetMusic(song, instrument);

        String fileName = OUTPUT_FOLDER + title + ".txt";
        String printString = title + "\nby " + author + "\n\n" + key + "\n\n\n\n" + sheetMusic;

        FileWriter sheetMusicWriter = new FileWriter(fileName);
        sheetMusicWriter.write(printString);
        sheetMusicWriter.close();
    }

    /**
     * Generates the sheet music portion of the txt file
     * 
     * @param song Song to print from
     * @param instrument Instrument to get music from
     * @return Sheet music for the given song and instrument
     */
    private static String getSheetMusic(Song song, Instrument instrument){
        String formattedSheetMusic = "";
        ArrayList<String[]> tempSheetMusic = new ArrayList<>();
        tempSheetMusic.add(BAR_LINE);
        ArrayList<MeasureGroup> measureGroups = song.getMeasureGroups();
        for(int i=0; i<measureGroups.size(); i++){
            MeasureGroup measureGroup = measureGroups.get(i);
            ArrayList<String[]> measureSheetMusic = getMeasureSheetMusic(measureGroup.getMeasure(instrument));
            tempSheetMusic.addAll(measureSheetMusic);
            tempSheetMusic.add(BAR_LINE);
            if((i+1)%5==0){
                formattedSheetMusic += formatSheetMusic(tempSheetMusic);
                formattedSheetMusic += "\n\n\n";
                tempSheetMusic.clear();
                tempSheetMusic.add(BAR_LINE);
            }
        }
        if(!tempSheetMusic.isEmpty())
            formattedSheetMusic += formatSheetMusic(tempSheetMusic);
        return formattedSheetMusic;
    }

    /**
     * Generates the sheet music for the given measure
     * 
     * @param measure Measure to get music from
     * @return Sheet music for given measure
     */
    private static ArrayList<String[]> getMeasureSheetMusic(Measure measure){
        ArrayList<String[]> sheetMusic = new ArrayList<>();
        sheetMusic.add(BLANK_SPACE);
        sheetMusic.add(BLANK_SPACE);
        for(Note note : measure.getNotes()){
            sheetMusic.add(note.getSheetMusic());
            sheetMusic.add(BLANK_SPACE);
            sheetMusic.add(BLANK_SPACE);
        }
        return sheetMusic;
    }

    /**
     * Formats generated sheet music from being stored horizontally (ArrayList<String[]>)
     * to vertical (String)
     * 
     * @param sheetMusic Unformatted sheet music
     * @return sheet music formatted as a String
     */
    private static String formatSheetMusic(ArrayList<String[]> sheetMusic){
        String formattedSheetMusic = "";
        int height = sheetMusic.get(0).length;
        for(int i=0; i<height; i++){
            for(String[] section : sheetMusic)
                formattedSheetMusic += section[i];
            formattedSheetMusic += "\n";
        }
        return formattedSheetMusic;
    }
}
