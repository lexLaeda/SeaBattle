package homeworks.seabatle.bean.ships.repository;


import homeworks.seabatle.bean.coordinates.Coordinate;
import homeworks.seabatle.bean.ships.*;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import homeworks.seabatle.exception.ship.ShipNotFoundExeption;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerShipsRepositoryTest {
    PlayerShipsRepository repository;

    FourDeckShip four;
    ThreeDeckShip three;
    TwoDeckShip two;
    OneDeckShip one;
    OneDeckShip oneExt;
    private static final String SUCCESS = "Ship successfully added!";
    @Before
    public void setUp() throws Exception {
        repository = new PlayerShipsRepository();

        Coordinate request1 = new Coordinate("B2 B5");
        four = new FourDeckShip();
        four.setShipCoords(request1);
        Coordinate request2 = new Coordinate("D1 D3");
        three = new ThreeDeckShip();
        three.setShipCoords(request2);
        Coordinate request3 = new Coordinate("F6 G6");
        two = new TwoDeckShip();
        two.setShipCoords(request3);
        Coordinate request4 = new Coordinate("B6");
        one = new OneDeckShip();
        one.setShipCoords(request4);
        Coordinate request5 = new Coordinate("J10");
        oneExt = new OneDeckShip();
        oneExt.setShipCoords(request5);



    }
    @Test
    public void testAddFourShip() {
        assertEquals(SUCCESS,repository.addShip(four));
        assertEquals(SUCCESS,repository.addShip(three));
        assertEquals(SUCCESS,repository.addShip(two));

    }
    @Test
    public void testAddOneShip() {
        assertEquals(SUCCESS,repository.addShip(three));
        assertEquals(SUCCESS,repository.addShip(two));
        assertEquals(SUCCESS,repository.addShip(one));
    }
    @Test(expected = ShipCreationRequestExeption.class)
    public void testFailToAdd(){
        repository.addShip(four);
        repository.addShip(three);
        repository.addShip(two);
        repository.addShip(one);
    }
    @Test()
    public void testFailMesToAdd(){
        assertEquals(SUCCESS,repository.addShip(four));
        assertEquals(SUCCESS,repository.addShip(three));
        assertEquals(SUCCESS,repository.addShip(two));
        try {
            repository.addShip(one);
        } catch (ShipCreationRequestExeption e){
            assertEquals("You can't create ship here,there" +
                    " is another ship in this coordinates",e.getMessage());
        }

    }
    @Test(expected = ShipNotFoundExeption.class)
    public void testIfNotFound(){
        repository.getShip(2,1);
    }
    @Test
    public void testGetShip() {
        repository.addShip(four);
        Ship ship4 = repository.getShip(1,2);
        assertThat(ship4,is(four));

        repository.addShip(three);
        Ship ship3 = repository.getShip(3,1);
        assertThat(ship3,is(three));

        repository.addShip(two);
        Ship ship2 = repository.getShip(5,5);
        assertThat(ship2,is(two));

        repository.delete(four);

        repository.addShip(one);
        Ship ship1 = repository.getShip(1,5);
        assertThat(ship1,is(one));

    }
    @Test
    public void testGetOneShip(){

        repository.addShip(oneExt);
        repository.addShip(one);
        repository.delete(one);


        repository.getShip(9,9);
        ;

    }

    @Test
    public void testUpdateShip() {
        repository.addShip(four);
        assertEquals(4,repository.getShip(1,2).getLifes());
        four.decrementLife();
        repository.updateShip(four);
        assertEquals(3,repository.getShip(1,2).getLifes());

    }

    @Test
    public void testClear() {
        assertEquals(SUCCESS,repository.addShip(four));
        assertEquals(SUCCESS,repository.addShip(three));
        assertEquals(SUCCESS,repository.addShip(two));
        assertEquals(3,repository.getSize());
        repository.clear();
        assertEquals(0,repository.getSize());
    }

    @Test
    public void testGetAll() {
        List<Ship> ships = Arrays.asList(four,three,two);
        assertEquals(SUCCESS,repository.addShip(four));
        assertEquals(SUCCESS,repository.addShip(three));
        assertEquals(SUCCESS,repository.addShip(two));
        assertEquals(ships,repository.getAll());
    }
    @Test
    public void testDelete() {
        repository.addShip(four);
        repository.delete(four);
    }
    @Test
    public void testGetSize() {
        repository.addShip(four);
        assertEquals(1,repository.getSize());
        repository.addShip(three);
        assertEquals(2,repository.getSize());
        repository.delete(four);
        assertEquals(1,repository.getSize());
    }


}