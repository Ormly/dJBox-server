package org.pineapple.core.interfaces;

import it.sauronsoftware.jave.EncoderException;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public interface IMultimediaInfoProvider
{
    IMediaFileMetadata getMediaFileMetadata(File file) throws TagException, IOException, EncoderException;
}
