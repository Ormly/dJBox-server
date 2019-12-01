package org.pineapple.core.interfaces;

import java.util.ArrayList;
import java.util.List;
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
    public abstract T popNextMedia();

    /**
     * Get a list of all objects currently in the queue
     * @return
     */
    public abstract List<T> getMedia();

    public void setOnQueueChanged(Runnable callback){

        this.onQueueChangedCallbacks.add(callback);
    }

    protected void invokeCallbacks(){
        for(Runnable r:this.onQueueChangedCallbacks){
            r.run();
        }
    }
}
