package org.pineapple.core;

import org.pineapple.core.interfaces.MediaQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class inherits from MediaQueue abstract class, and implements is using a LinkedList.
 */
public class SongQueue extends MediaQueue<Song>
{
    /**
     * Generates a new Song queue.
     */
    public SongQueue()
    {
        this.queue = new LinkedList<>();
    }

    /**
     * Pushes a Song object into the queue.
     * @param obj
     */
    @Override
    public void pushMedia(Song obj)
    {
        this.queue.add(obj);
        this.invokeCallbacks();
    }

    /**
     * Pops an object form the queue. Throws a NoSuchElementException if the queue is empty.
     * @return Song
     * @throws NoSuchElementException
     */
    @Override
    public Song popNextMedia() throws NoSuchElementException
    {
        Song s = this.queue.remove();
        this.invokeCallbacks();

        return s;
    }

    /**
     * Returns all songs contained in the queue.
     * @return List of songs in the queue
     */
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
