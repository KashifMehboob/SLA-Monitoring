/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import multichain.command.MultichainException;
import multichain.command.RuntimeParameters;
import multichain.command.WalletTransactionCommand;
import multichain.object.BalanceAssetGeneral;
import org.apache.commons.lang.time.StopWatch;

/**
 *
 * @author Kashif
 */
public class Read_Status {
    
        
      public  List<List<String>> SLA_Read(String Path) throws FileNotFoundException, IOException 
    {  
        String row;
        List<List<String>> rows = new ArrayList<>();
        //try (BufferedReader csvReader = new BufferedReader(new FileReader("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\ServiceParameters.csv"))) 
        try (BufferedReader csvReader = new BufferedReader(new FileReader(Path))) 
        {
            while ((row = csvReader.readLine()) != null) 
            {
                 String[] data = row.split(",");
                rows.add(Arrays.asList(data));
             
            }  
            csvReader.close();
            
        }
        return(rows);
    } 
      public static String getStatus(String url, int IterationNumber) throws IOException 
        {       
                //Blockchain Setup Parameters
                RuntimeParameters RTP = new RuntimeParameters();
                RTP.setDatadir("C:\\Users\\Kashif\\AppData\\Roaming\\MultiChain");
                RTP.setRpcport("7748");
                WalletTransactionCommand Obj = new WalletTransactionCommand("localhost", "7748", "multichainrpc","6Zr3WeHWDUCxWxeAULrn23zt6YSeZY6ob6vTJuWbqA8S",RTP);
                BalanceAssetGeneral Obj2 = new BalanceAssetGeneral();
                Obj2.setAssetref("60-266-19659");
                Obj2.setName("SLA_Validating_Tokens");
                
                //Blockchain Setup Parameters
 
		String result = "";
                boolean violation = true; 
                long SLO_RT;
                String Monitoring_Service="1GxMTAdaNg94QogeHB5P6wXWTReMhpVx3ZtMWE";
                String To_EndPoint_Hash="";
                String To_EndPoint_Service_Name="";
                int Parallel_Running_Threads=3;
                String Response_Status="";
                String InternetStatus="";
                String TxID="";
                String OutputLogString,TransactionLog="";
		int code = 0;
                int AssetValue=0;
		try {
                        Path filePath = Paths.get("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\SuccessfulLogs.csv");
                        if (!Files.exists(filePath)) {
                        FileWriter csvWriter = new FileWriter("SuccessfulLogs.csv");
                        
                        csvWriter.append("Domain");
                        csvWriter.append(",");
                        csvWriter.append("Availability");
                        csvWriter.append(",");
                        csvWriter.append("ResponseTime");
                        csvWriter.append(",");
                        csvWriter.append("IterationNumber");
                        csvWriter.append(",");
                        csvWriter.append("Time Stamp");
                        csvWriter.append("\n");
                        }
                        Path filePath_NR = Paths.get("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\NotRespondedLogs.csv");
                        if (!Files.exists(filePath_NR)) {
                        FileWriter csvWriter_NR = new FileWriter("NotRespondedLogs.csv");
                        
                        csvWriter_NR.append("Domain");
                        csvWriter_NR.append(",");
                        csvWriter_NR.append("Availability");
                        csvWriter_NR.append(",");
                        csvWriter_NR.append("ResponseTime");
                        csvWriter_NR.append(",");
                        csvWriter_NR.append("IterationNumber");
                        csvWriter_NR.append(",");
                        csvWriter_NR.append("Time Stamp");
                        csvWriter_NR.append("\n");
                        }
                        
                        
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        StopWatch watch = new StopWatch();
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();
                        try
                        {
                            watch.start();
                            code = connection.getResponseCode();
                            if(200 <= code && code <= 399)
                            {
                                InternetStatus="Internet is running normally.";
                            }
                            else
                            {
                                InternetStatus="Internet is NOT available.";
                            }
                
                        }
                        catch (IOException e) 
                        {
                            e.printStackTrace();
                        } 
                        finally 
                        {
                            watch.stop();
                        }/*
                        System.out.println("Domain," + "\t\t\tAvaiability," + "ResponseTime");*/
                        //Read SLO_Data
                        Read_Status SLO_Obj = new Read_Status();
                        List<List<String>> SLO_rows;
                        String SLOFilePath="E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\SLO\\SLO15.csv";
                        SLO_rows=SLO_Obj.SLA_Read(SLOFilePath);
                        Object [] SLO_Array=SLO_rows.toArray();
                        //System.out.println(SLO_Array[IterationNumber+1].toString());
                        String [] ResponseTimeArr = SLO_Array[IterationNumber+1].toString().split(",");
                        //System.out.println(ResponseTimeArr[2]);
                        SLO_RT = Long.parseLong(ResponseTimeArr[2].trim());
                        To_EndPoint_Hash=ResponseTimeArr[3].trim();
                        To_EndPoint_Hash=To_EndPoint_Hash.replace(",","");
                        To_EndPoint_Hash=To_EndPoint_Hash.replace("[","");
                        To_EndPoint_Hash=To_EndPoint_Hash.replace("]","");
                        To_EndPoint_Service_Name=ResponseTimeArr[0].trim();
                        To_EndPoint_Service_Name=To_EndPoint_Service_Name.replace(",","");
                        To_EndPoint_Service_Name=To_EndPoint_Service_Name.replace("[","");
                        To_EndPoint_Service_Name=To_EndPoint_Service_Name.replace("]","");
                        long TimeMilli=watch.getTime();
			if (code == 200) 
                        {
                                AssetValue=1;
                                Response_Status="Normal";
                                result = "-> Yes \t" + "Code: " + code+" Msg: "+ connection.getResponseMessage()+" Response Time: "+watch.toString()+" ResponseTime(millisec): "+watch.getTime()+ " Time Stamp: "+timestamp.toLocalDateTime();
                            try (FileWriter csvWriter = new FileWriter("SuccessfulLogs.csv",true)) {
                                csvWriter.append(url);
                                csvWriter.append(",");
                                csvWriter.append("Yes");
                                csvWriter.append(",");
                                csvWriter.append(String.valueOf(TimeMilli));
                                csvWriter.append(",");
                                csvWriter.append(String.valueOf(IterationNumber));
                                csvWriter.append(",");
                                csvWriter.append(String.valueOf(timestamp.toLocalDateTime()));
                                csvWriter.append("\n");
                                csvWriter.flush();
                            }
                                
                                    
                                if (TimeMilli<=SLO_RT)
                                {
                                    violation=false;
                                }
                                else
                                {
                                    violation=true;
                                }
                               // System.out.println("Transaction is being sent at "+LocalDateTime.now()+" having blockchain address "+To_EndPoint_Hash + ", bearing endpoint domain name "+To_EndPoint_Service_Name+ " with violation status "+violation+" and response status "+Response_Status+"");
                                
                                    
                                
			} else 
                        {
                                AssetValue=0;
                                violation=true;
                                Response_Status="Not Responsive";
				result = "-> No \t" + "Code: " + code+" Msg: "+ connection.getResponseMessage()+" Response Time: "+watch.toString()+" ResponseTime(millisec): "+ " Time Stamp: "+timestamp.toLocalDateTime();
                                FileWriter csvWriter_NR = new FileWriter("NotRespondedLogs.csv",true);
                                csvWriter_NR.append(url);
                                csvWriter_NR.append(",");
                                csvWriter_NR.append("No");
                                csvWriter_NR.append(",");
                                csvWriter_NR.append(String.valueOf(TimeMilli));
                                csvWriter_NR.append(",");
                                csvWriter_NR.append(String.valueOf(IterationNumber));
                                csvWriter_NR.append(",");
                                csvWriter_NR.append(String.valueOf(timestamp.toLocalDateTime()));
                                csvWriter_NR.append("\n");
                                csvWriter_NR.flush();
                                csvWriter_NR.close();
			}
                        OutputLogString="Transaction is being sent at "+LocalDateTime.now()+" having blockchain address "+To_EndPoint_Hash + ", bearing endpoint domain name "+To_EndPoint_Service_Name+ " with violation status "+violation+" and response status/availability "+Response_Status+" and number of running instance(s) is/are "+Parallel_Running_Threads+".";
                        System.out.println(OutputLogString);
                        try
                        {
                        TxID=Obj.sendWithDataFrom(Monitoring_Service, To_EndPoint_Hash, "SLA_Validating_Tokens", AssetValue, " detailed "+OutputLogString+"");
                        }
                        catch(MultichainException ex)
                        {
                            System.out.println("Error"+ex.getMessage());
                        }
                        String Range="";
                        double PercentDiff,d_TimeMilli,d_SLO_RT;
                        d_TimeMilli=(double)TimeMilli;
                        d_SLO_RT=(double)SLO_RT;
                        if(TimeMilli<SLO_RT)
                        {
                            //PercentDiff=(d_TimeMilli/d_SLO_RT)*100;
                            Range="Within cut-off";
                        }
                        else
                        {
                            //PercentDiff=(d_SLO_RT/d_TimeMilli)*100;
                            Range="Exceeds cut-off";
                        }
                        PercentDiff=(d_TimeMilli/d_SLO_RT)*100;
                        String str = String.format("%1.2f", PercentDiff);
                        PercentDiff = Double.valueOf(str);
                        int IterNo=IterationNumber+1;
                        TransactionLog=TxID+","+Monitoring_Service+","+To_EndPoint_Hash + ","+To_EndPoint_Service_Name+","+violation+","+Response_Status+","+Parallel_Running_Threads+","+IterNo+","+SLO_RT+","+TimeMilli+","+PercentDiff+","+Range+","+InternetStatus;
                        System.out.println(TransactionLog);
                        //Write TransactionLog to log file here
                        Path filePathTransactionLog = Paths.get("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\TransactionLogs\\TransactionLog15_Loaded.csv");
                        if (!Files.exists(filePathTransactionLog)) 
                        {
                            try (FileWriter csvWriter = new FileWriter("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\TransactionLogs\\TransactionLog15_Loaded.csv")) 
                            {
                                csvWriter.append(TransactionLog);
                                csvWriter.append("\n");
                            }
                            
                            catch (Exception e) 
                                {
                                }
                        }
                        else
                        {
                            try (FileWriter csvWriter = new FileWriter("E:\\ResearchWork\\Multichain_Java_Client\\MultiChainJavaAPI-master\\MultiChainJavaAPI-master\\TransactionLogs\\TransactionLog15_Loaded.csv",true)) 
                            {
                                csvWriter.append(TransactionLog);
                                csvWriter.append("\n");
                            }
                            
                            catch (Exception e) 
                                {
                                }
                            
                        }
                        
                        
                        connection.disconnect();
                    }
                catch (Exception e) 
                    {
			result = "-> Does Not Exist \t" + "Invalid domain - Exception: " + e.getMessage();
                    }
		System.out.println(url + "\t\tResponse Status:" + result);
		return result;
	}

}
           
    

