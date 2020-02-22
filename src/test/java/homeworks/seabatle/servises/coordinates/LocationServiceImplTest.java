package homeworks.seabatle.servises.coordinates;

import homeworks.seabatle.exception.parser.IncorrectInputParseExeption;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationServiceImplTest {
    private LocationService service;
    private String req1 = "C4";
    private String req2 = "C4 C7";
    private String req3 = "C4 D4";
    private String req4 = "C4 dq";
    private String req5 = "qwe";
    private String req6 = "";
    private String req7 = "C1 D29";
    private String req8 = "CC";
    private String req9 = "C A A";

    @Before
    public void setUp() throws Exception {
        service = new LocationServiceImpl();
    }

    @Test
    public void testGetCoordinates() {
        int[] expected1 = {23};
        int[] expected2 = {23,24,25,26};
        int[] expected3 = {23,33};

        assertArrayEquals(expected1,service.getCoordinates(req1));
        assertArrayEquals(expected2,service.getCoordinates(req2));
        assertArrayEquals(expected3,service.getCoordinates(req3));
    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testGetFake1Coordinates() {
        service.getCoordinates(req4);
    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testGetFake2Coordinates() {
        service.getCoordinates(req5);
    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testGetFake3Coordinates() {
        service.getCoordinates(req6);
    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testGetFake4Coordinates() {
        service.getCoordinates(req7);
    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testGetFake5Coordinates() {
        service.getCoordinates(req8);
    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testGetFake6Coordinates() {
        service.getCoordinates(req9);
    }
}