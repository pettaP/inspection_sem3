/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import  Main.*;
import  Controller.*;
import java.util.Scanner;

/**
 *
 * @author Peter
 */
public class View {
    
    Controller  contrl;
    int         inspectioncost;
    
    public View(Controller contrl){
        this.contrl = contrl;
    }
    
    public void runProgram (){
        
        contrl.startInspection();
        
        contrl.closeDoor();
        
        inspectioncost = contrl.searchInspectionCost("abc123");
        System.out.print ("The inspection cost is: " + inspectioncost);
        
        contrl.payCard(1234, "1234567890", "Tomas Andersson", 213, 311);
        
        Scanner     sc = new Scanner (System.in);
        System.out.println ("\n" + "press enter to start receive inspection item");
        String line1 = sc.nextLine();
        System.out.println ("Press enter to start");
        String line2 = sc.nextLine();
        
        while (contrl.arrayHasNext()){
            
            String nextSpecefiedInspection = contrl.getNextSpecefiedInspection(line1, line2);
            System.out.println (nextSpecefiedInspection);
            System.out.println ("Is inspection passed? pass/fail: ");
            line1 = sc.nextLine();
            System.out.println ("Comment to current inspection: ");
            line2 = sc.nextLine();
        }
        
        System.out.println  (contrl.getNextSpecefiedInspection(line1, line2));
        
        contrl.printInspectionResults();
    }
    
}