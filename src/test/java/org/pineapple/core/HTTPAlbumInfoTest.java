package org.pineapple.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;

@DisplayName("HTTP Album Info Tests")
public class HTTPAlbumInfoTest
{
    @Test
    @DisplayName("Album Art Cover URL test")
    void AlbumArtCoverURLTest()
    {
        try
        {
            Assertions.assertEquals("https://lastfm.freetls.fastly.net/i/u/174s/11dd7e48a1f042c688bf54985f01d088.png",
                                    HTTPAlbumInfo.GetMediumCoverArt("Daft Punk", "Random Access Memories"));
        } catch(IOException e)
        {
            e.printStackTrace();
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
