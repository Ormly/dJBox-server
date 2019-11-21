package org.pineapple.api;

import org.pineapple.core.AuthRequest;
import org.pineapple.core.AuthResponse;
import org.pineapple.core.JukeBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController
{
    @Autowired
    private JukeBox jb;

    /**
     * Attempts to authentication an existing user. If username and password match,
     * a valid token will be generated and returned to the caller.
     * Otherwise, an HTTP error 401 UNAUTHORIZED is returned.
     *
     * @param req: A json formatted authentication request containing two key value pairs:
     *           "userName": "username",
     *           "password": "pwd1234"
     * @return A json formatted auth response containing one key-value pair:
     *           "token": "sometokenvalue"
     */
    @PostMapping("/auth")
    public void authenticate(@RequestBody AuthRequest req, HttpServletResponse response)
    {
        // TODO: move token response from body to header!
        String token = jb.doAuthentication(req.getUserName(), req.getPassword());
        response.setHeader("token", token);
        response.setStatus(200);
    }
}
