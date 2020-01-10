package org.pineapple.core;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPAlbumInfo
{
    /**
     * Returns url for album art cover
     * @param artist of song
     * @param album of song
     * @return url for album art cover
     * @throws IOException
     * @throws InterruptedException
     */
    public static String GetMediumCoverArt(String artist, String album)
    throws IOException, InterruptedException
    {
        String albumCoverURL = "";
        artist = artist.replace(" ","%20");
        album = album.replace(" ", "%20");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=0888e0e13e3258c685027516f7c3d018&artist="
                + artist + "&album=" + album + "&format=json")).GET().build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseString = response.body().toString();

        Pattern p = Pattern.compile("https://lastfm.freetls.fastly.net/i/u/174s/[a-z0-9]*.png");
        Matcher m = p.matcher(responseString);
        if(m.find())
            albumCoverURL = m.group(0);

        return albumCoverURL;
    }
}
