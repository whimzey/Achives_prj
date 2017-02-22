package messages;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


import dbService.executor.Executor;

public class MessageDAO {
private Executor executor;
    

    public MessageDAO(Connection connection) {
        this.executor = new Executor(connection);
    }
    public Map<String,String> getMessagesfromBD(long id) throws SQLException {
    	
    	Map<String,String> array = executor.execQuery("select * from messages where bossID =" + id + "", result -> {
    		Map<String,String> innerarray = new TreeMap<String,String>();
        	while(result.next()){
        		innerarray.put(result.getString(1),result.getString(3));
        		
            } 
            return innerarray;
        });
         Map<String,String> map = new TreeMap<String,String>();
         
         for (Map.Entry<String, String> entry : array.entrySet())
         {
        	 String[] x = entry.getValue().split(",");
        	 
        	 String achive = executor.execQuery("select * from achives where idAchives ='" + x[1] + "'", result -> {
        		 result.next();
                 return result.getString(2);
             });
        	
			map.put(entry.getKey(), x[0]+","+achive);
		}
         return map;
    }
    public ArrayList<String> getAnswerfromBoss(long l) throws SQLException{
    	ArrayList<String> array = executor.execQuery("select * from answersfromboss where idUser =" + l + "", result -> {
            ArrayList<String> innerarray = new ArrayList<>();
        	while(result.next()){
        		innerarray.add(result.getString(3));
            } 
            return innerarray;
        });
    	ArrayList<String> answers = new ArrayList<String>();
    	
    	if(array!=null){
    		for (int i = 0; i < array.size(); i++) {
			String[] x =array.get(i).split(",");
			
			String boss = executor.execQuery("select * from boss where idboss ='" + x[0] + "'", result -> {
		   		 result.next();
		            return result.getString(4);
		        });
			 
			 String result = x[2];
			 String answer = boss + " перевел(а) вашу заявку на подтверждение достижения " + "\""+x[1]+"\"" + " в статус "+ "\""+result+"\"";
		
			 answers.add(answer);
		}
    	
    	
    	return answers;}
    	else return null;
    	
    }
    public void deleteAnswersfromBoss(String id) throws SQLException{
    	 executor.execUpdate("delete from answersfromboss where idUser ='"+Long.parseLong(id)+ "'");
    }
    public void deleteUserRequest(String id) throws SQLException{
   	 executor.execUpdate("delete from messages where idmessages ='"+Long.parseLong(id)+ "'");
   }
    
    public void insertBossAnswer(long id, String message) throws SQLException {
    	
            executor.execUpdate("insert into answersfromboss (idUser, answer) values ('" + id + "','" + message + "' )");
        
    }
    
	
	public void addMessagetoBoss(String name, String achive) throws SQLException {
		System.out.println(achive);
		LinkedList<Integer> ids = executor.execQuery("select * from achives where Name ='" + achive + "'", result -> {
        	result.next();  
        	LinkedList<Integer> ids1 = new LinkedList<Integer>();
        	ids1.add(result.getInt(1));
        	ids1.add(result.getInt(3));
            return ids1;
            
        });
		
		String message = name+","+ids.get(0);
		executor.execUpdate("insert into messages (bossID, messagebody) values ('" + ids.get(1) + "','" + message + "' )");
		
	}
    	
    }


