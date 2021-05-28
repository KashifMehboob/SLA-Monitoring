/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichain;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Kashif
 */
public class PingStatus_SLA_Monitoring_Scaled {
    public static void main(String args[]) throws Exception {
                System.out.println("************************************************************************");
                System.out.println("Starting Blockchain Based Monitoring Service for Service Level Agreement");
                System.out.println("************************************************************************");
                Read_Status PingObj = new Read_Status();
                List<List<String>> rows;// = Arrays.asList();
                String EndPointFilePath="E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\Endpoints_SLA_MS\\Endpoints1.csv";
                rows=PingObj.SLA_Read(EndPointFilePath);
		int k=1;
                int j;
                 while (true) 
                    {  j=0;
                        System.out.println("***************************");
                        System.out.println("Monitoring Iteration No. "+k);
                        System.out.println("***************************");
                        for (List<String> rowData : rows)
                        //for (int j = 0; i < hostList.length; j++) 
                            {  
                               // j=0;
                                String url =rowData.toString();
                                url=url.replace(",","");
                                url=url.replace("[","");
                                url=url.replace("]","");
                                Read_Status.getStatus(url,j);
                                j++;
                            }
                        k++;
                        Thread.sleep(5000);
                        
                    }
	}
}
