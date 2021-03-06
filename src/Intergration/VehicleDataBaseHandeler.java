/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intergration;

import ExternalSys.ConsoleLogger;
import ExternalSys.LogHandler;
import java.io.IOException;

/**
 *
 * @author Peter
 */
public class VehicleDataBaseHandeler {
    
    SpecefiedInspection[]   currentInspectionList;
    String                  searchedRegNum;
    String[]                legalRegNumbers = new String[5];
    Log                     logger;
    
    /**
     * The cinstructor creates an instance of a "dummy" SpeciefiedInspection list. 
     * This list contains objects of SpecefiedInspection with item be inspected and their cost
     * it also sets the attribut regNum to "abc123" to be matched with the parameter sent to the method in the class
     * @param logger
     * @throws java.io.IOException
     */
    public VehicleDataBaseHandeler(Log logger) throws IOException{
        this.logger = logger;
        SpecefiedInspection[]   temp                  = {   (new SpecefiedInspection("Breaks", 30, "Comment", "fail")), 
                                                            (new SpecefiedInspection("Steering", 50, "Comment", "pass" )), 
                                                            (new SpecefiedInspection("Window", 40, "Comment", "fail"))};
        this.currentInspectionList = temp;
        
        legalRegNumbers[0] = "ppp321";
        legalRegNumbers[1] = "abc123";
        legalRegNumbers[2] = "hej111";
        legalRegNumbers[3] = "cba123";
        legalRegNumbers[4] = "aaa333";
    }

    public VehicleDataBaseHandeler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method checks the regnumber in the parameter and returns a list of speciefied inspections if the parameter is the same as the attribute in the class
     * Creates a new list of the items that needs to be inspected. The components which are amrked as fail or false for isInspectionPassed
     * @param customerRegNum - the registration number of the vehicle that is about to be inspected
     * @return a list of specefied inspections and the cost of the specefied inspection
     * @throws Intergration.DataBaseAccesException
     */
    public SpecefiedInspection[] getInspectionList(String customerRegNum)throws DataBaseAccesException {
        this.searchedRegNum = customerRegNum;
        
        try{
            validateRegNum(customerRegNum);
        }
        catch(InvalidRegNumException e){
            logger.logException(e);
            throw new DataBaseAccesException(e);
        }
        
        int counter = 0;
        
        for (SpecefiedInspection currentInspectionList1 : currentInspectionList) {
            if (currentInspectionList1.isInspectionPassed() == false) {
                counter++;
            }
        }
        int currentIndex = 0;
        SpecefiedInspection[]   currentSpecefiedInspectionList = new SpecefiedInspection[counter];
        
        if (customerRegNum.equalsIgnoreCase(this.searchedRegNum)){
            for (SpecefiedInspection currentInspectionList1 : currentInspectionList){
                if (currentInspectionList1.isInspectionPassed() == false){
                    currentSpecefiedInspectionList[currentIndex] = currentInspectionList1;
                    currentIndex++;
                }
            }
        }
        System.out.println ("\n" + "Creating and returning a specefied inspection list based on the registration number and which current inspections there are to be made");
        return currentSpecefiedInspectionList; 
    }
    
    /**
     * used to create an exception. if the registartion number given doesnt match any of the list the value is 0 which causes an exception to be thrown in the method
     * @param customerRegNum the entered registration number
     * @throws InvalidRegNumException describing why the exception occurred
     */
    private void validateRegNum(String customerRegNum)throws InvalidRegNumException{
        int counter = 0;
        
        for (String legalRegNumber : legalRegNumbers)
            if(customerRegNum.equalsIgnoreCase(legalRegNumber))
                counter++;
        if (counter == 0)
            throw new InvalidRegNumException(customerRegNum);
        
    }
    
    /**
     * Changes the current logger used in the program for printing ot the exceptions
     * @param log the current logger to be used
     */
    public void setLog(Log log){
        this.logger = log;
    }
    
    /**
     * Method returns the current logger used for logging exceptions
     * @return the current logger used
     */
    public Log getLogger(){
        return this.logger;
    }
}
