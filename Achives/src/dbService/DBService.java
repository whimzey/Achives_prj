package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.BossDataSet;
import dbService.dataSets.UsersDataSet;
import messages.MessageDAO;

import org.h2.jdbcx.JdbcDataSource;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public UsersDataSet getUser(long id) throws DBException {
        try {
        	UsersDAO dao = new UsersDAO(connection);
        	UsersDataSet set = dao.get(id);
        	set.toString();
            return (dao.getFullDataSet(set));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public UsersDataSet getUserbyName(String name) throws DBException {
        try {
        	UsersDAO dao = new UsersDAO(connection);
        	UsersDataSet set = dao.getbyName(name);
        	
            return (dao.getFullDataSet(set));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public UsersDataSet getUser(String username) throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
            UsersDataSet set= dao.get(dao.getUserId(username));
            set.toString();
        	return (dao.getFullDataSet(set));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public BossDataSet getBossProfile(String name) throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
          	return dao.getBossProfile(name);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public Map<String,String> getAchivesonApproval(long l) throws DBException {
        try {
            MessageDAO dao = new MessageDAO(connection);
            Map<String,String> list = dao.getMessagesfromBD(l);
        	return list;
        } catch (SQLException e) {
            throw new DBException(e);
        }
        
    }
    
    public ArrayList<String> getAnswerfromBoss(long l) throws DBException {
        try {
            MessageDAO dao = new MessageDAO(connection);
            ArrayList<String> list = dao.getAnswerfromBoss(l);
        	return list;
        } catch (SQLException e) {
            throw new DBException(e);
        }
        
    }
    public ArrayList<String> getAchivesList(long id) throws DBException {
        try {
        	UsersDAO dao = new UsersDAO(connection);
            ArrayList<String> list = dao.getAchivesList(id);
        	return list;
        } catch (SQLException e) {
            throw new DBException(e);}
        }
        
    public void addUser(String username,String pass, String name, String boss) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.insertUser( username, pass,  name,  boss);
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void addBoss(String username,String pass, String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.insertBoss(username, pass, name);
            connection.commit();
             
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void addAnswer(long id, String message) throws DBException {
        try {
            connection.setAutoCommit(false);
            MessageDAO dao = new MessageDAO(connection);
           
            dao.insertBossAnswer(id, message);
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void addscore(long id, String achive) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
           
            dao.addscore(id, achive);
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void cleanUp() throws DBException {
        UsersDAO dao = new UsersDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public void deleteAnswersfromBoss(String id) throws SQLException{
    	MessageDAO dao = new MessageDAO(connection);
    	dao.deleteAnswersfromBoss(id);
    }
    public void deleteUserRequest(String id) throws SQLException{
    	MessageDAO dao = new MessageDAO(connection);
    	dao.deleteUserRequest(id);
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

          /*  StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("nishtyaki/").          //db name
                    append("user=root&").          //login
                    append("password=");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());*/
            String url = "jdbc:mysql://localhost:3306/nishtyaki?useUnicode=yes&characterEncoding=UTF-8";
            String name = "root";
            String pass = "";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getH2Connection() {
        try {
            String url = "jdbc:mysql://localhost:3306/nishtyaki";
            String name = "root";
            String pass = "";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	

	public void addMessagetoBoss(String name, String achive) throws SQLException {
		MessageDAO dao = new MessageDAO(connection);
		dao.addMessagetoBoss( name,  achive);
	}

	public void addRecievedAchives(long idUser, String achive) throws DBException {
		try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
           
            dao.addRecievedAchives(idUser, achive);
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
		
	}
}
