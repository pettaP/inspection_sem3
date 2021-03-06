/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Intergration.VehicleDataBaseHandeler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Intergration.*;
import Observer.InspectionObserver;

/**
 *
 * @author Peter
 */
public class ControllerTest {
    
    VehicleDataBaseHandeler     vdb;
    Printer             printer;
    String              regNum;
    InspectionObserver  obs;
    
    public ControllerTest() {
    }
   
    @BeforeClass
    public static void setUpClass() {   
    }
    
    @AfterClass
    public static void tearDownClass() {     
    }
    
    @Before
    public void setUp() {
        regNum = "abc123";
        printer = new Printer();
        vdb = new VehicleDataBaseHandeler();
        obs = new InspectionObserver() {
            @Override
            public void stateHasChanged(boolean finalIns) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
    
    @After
    public void tearDown() {
        regNum = null;
        printer = null;
        vdb = null;
    }

    /**
     * Test of startInspection method, of class Controller is assumed to function correct since its methods are tested in the Class Garage
     */
    

    /**
     * Test of closeDoor method, of class Controller, is assumed to function correct since its methods are tested in the Class Garage
     */

    /**
     * Test of searchInspectionCost method, of class Controller.
     * If amount returned by method is 70 the test is passed
     */
    @Test
    public void testSearchInspectionCost() throws DataBaseAccesException {
        System.out.println("searchInspectionCost");
        Controller instance = new Controller(vdb, printer, obs);
        int expResult = 70;
        int result = instance.searchInspectionCost(regNum);
        assertEquals(expResult, result);
    }

    /**
     * Test of payCard method, of class Controller.
     * If returned comment matches expResult test is passed
     */
    @Test
    public void testPayCard() throws DataBaseAccesException {
        System.out.println("payCard");
        int pin = 1234;
        String cardNumber = "1234567890";
        String cardHolder = "Tomas Andersson";
        int expiryDate = 21;
        int cvc = 331;
        Controller instance = new Controller(vdb, printer, obs);
        instance.searchInspectionCost(regNum);
        instance.payCard(pin, cardNumber, cardHolder, expiryDate, cvc);
        String expResult = "Thank you, welcome back";
        String result = printer.getPrinterStatus();
        assertEquals (expResult, result);
    }
    
    /**
     * If worng card number is entered the returned comemnt should match expresult
     */
    @Test
    public void testPayCardFalseNumber() throws DataBaseAccesException {
        System.out.println("payCard");
        int pin = 1234;
        String cardNumber = "1134567890";
        String cardHolder = "Tomas Andersson";
        int expiryDate = 21;
        int cvc = 331;
        Controller instance = new Controller(vdb, printer, obs);
        instance.searchInspectionCost(regNum);
        instance.payCard(pin, cardNumber, cardHolder, expiryDate, cvc);
        String expResult = "Card payment was declined";
        String result = printer.getPrinterStatus();
        assertEquals (expResult, result);
    }
   
    /**
     * Test of getNextSpecefiedInspection method, of class Controller.
     */
    @Test
    public void testGetNextSpecefiedInspection() throws DataBaseAccesException {
        System.out.println("getNextSpecefiedInspection");
        String isCurrentInspectionPassed = "";
        String currentInspectionResults = "";
        Controller instance = new Controller(vdb, printer, obs);
        instance.searchInspectionCost(regNum);
        String expResult = "Breaks";
        String result = instance.getNextSpecefiedInspection(isCurrentInspectionPassed, currentInspectionResults);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of printInspectionResults method, of class Controller.
     * If the returned String matches the result is passed.
     */
    @Test
    public void testPrintInspectionResults() throws DataBaseAccesException {
        System.out.println("printInspectionResults");
        Controller instance = new Controller(vdb, printer,obs);
        instance.searchInspectionCost(regNum);
        instance.printInspectionResults();
        String expResult = "ResultsPrinted";
        String result = printer.getPrinterStatus();
        assertEquals (expResult, result);
    }
    
}
