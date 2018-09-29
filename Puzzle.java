package myrtgs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Puzzle {
    
	public static void fileReadHash(String filepath)//to read text file and solve the puzzle
	{
		try
		{
			FileReader fr=new FileReader(new File(filepath));
			BufferedReader br=new BufferedReader(fr);
			String line="";
			int count=0,counter=0,rcount=0;
			List l=new ArrayList();//to store the duplicate wine id and person id if already selected by other users
			HashSet hs=new HashSet();//to store the Person ID
			HashMap<String,String> hm=new HashMap();//to store the wine id as key and person id as value
			while((line=br.readLine())!=null)
			{
				String str[]=line.split("\t");
				counter++;
				hs.add(str[0]);
				if(!hm.containsKey(str[1]))
				{
				hm.put(str[1], str[0]);
				}
				else
				{
					count++;
					List ld=new ArrayList();
					ld.add(str[1]);
					ld.add(str[0]);
					l.add(ld);
				}
				System.gc();
				System.out.println("Reading Text File Line No="+counter);
			}
			br.close();
			fr.close();
			Iterator it=l.iterator();
			while(it.hasNext())
			{
				List iner=(List)it.next();
				String name=hm.get(iner.get(0));
				int countm=Collections.frequency(hm.values(), name);
				if(countm>3)
				{
					hm.put(iner.get(0).toString(), iner.get(1).toString());
				}
			}			
			Iterator its=hs.iterator();
			HashMap<String,String> hmd=new HashMap();
			while(its.hasNext())
			{
				String val=its.next().toString();
				int countm=Collections.frequency(hm.values(), val);
				if(countm>3)
				{
					
					for (Map.Entry entry:hm.entrySet()) {
			            if (entry.getValue().equals(val) && rcount!=3) {			            	
			            	hmd.put(entry.getKey().toString(), entry.getValue().toString());
			            	rcount++;
			            }
			        }
					rcount=0;
				}
				else
				{
					for (Map.Entry entry:hm.entrySet()) {
			            if (entry.getValue().equals(val)) {			            	
			            	hmd.put(entry.getKey().toString(), entry.getValue().toString());
			            }
			        }
				}
			}
			System.out.println("Total Wine="+hmd.size()+" with average wine distributed count is="+hmd.size()/hs.size());
			for(Map.Entry entry:hmd.entrySet())
			{
				System.out.println(entry.getValue()+"\t"+entry.getKey());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the File Path in the format i.e C://Users//rekha//Downloads//test.txt");
		String filepath=in.next();
		fileReadHash(filepath);
	}

}
