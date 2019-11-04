package org.pineapple.core.interfaces;

public interface IMediaQueue
{
    /*
     Adds a media to the queue
     */
    void addMedia(String pathToMedia);

    /*
    Removes the next media file from the queue and returns it
     */
    String popNextMedia();
}
