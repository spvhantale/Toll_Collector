import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;

import com.model.PrintResult;
import com.service.TollCollection;


public class Geektrust {
    public static void main(String[] args)  {
        try {

            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
            	
               String inputCommand = sc.nextLine();
               
               String[] data=inputCommand.split(" ");
  
               TollCollection tollCollection=new TollCollection();
               
               if(data[0].equals("FASTAG")) {
            	   
            	   tollCollection.fasTagMethod(data);
            	   
               }else if(data[0].equals("COLLECT_TOLL")) {
            	   
            	   tollCollection.collectMethod(data);
            	   
               }else {
            	   
            	   PrintResult printResult=new PrintResult();
            	   
            	   printResult.printMethod();
               }
               
            }
            sc.close(); 
        } catch (IOException e) {
        }
	}
}
