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

/**
 * This class extracts metadata from mp3 files.
 */
public class MP3MetadataProvider implements IMultimediaInfoProvider
{
    /**
     * Extracts the metadata an .mp3 sound file provides.
     *
     * @param file
     * @throws TagException
     * @throws IOException
     * @throws EncoderException
     * @throws InterruptedException
     */
    @Override
    public IMediaFileMetadata getMediaFileMetadata(File file)
    throws TagException, IOException, EncoderException, InterruptedException
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
                     duration,
                     HTTPAlbumInfo.GetMediumCoverArt(tag.getArtist(), tag.getAlbum()));
        return s;
    }

    /**
     * Returns the duration of an .mp3 file. Throws and EncoderException if encoding is
     * not correct.
     * @param file
     * @return double duration
     * @throws EncoderException
     */
    private double getDuration(File file) throws EncoderException
    {
        Encoder encoder = new Encoder();
        double d = 0.0;

        MultimediaInfo mi = encoder.getInfo(file);
        d = (double) mi.getDuration();
        return d;
    }

}
