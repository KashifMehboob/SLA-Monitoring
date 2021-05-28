
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kashif
 */
public class RunTimeServiceParameters {
    public static void main( String[] args ) throws IOException 
    {
        String row;
        int LoopCounter=0;
        int ResponseTime=0;
        

        
        try (BufferedReader csvReader = new BufferedReader(new FileReader("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\ServiceParameters.csv"))) {
        
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                // do something with the data
                if(LoopCounter >0)
                {
                    System.out.print("Domain: "+data[0]+", Availability: "+data[1]+", Response Time: "+data[2]+" sec.");
                    if (LoopCounter ==1)
                    {
                        ResponseTime=Integer.parseInt(data[2]);
                    }
                    System.out.println();
                }
                LoopCounter+=1;
            }       }
}}
