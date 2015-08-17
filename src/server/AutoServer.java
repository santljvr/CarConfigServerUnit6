package server;
import model.Automobile;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* Interface implemented in BuildCarModelOptions class*/
/**
 * Created by santa on 6/23/15.
 */
public interface AutoServer {

    public void buildusingProperty(String filename);
    public ArrayList<String> getModelList();
   public void sendModelList(ObjectOutputStream os, ArrayList<String> list);
    public void sendSelectedAutomobile(ObjectOutputStream os, String modelname);
    public LinkedHashMap<String, Automobile> getAutomobileList();
	public void sendAutomobileList(ObjectOutputStream os, LinkedHashMap<String, Automobile> autoList);
}
