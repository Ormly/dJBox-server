package org.pineapple.core;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;
import org.pineapple.core.interfaces.IMediaFileMetadata;
import org.pineapple.core.interfaces.IMultimediaInfoProvider;

import java.io.File;
import java.io.IOException;

public class MP3MetadataProvider implements IMultimediaInfoProvider
{
    @Override
    public IMediaFileMetadata getMediaFileMetadata(File file) throws TagException, IOException, EncoderException
    {
        MP3File music = null;
        Song s = null;
        music = new MP3File(file.getPath());

        ID3v1 tag = music.getID3v1Tag();

        double duration = this.getDuration(file) / 1000.0;

        s = new Song(tag.getTitle(),
                     tag.getArtist(),
                     tag.getAlbum(),
                     Integer.valueOf(tag.getYear()),
                     tag.getSongGenre(),
                     file.getPath(),
                     duration);
        return s;
    }

    private double getDuration(File file) throws EncoderException
    {
        Encoder encoder = new Encoder();
        double d = 0.0;

        MultimediaInfo mi = encoder.getInfo(file);
        d = (double) mi.getDuration();
//        System.out.println("duration(sec) = "+  d / 1000.0);
        return d;
    }

}
