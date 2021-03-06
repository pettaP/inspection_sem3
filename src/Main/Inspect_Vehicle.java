/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import ExternalSys.LogHandler;
import Intergration.DataBaseAccesException;
import Intergration.VehicleDataBaseHandeler;
import Intergration.Printer;
import View.*;
import java.io.IOException;

/**
 *
 * @author Peter
 */
public class Inspect_Vehicle {
    
    /**
     * Setup for the program. Calling constructors in the program
     * @param Args  
     * @throws java.io.IOException  
     */
    
    public static void main (String[] Args) throws IOException{
       
       VehicleDataBaseHandeler  vdb = null;
       try{
           LogHandler logger = LogHandler.getLogHandler();
           vdb = new VehicleDataBaseHandeler(logger);
       }
       catch(IOException e){
           System.out.println ("Running without logger");
       }
       Printer                  printer = new Printer();
       InspectionStatsView      insView = new InspectionStatsView();
       Controller               contrl = new Controller(vdb, printer, insView);
       View                     view = new View(contrl);
       
       
       view.runProgram();
   }
    
}
