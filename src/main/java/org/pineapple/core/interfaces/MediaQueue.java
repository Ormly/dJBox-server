package org.pineapple.core.interfaces;

import java.util.List;
import java.util.Queue;

public abstract class MediaQueue<T>
{
    protected Queue<T> queue;
    /*
     Adds a media to the queue
     */
    public abstract void pushMedia(T obj);

    /*
    Removes the next media file from the queue and returns it
     */
    public abstract T popNextMedia();

    /*
    getsSongs from
     */
    public abstract List<T> getMedia();
}
