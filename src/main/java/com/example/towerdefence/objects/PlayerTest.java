package com.example.towerdefence.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class PlayerTest {

    Player player;

    /**
     * set up to create player object
     */
    @Before
    public void setUp() {
        player = new Player();
    }

    /**
     * test expected name which is non-empty, non-null, and has non-whitespace characters
     */
    @Test
    public void testSetExpectedName(){
        //return 0 error code
        assertEquals(player.setName("test"), 0);
        assertEquals(player.getName(), "test");
    }

    /**
     * checks expected name with whitespaces
     */
    @Test
    public void testSetExpectedNameWithWhitespace(){
        assertEquals(player.setName("test one"), 0);
        assertEquals(player.getName(), "test one");
    }

    /**
     * test error code (-1) for null name and checks that name was not changed
     */
    @Test
    public void testSetNullName() {
        player.setName("test");
        assertEquals(player.setName(null), -1);
        assertEquals(player.getName(), "test");
    }

    /**
     * tests error code (-1) when trying to set empty name and checks that name was
     * not changed
     */
    @Test
    public void testSetEmptyName() {
        assertEquals(player.setName(""), -1);
        assertNull(player.getName());
    }

    /**
     * tests error code (-1) when trying to set whitespace only name and checks that
     * name was not changed
     */
    @Test
    public void testSetWhiteSpaceName() {
        assertEquals(player.setName("    "), -1);
        assertNull(player.getName());
    }
}