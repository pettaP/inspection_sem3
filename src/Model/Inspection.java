/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Intergration.SpecefiedInspection;
import Observer.*;
/**
 *
 * @author Peter
 */
public class Inspection {
    
    String                              regNum;
    SpecefiedInspection[]               inspectionList;
    int                                 costOfInspection;
    int                                 indexOfComponentToInspect;
    boolean                             arrayHasNext;
    String                              componentToInspect;
    boolean                             finalInspectionResult;
    InspectionObserver                  insObs;
    
    /**
     * The constructor creates a new Inspection and set the attributes of this to the current registartion number, 
     * the specified inspection list, and the totalt cost of the specefied inspections
     * @param regNum the registartion number of the vehicle to be inspected
     * @param inspectionList the list recevied from the class VehicleDataBase containing the inspections for the vehicle
     * @param insObs an instance of InspectionObserver, used for notifcation for the observing class
     */
    public Inspection (String regNum, SpecefiedInspection[] inspectionList,InspectionObserver insObs){
        this.regNum = regNum;
        this.inspectionList = inspectionList;
        this.costOfInspection = calculateCost(inspectionList);
        this.indexOfComponentToInspect = 0;
        this.arrayHasNext = true;
        this.finalInspectionResult = true;
        this.insObs = insObs;
        System.out.println ("\n" + "Creating a new inspection for vehicle");
    }
 
    /**
     * This method takes sends the inspection list to a method in the class SpeciedfiedInspection where the cost for the inspection is calculated and sent back
     * @param inspectionList contanins the specefied inspections
     * @return the total cost for all inspections of the vehicle
     */
    private int calculateCost(SpecefiedInspection[] inspectionList){
        int cost = 0;
        for (SpecefiedInspection inspectionList1 : inspectionList) {
            cost = cost + inspectionList1.getCostOfComponentToInspect();
        }
        System.out.println ("\n" + "Calculating the total cost for the inspection by adding the individual cost of components specefied in the list created by VDB");
        return cost;
    }
    /**
     * Gets the total inspection cost of the vehicle 
     * @return the total cost for all inspections on the vehicle
     */
    public int getInspectionCost (){
        System.out.println ("\n" + "Returning the calculated inspection cost");
        return this.costOfInspection;
    }
    
    /**
     * Returns the current specefied inspection for the vehicle
     * If list is finished it returns a satement informing this 
     * It also accepts comments and boolean value for if the inspection is passed. These values are stored to the element prior to the current elemtn to be returned
     * @param isCurrentInspectionPassed
     * @param currentInspectionResults
     * @return next current compnent to inspect
     */
    public String getNextSpecefiedInspection(String isCurrentInspectionPassed, String currentInspectionResults){
        if (indexOfComponentToInspect == inspectionList.length){
            componentToInspect = "End of inspection list. Inspection compleated";
            inspectionList[indexOfComponentToInspect - 1].setInspectionPassed(isCurrentInspectionPassed);
            inspectionList[indexOfComponentToInspect - 1].setInspectionResult(currentInspectionResults);
        }
        if (indexOfComponentToInspect < inspectionList.length) {
            if (indexOfComponentToInspect == 0){
            componentToInspect = inspectionList[indexOfComponentToInspect].getComponentToInspect();
            indexOfComponentToInspect = 1;
            }
            else{
            componentToInspect = inspectionList[indexOfComponentToInspect].getComponentToInspect();
            inspectionList[indexOfComponentToInspect - 1].setInspectionPassed(isCurrentInspectionPassed);
            inspectionList[indexOfComponentToInspect - 1].setInspectionResult(currentInspectionResults);
            indexOfComponentToInspect++;
                if (indexOfComponentToInspect == inspectionList.length)
                    arrayHasNext = false;
            }
        }
        return componentToInspect;
    }
    
    /**
     * returns the specefied inspection list
     * @return the specefied inspection list
     */
    public SpecefiedInspection[] getSpecefiedInspectionList(){
        return this.inspectionList;
    }
    
    /**
     * if the array has another element. To help while loop in view
     * @return true/false
     */
    public boolean arrayHasNext(){
        return this.arrayHasNext;
    }
    
    /**
     * Contacts the observer that the state has changed. Send the boolean result of the inspection so the observing class can store this result
     */
    public void checkState(){
        insObs.stateHasChanged(getFinalResult());
    }
    
    /**
     * Check if all the components for the vehilce passed inspection. If all passed the method returns true.
     * @return value describing if total inspection passed or failed
     */
    public boolean getFinalResult(){
        for(SpecefiedInspection spec : inspectionList){
            if(spec.isInspectionPassed()== false){
                finalInspectionResult = false;
            }
        }
        return finalInspectionResult;
    }
}
