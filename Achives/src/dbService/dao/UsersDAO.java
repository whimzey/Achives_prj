package dbService.dao;

import dbService.dataSets.BossDataSet;
import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;



public class UsersDAO {

    private Executor executor;
    

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }
    
    public UsersDataSet getFullDataSet(UsersDataSet dataset) throws SQLException {
        String boss = executor.execQuery("select * from boss where idboss=" + dataset.getBoss(),result-> {
            result.next();
            return result.getString(4);
        });
       
        String[]achives = dataset.getAvaliableacheves().split(",");
        ArrayList<String> avaliableacheves = new ArrayList<String>();
        
      
        for (int i = 0; i < achives.length; i++) {
        	avaliableacheves.add(executor.execQuery("select * from achives where idAchives=" + achives[i],result-> {
                result.next();
                return result.getString(2);}));
	}
        
       String[]rachives = dataset.getReachedachives().split(",");
       ArrayList<String> reachedachives=new ArrayList<String>();
      
      for (int i = 0; i < rachives.length; i++) {
    	  reachedachives.add(executor.execQuery("select * from achives where idAchives=" + rachives[i],result-> {
              result.next();
              return result.getString(2);}));
	}
      
      return new UsersDataSet(dataset.getId(), dataset.getUserName(), dataset.getPassword(), dataset.getName(), boss, reachedachives, avaliableacheves, dataset.getScore(), dataset.getMessages());
    }  
      
    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("select * from users where idUsers='" + id + "'", result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2),result.getString(3),result.getString(4),result.getInt(5),result.getString(6),result.getString(7),result.getInt(8),result.getString(9));
        });
    }
    public BossDataSet getBossProfile(String name) throws SQLException {
        return executor.execQuery("select * from boss where usernameBoss='" + name+ "'", result -> {
            result.next();
            
            return new BossDataSet(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
        });
    }
    
    public ArrayList<String> getAchivesList(long id) throws SQLException{
    	
    	return executor.execQuery("select * from achives where BossId=" + id + "", result -> {
    		ArrayList<String> achives=new ArrayList<String>();
    		while(result.next()){
        	   achives.add(result.getString(2));
           }
            return achives;
        });
    }
public ArrayList<String> getAchivesListbyId(long id) throws SQLException{
    	
    	return executor.execQuery("select * from achives where BossId=" + id + "", result -> {
    		ArrayList<String> achives=new ArrayList<String>();
    		while(result.next()){
        	   achives.add(result.getString(1));
           }
            return achives;
        });
    }
    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where username='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }
    

    public void insertUser(String username, String password,String name,String Boss) throws SQLException {
    	ArrayList<String> avaliableachives = getAchivesListbyId(Long.parseLong(Boss));
    	String achives="";
    	for(String e: avaliableachives){
    		achives+=e+",";
    	}
    	if(!achives.equals("")){achives = achives.substring(0, achives.length()-1);}
    	executor.execUpdate("insert into users (username, password, Name, Boss, avaliableachives) values ('" + username + "','" + password + "','" + name + "','" + Boss + "','" + achives + "')");
    }
    public void insertBoss(String username, String password,String name) throws SQLException {
        executor.execUpdate("insert into boss (usernameBoss, passwordBoss, nameBoss) values ('" + username + "','" + password + "','" + name + "')");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }

	public UsersDataSet getbyName(String name) throws SQLException {
		 return executor.execQuery("select * from users where Name='" + name + "'", result -> {
	            result.next();
	            return new UsersDataSet(result.getLong(1), result.getString(2),result.getString(3),result.getString(4),result.getInt(5),result.getString(6),result.getString(7),result.getInt(8),result.getString(9));
	        });
		
	}
	public UsersDataSet getbyUsername(String name) throws SQLException {
		 return executor.execQuery("select * from users where username='" + name + "'", result -> {
	            result.next();
	            return new UsersDataSet(result.getLong(1), result.getString(2),result.getString(3),result.getString(4),result.getInt(5),result.getString(6),result.getString(7),result.getInt(8),result.getString(9));
	        });
		
	}

	public void addscore(long id, String achive) throws SQLException {
		
		int achivevalue = executor.execQuery("select * from achives where Name='" + achive + "'", result -> {
            result.next();
            return result.getInt(4);});
	
		int score = executor.execQuery("select * from users where idUsers='" + id + "'", result -> {
            result.next();
            return result.getInt(8);});
		
		score+=achivevalue;
		executor.execUpdate("update users SET score='"+score+"' WHERE idUsers='"+id+"'");
	}

	public void addRecievedAchives(long idUser, String achive) throws SQLException {
		int achivevalue = executor.execQuery("select * from achives where Name='" + achive + "'", result -> {
            result.next();
            return result.getInt(1);});
		String achives = executor.execQuery("select * from users where idUsers='" + idUser + "'", result -> {
            result.next();
            return result.getString(6);});
		achives+=","+achivevalue;
		executor.execUpdate("update users SET reachedachives='"+achives+"' WHERE idUsers='"+idUser+"'");
		
	}
    
}
