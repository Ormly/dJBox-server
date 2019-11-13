package org.pineapple.api;

import org.pineapple.core.AuthRequest;
import org.pineapple.core.AuthResponse;
import org.pineapple.core.JukeBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController
{
    @Autowired
    private JukeBox jb;

    @PostMapping("/auth")
    public AuthResponse authenticate(@RequestBody AuthRequest req){
        return new AuthResponse(jb.doAuthentication(req.getUserName(), req.getPassword()));

    }
}
