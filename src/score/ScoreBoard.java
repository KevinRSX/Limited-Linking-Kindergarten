package score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScoreBoard{
//	private static final String FILE_PATH = "data/1.txt";
	private static final int MAX_RECORD = 10;
	
    private List<Record> list;
    private int num = 0;
    private static ScoreBoard scoreboard = new ScoreBoard();
    private ScoreBoard() {}
    public static ScoreBoard getInstance() {return scoreboard;}
    
    @SuppressWarnings("unchecked")
	public String readScoreBoard(String filepath) {
    	list = new ArrayList<>();
    	String line = null, info = null;	// info for testing
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
                num=0;
            }
	    }catch(Exception e){
	        System.out.println("Error");
	        info =  "Error";
	        e.printStackTrace();
	    }
        Collections.sort(list);
        
		return info;
    }
    
    public void wirteScoreBoard(String filepath, String username, int score) {
    	FileWriter fw = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        try {
        	fw = new FileWriter(filepath,true);
            fw.write(username+", " + score + ", " + df.format(new Date()) + "\n");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(null != fw) {
                    try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
            }
        }
    }
    
    public String[][] getScores(String filepath){
    	this.readScoreBoard(filepath);
    	String[][] arr = new String[MAX_RECORD][3];
    	int i = 0;
    	Iterator<Record> iterator = list.iterator();
        while(iterator.hasNext() && i < MAX_RECORD){
            Record m = iterator.next();
            arr[i][0] = m.getName();
            arr[i][1] = (new Integer(m.getScore())).toString();
            arr[i][2] = m.getTime();
            i++;
            num++;
        }
    	return arr;
    }
    
    public int getlistSize() {
    	return list.size();
    }
    public int getNum() {
    	return num;
    }

}
