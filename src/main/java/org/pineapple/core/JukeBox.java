package org.pineapple.core;

import org.pineapple.core.interfaces.IMediaLibrary;
import org.pineapple.core.interfaces.IMediaQueue;
import org.pineapple.core.interfaces.IPlayer;
import org.pineapple.db.UserDAO;
import org.pineapple.utils.interfaces.IAuthenticationManager;

import java.util.List;

public class JukeBox
{
    private IMediaLibrary libraryProvider;
    private IPlayer player;
    private IMediaQueue playlist;
    private IAuthenticationManager authenticationManager;

    public JukeBox(IMediaLibrary library, IPlayer player){
        this.libraryProvider = library;
        this.player = player;
        this.authenticationManager = new AuthenticationManager(new UserDAO());
    }

    public List<Media> getAllSongs(){
        return this.libraryProvider.getAllSongs();
    }

    public String doAuthentication(String userName, String password){
        return authenticationManager.authenticate(userName,password);
    }
}
