package org.pineapple.core;

import org.pineapple.core.interfaces.MediaQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class inherits from MediaQueue abstract class, and implements is using a LinkedList.
 */
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
        this.invokeCallbacks();
    }

    @Override
    public Song popNextMedia()
    {
        Song s = this.queue.remove();
        this.invokeCallbacks();

        return s;
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
