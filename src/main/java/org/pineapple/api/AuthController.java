package org.pineapple.api;

import org.pineapple.core.AuthRequest;
import org.pineapple.core.JukeBox;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController
{
    /**
     * Attempts to authentication an existing user. If username and password match,
     * a valid token will be generated and returned to the caller.
     * Otherwise, an HTTP error 401 UNAUTHORIZED is returned.
     *
     * @param req: A json formatted authentication request containing two key value pairs:
     *             "userName": "username",
     *             "password": "pwd1234"
     * @return A json formatted auth response containing one key-value pair:
     * "token": "sometokenvalue"
     */
    @PostMapping("/auth")
    public void authenticate(@RequestBody AuthRequest req, HttpServletResponse response)
    {
        // Throws an exception if authentication failed
        String token = JukeBox.getInstance().doAuthentication(req.getUserName(), req.getPassword());

        // if successful returns token in the response header
        response.setHeader("token", token);
        response.setStatus(200);
    }

    /**
     * Logs out the user with the given token
     *
     * @param token
     */
    @GetMapping("/auth/logout")
    public void logOut(@RequestHeader("token") String token)
    {
        // throws an exception if token is invalid
        JukeBox.getInstance().validateToke(token);

        // invalidates the given token
        JukeBox.getInstance().logOutToken(token);
    }
}
