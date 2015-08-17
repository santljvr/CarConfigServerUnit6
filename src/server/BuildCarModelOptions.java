package server;
//this class is for building car using AutoServer Interface....
/**
 * Created by santa on 6/23/15.
 *this class will create a server with build option class methods just like KnockKnock server
 */

import adapter.BuildAuto;
import model.Automobile;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import dbconnector.Operation;

public class BuildCarModelOptions implements AutoServer {
	
	Operation operation1 = new Operation();


        static BuildAuto auto = null;
        
   public BuildCarModelOptions(){
	   auto = new BuildAuto();
   }

    public void buildusingProperty(String filename){
        if(auto==null) {
            auto=new BuildAuto();
        }
        auto.buildAuto(filename);

    }

    public ArrayList<String> getModelList() {
        ArrayList<String> modellist = new ArrayList<String>();
        if(auto!=null){
            modellist.addAll(auto.getMlist());
        }
        return modellist;
    }
    
   public void sendModelList(ObjectOutputStream os, ArrayList<String> list){
	   auto.sendList(os,list);
    	
    } 
   
    public void sendSelectedAutomobile(ObjectOutputStream os, String modelname) {
        auto.sendSelectedAuto(os,modelname);
        
    }

    public LinkedHashMap<String, Automobile> getAutomobileList()
    {  
    	return auto.getautocarHash();
    }
    
    public void sendAutomobileList(ObjectOutputStream os, LinkedHashMap<String, Automobile> autoList) {
        auto.sendSelectedAutoList(os,autoList);
        
    }
    

}
