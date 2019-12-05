package score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ScoreBoard{
    private List<Record> list = new ArrayList<>();
    private  Object[][] arr = new Object[1000][3];
    private int num = 0;

    public ScoreBoard() {
    	
    }
    public String showScoreBoard(String filepath) {
    	String line = null;
    	String info = null;
        try {
            File file = new File(filepath);
            if(file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                while((line = bufferedReader.readLine()) != null){ 
                	if(!line.equals("")) {
                	String[] str = line.split(", ");
                	Record r = new Record(str[0], str[1],str[2]);
                    list.add(r);
                	}
                }
                bufferedReader.close();
                read.close();
                info = "File exists";
            }
            else{
                System.out.println("Cannot find this file!");
                info = "Cannot find this file!";
            }
    }catch(Exception e){
        System.out.println("Error");
        info =  "Error";
        e.printStackTrace();
    }

        Collections.sort(list);
        Iterator<Record> iterator = list.iterator();
        while(iterator.hasNext()){
            Record m = iterator.next();
            System.out.println(m.toString());
        }
		return info;
    }
    
    public int listSize() {
    	return list.size();
    }
    public int getNum() {
    	return num;
    }
    
    public Object[][] getScores(){
    	int i = 0;
    	Iterator<Record> iterator = list.iterator();
        while(iterator.hasNext() && i < 10){
            Record m = iterator.next();
            arr[i][0] = m.getName();
            arr[i][1] = m.getScore();
            arr[i][2] = m.getTime();
            i++;
            num++;
        }
    	return arr;
    }


}