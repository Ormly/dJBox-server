package org.pineapple.core;

import org.pineapple.core.interfaces.MediaQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SongQueue extends MediaQueue<Song>
{
    public SongQueue()
    {
        this.queue = new LinkedList<>();
    }

    @Override
    public void pushMedia(Song obj)
    {
        this.queue.add(obj);
    }

    @Override
    public Song popNextMedia()
    {
        return this.queue.remove();
    }

    @Override
    public List<Song> getMedia()
    {
        List<Song> list = new ArrayList<>();
        for(Song s : this.queue)
        {
            list.add(s);
        }

        return list;
    }
}
