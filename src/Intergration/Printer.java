/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intergration;
import  Model.*;
import  Controller.*;

/**
 *
 * @author Peter
 */
public class Printer {
 
    String     printerStatus;
    
    public Printer(){
        
    }
    
    /**
     * Takes an boject of Receipt and uses the getters of the object to fill out a receipt that is printed to the customer
     * @param currentReceiptToPrint obejct of Receipt with all payment information stored
     */
    public void printCustomerReceipt(Receipt currentReceiptToPrint){
       System.out.println ("\n" + "The class Printer prints the recepit using informating from the class Receipt");
       System.out.println ();
       System.out.println ("Amount due: " + currentReceiptToPrint.getAmountDue());
       System.out.println ("Amonut received: " + currentReceiptToPrint.getAmountPaid());
       System.out.println ("Taxes included in price: " + currentReceiptToPrint.getVat());
       System.out.println (currentReceiptToPrint.getComment() + "\n");
       printerStatus = currentReceiptToPrint.getComment();
    }
    
    /**
     * Creates a print out for each component that was inspected and the status of the component and comments
     * @param currentResults accepts an array of specefied inspections
     */
    public void printResults (SpecefiedInspection[] currentResults ){
        System.out.println ("\n" + "Creating a result printout with the results and comments from the inspection.");
        for (int i = 0; i < currentResults.length; i++){
            System.out.println (currentResults[i].getComponentToInspect());
            System.out.println (currentResults[i].isInspectionPassed());
            System.out.println (currentResults[i].getInspectionComment());
            System.out.println ();
            printerStatus = "ResultsPrinted";
        }
    }
    
    /**
     * Used for testing program
     * @return a string prepresenting the last action of the printer
     */
    public String getPrinterStatus(){
        return this.printerStatus;
    }
}
