package org.pineapple.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class is used to generate test cases for the getSha256 method
 */
@DisplayName("AuthenticationManager Tests")
public class AuthenticationManagerTest
{

    @Test
    @DisplayName("Empty string test")
    void emptyStringTest()
    {
        //pass
        //Note: e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855 is SHA-256 result for an empty string
        Assertions.assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
                                AuthenticationManager.getHash256(""));

        //fail
        Assertions.assertNotEquals("",
                                   AuthenticationManager.getHash256(""));
    }

    @Test
    @DisplayName("Arbitrary string test")
    void arbitraryStringTest()
    {
        //pass
        Assertions.assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
                                AuthenticationManager.getHash256("password"));

        //fail
        Assertions.assertNotEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d7",
                                   AuthenticationManager.getHash256("password1"));
    }

    @Test
    @DisplayName("Numbers string test")
    void numStringTest()
    {
        //pass
        Assertions.assertEquals("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
                                AuthenticationManager.getHash256("1234"));
        //fail
        Assertions.assertNotEquals("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4",
                                   AuthenticationManager.getHash256("123"));

    }

    @Test
    @DisplayName("White space string test")
    void whiteSpaceStringTest()
    {
        //pass
        Assertions.assertEquals("9180af6d3d4e099c8d730bd5d88aa5d211bfdf2053b24a993fee327a7ac883fe",
                                AuthenticationManager.getHash256("pass wor d"));

        //fail
        Assertions.assertNotEquals("9180af6d3d4e099c8d730bd5d88aa5d211bfdf2053b24a993fee327a7ac883fe",
                                   AuthenticationManager.getHash256("passwor d"));
    }

    @Test
    @DisplayName("All caps string test")
    void allCapsStringTest()
    {
        //pass
        Assertions.assertEquals("0be64ae89ddd24e225434de95d501711339baeee18f009ba9b4369af27d30d60",
                                AuthenticationManager.getHash256("PASSWORD"));

        //fail
        Assertions.assertNotEquals("0be64ae89ddd24e225434de95d501711339baeee18f009ba9b4369af27d30d60",
                                   AuthenticationManager.getHash256("PASSWORD1"));
    }

    @Test
    @DisplayName("Special characters string test")
    void specialCharsStringTest()
    {
        //pass
        Assertions.assertEquals("3478267b5612791b40988906b3a7897eb6ab501e04b95ed32f99d0afdf669d9c",
                                AuthenticationManager.getHash256("pässword"));

        //fail
        Assertions.assertNotEquals("3478267b5612791b40988906b3a7897eb6ab501e04b95ed32f99d0afdf669d9c",
                                   AuthenticationManager.getHash256("pässword1"));
    }

}
