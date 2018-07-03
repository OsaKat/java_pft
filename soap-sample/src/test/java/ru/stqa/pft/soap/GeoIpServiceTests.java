package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.168.0.15");
        Assert.assertEquals(ipLocation, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }
}
