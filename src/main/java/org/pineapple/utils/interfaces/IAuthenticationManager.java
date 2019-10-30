package org.pineapple.utils.interfaces;

public interface IAuthenticationManager
{
    /*
    Verifies userName and password are known and matched,
    and provides a security token to be used in subsequent requests.
     */
    public boolean authenticate(String userName, String password, char[] token);

    /*
    Invalidates the security token of a logged in user.
     */
    public boolean logOut(String userName);

    /*
    Creates a new user in the system.
     */
    public boolean createUser(String userName, String password);
}
