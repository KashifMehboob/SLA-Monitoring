/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multichain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import multichain.command.MultichainException;
import multichain.command.RuntimeParameters;
import multichain.command.WalletTransactionCommand;
import multichain.object.BalanceAssetGeneral;

/**
 *
 * @author Kashif
 */
public class SLA_Assesment {
    public  long MakeSLAFeedBack() 
    {   long lnducedDelay_Outer=0;
        RuntimeParameters RTP = new RuntimeParameters();
        RTP.setDatadir("C:\\Users\\Kashif\\AppData\\Roaming\\MultiChain");
        RTP.setRpcport("7748");
        WalletTransactionCommand Obj = new WalletTransactionCommand("192.168.8.102", "7748", "multichainrpc","6Zr3WeHWDUCxWxeAULrn23zt6YSeZY6ob6vTJuWbqA8S",RTP);
        
        BalanceAssetGeneral Obj2 = new BalanceAssetGeneral();
        Obj2.setAssetref("60-266-19659");
        Obj2.setName("SLA_Validating_Tokens");
        Obj2.setQty(1);
        List<BalanceAssetGeneral> Asset = new ArrayList<BalanceAssetGeneral>();
        Asset.add(Obj2);
        int LoopCounter=0;
        double RandomDelay;
        long lnducedDelay=0;
        //lnducedDelay_Outer=lnducedDelay;
        
        
        try
        {
        BufferedReader br = new BufferedReader(new FileReader("D:\\VotersData\\NewVoterList\\MalleabilityAttackAddressesPart1.csv")); 
        String line = null;
        String MonitoringService="15oKdRSk5tEA74iZGYRLJiGVoLbsrnvYs1mCRo";//Last two addresses from PS 21 in VotersData
        String EndPoint_Service1="1NaHtWDeU4S75Myfi5zRTXkoKY4FAnF9JBJjnB";
        String TxID;
        
        while ((line = br.readLine()) != null) 
        {
        String[] EndPointNodeAddresses = line.split(",");
        for (String str : EndPointNodeAddresses) 
            {
                try
                {
                    Random ran = new Random();
                    int x=ran.nextInt(6)+0;
                    lnducedDelay=(long)x;
                    
                    //RandomDelay=Math.random();
                    //RandomDelay=RandomDelay*10;
                    //lnducedDelay = (long)RandomDelay;
                    TimeUnit.SECONDS.sleep(lnducedDelay);
                    
                }
                catch(InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }
                try
                {
                str=str.replaceAll("^\"|\"$", "");
                
                LoopCounter=LoopCounter+1;
                
                System.out.println("Transaction is being sent at "+LocalDateTime.now()+" for voter "+str + " with a delay of "+lnducedDelay+ " seconds.");
                TxID=Obj.sendWithDataFrom(str, MonitoringService, "SLA_Validating_Tokens", 1, " Tx for No. "+LoopCounter+" at (Root Node)");
                System.out.println("TxID: "+TxID+"|Tx No "+LoopCounter+" at "+LocalDateTime.now() + " for a successful transaction.");
                
                }
                catch(MultichainException ex)
                {
                    //throw ex;
                    
                    
                    System.out.println("due to insufficient validating tokens. A failed Transaction");
                    System.out.println("****************************************************************");
                    continue;
                }
            }
        }
        br.close();
        }
        
        catch(FileNotFoundException ex)
        {
            System.out.println("Error"+ex.getMessage());
        }
        catch(IOException ex)
        {
            System.out.println("Error"+ex.getMessage());
        }
        return LoopCounter;
    }
}
