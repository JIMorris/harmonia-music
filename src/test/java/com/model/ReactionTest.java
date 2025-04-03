package com.model;

import static org.junit.Assert.*;
import org.junit.Test;



public class ReactionTest {

    @Test
    public void testReactionConstructorAndGetters() {
        User user = new User("username", "password", "John", "lastname");
        Reaction reaction = new Reaction(5, "Nice!", user);

        assertEquals(5, reaction.getRating());
        assertEquals("Nice!", reaction.getComment());
        assertEquals(user, reaction.getAuthor());
    }

    @Test
    public void testSetRating() {
        Reaction reaction = new Reaction(3, "Okay", new User("username", "password", "firstName", "Kate"));
        reaction.setRating(4);

        assertEquals(4, reaction.getRating());
    }

    @Test
    public void testSetComment() {
        Reaction reaction = new Reaction(3, "This could use improvement", new User("username", "password", "firstName","Luke"));
        reaction.setComment("Much better");

        assertEquals("Much better", reaction.getComment());
    }
}
