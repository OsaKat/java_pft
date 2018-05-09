package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

/**Created by Kate on 09.05.2018
 */

public class PointTest {
    @Test
    public void testDistance1 () {
        Point p1 = new Point(3,2);
        Point p2 = new Point(7,8);
        Assert.assertEquals(p1.distance(p2), 7.211102550927978);
    }

    @Test
    public void testDistance2 () {
        Point p1 = new Point(4,3);
        Point p2 = new Point(5,9);
        Assert.assertEquals(p1.distance(p2), 6.082762530298219);
    }
}