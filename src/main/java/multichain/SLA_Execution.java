/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kashif
 */
public class SLA_Execution {
    
    public static void main( String[] args ) throws IOException 
    {
                Read_Status SLO_Obj = new Read_Status();
                List<List<String>> rows;// = Arrays.asList();
                String SLOFilePath="E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\SLO.csv";
                rows=SLO_Obj.SLA_Read(SLOFilePath);
               
            Object [] SLO_Array=rows.toArray();
            System.out.println(SLO_Array[2].toString());
            String [] ResponseTimeArr = SLO_Array[2].toString().split(",");
            System.out.println(ResponseTimeArr[2]);
             }
        
    }
    
//}

