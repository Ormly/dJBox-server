package org.pineapple.core;

import java.util.HashMap;

public class MediaLibrary
{
    public static HashMap<Integer,String> songs = new HashMap<Integer,String>(){{
        put(1,"Song1");
        put(2,"Song2");
        put(3,"song3");
    }};
    public static HashMap<Integer,String> getSongList(){
        return songs;
    }
}
