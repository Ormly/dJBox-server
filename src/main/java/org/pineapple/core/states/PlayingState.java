package org.pineapple.core.states;

import java.util.NoSuchElementException;

public class PlayingState extends JukeBoxState
{
    public PlayingState()
    {
        this.play();
    }


    @Override
    public void disable()
    {
        jb.setState(new DisabledState());
    }


    @Override
    public void handleSongFinished()
    {
        jb.setState(new WaitingState());
    }

    @Override
    public void pause()
    {
        jb.setState(new PausedState());
    }

    private void play()
    {
        try{
            jb.playSong(jb.popNextSong());
        }
        catch(NoSuchElementException e)
        {
            jb.setState(new WaitingState());
        }

    }
}
