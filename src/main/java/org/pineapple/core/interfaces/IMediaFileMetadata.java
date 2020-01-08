package org.pineapple.core.interfaces;

import java.io.File;

public interface IMediaFileMetadata
{
    String getTitle();
    String getArtist();
    String getAlbum();
    String getGenre();
    double getDuration();
    int getYear();
}
