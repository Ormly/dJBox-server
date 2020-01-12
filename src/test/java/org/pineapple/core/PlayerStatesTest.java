package org.pineapple.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import org.pineapple.core.states.JukeBoxState;
import org.pineapple.core.states.WaitingState;
import org.pineapple.db.DBConnection;
import org.pineapple.db.SongDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ExtendWith(ApplicationExtension.class)
public class PlayerStatesTest
{
    static private JukeBox j;
    static private SongDAO s;
    static private Connection c;
    static List<Song> songs = new ArrayList<>();

    @BeforeAll
    static void beforeAll()
    {
        j = JukeBox.getInstance();
        s = new SongDAO();
        c = DBConnection.getConnection(DBConnection.Database.MEDIA);

        songs = s.getAll();
    }

    @AfterEach
    //after each test, set the state back to waiting
    void afterEach() { j.setState(new WaitingState()); }

    @Test
    void initializedState()
    {
        System.out.println("Initialized state: " + j.getState());

        //check if the state is a waiting state
        Assertions.assertTrue(j.getState().toString().contains("Waiting"));

        afterEach();
    }

    @Test
    void playingTransitionState()
    {
        //get a random song
        Random rnd = new Random();
        Song s1 = songs.get(rnd.nextInt(songs.size()));

        //transition state
        j.addSongToQueue(s1.getId());

        //check state
        Assertions.assertTrue(j.getState().toString().contains("Playing"));

        try {
            Thread.sleep(3000);

        } catch(InterruptedException e)
        {
            System.out.println(e);
        }

        afterEach();
    }

    //TODO
    @Test
    void pausedTransitionState()
    {
        //get a random song
        Random rnd = new Random();
        Song s1 = songs.get(rnd.nextInt(songs.size()));
        j.addSongToQueue(s1.getId());

        try {
            Thread.sleep(3000);

        } catch(InterruptedException e)
        {
            System.out.println(e);
        }

        //transition state
        System.out.println("Now pausing!");
        j.pauseCurrentSong();

        Assertions.assertTrue(j.getState().toString().contains("Paused"));

        afterEach();
    }

    //TODO
    @Test
    void disabledTransitionState()
    {
        //get a random song
        Random rnd = new Random();
        Song s1 = songs.get(rnd.nextInt(songs.size()));
        j.addSongToQueue(s1.getId());

        try {
            Thread.sleep(3000);

        } catch(InterruptedException e)
        {
            System.out.println(e);
        }

        //transition state
        j.disableJukeBox();

        Assertions.assertTrue(j.getState().toString().contains("Disabled"));

        afterEach();
    }

    @Test
    void waitingTransitionState()
    {
        JukeBoxState state = new WaitingState();

        //transition state
        j.setState(state);

        Assertions.assertEquals(j.getState(), state);

        afterEach();
    }

}
