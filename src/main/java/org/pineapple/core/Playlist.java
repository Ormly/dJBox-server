/*
This class implements a media queue that can be used as a playlist
 */
package org.pineapple.core;

import org.pineapple.core.interfaces.MediaQueue;

import java.util.List;

public class Playlist extends MediaQueue<Song>
{
    @Override
    public void pushMedia(Song obj)
    {

    }

    @Override
    public Song popNextMedia()
    {
        return null;
    }

    @Override
    public List<Song> getMedia()
    {
        return null;
    }
}
