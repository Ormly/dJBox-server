package org.pineapple.core.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public abstract class MediaQueue<T>
{
    protected Queue<T> queue;
    protected List<Runnable> onQueueChangedCallbacks = new ArrayList<>();

    /**
     * Adds an object of type T to the queue.
     * @param obj
     */
    public abstract void pushMedia(T obj);

    /**
     * Removes the next media file from the queue and returns it
     * @return
     */
    public abstract T popNextMedia() throws NoSuchElementException;

    /**
     * Get a list of all objects currently in the queue
     * @return
     */
    public abstract List<T> getMedia();

    /**
     * Event handler for when the currently playing song ends.
     * @param callback
     */
    public void setOnQueueChanged(Runnable callback){

        this.onQueueChangedCallbacks.add(callback);
    }

    /**
     * Invokes and runs the added Runnable callbacks.
     */
    protected void invokeCallbacks(){
        for(Runnable r:this.onQueueChangedCallbacks){
            r.run();
        }
    }
}
