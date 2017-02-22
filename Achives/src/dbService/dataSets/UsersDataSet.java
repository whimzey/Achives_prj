package dbService.dataSets;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String name;
    private String username;
    private String password;
private int bossID;
private String reachedachivesID;
private String avaliableachevesID;
private String bossname;
private ArrayList<String> reachedachives;
private ArrayList<String> avaliableacheves;
private int score;
private String messages;
    public UsersDataSet(long id, String username, String password, String name, int boss, String reachedachives, String avaliableacheves, int score, String messages) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.bossID = boss;
        this.reachedachivesID = reachedachives;
        this.avaliableachevesID = avaliableacheves;
        this.score = score;
        this.messages = messages;
    }
    public UsersDataSet(long id, String username, String password, String name, String boss, ArrayList<String> reachedachives, ArrayList<String> avaliableacheves, int score,String messages) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.bossname = boss;
        this.reachedachives = reachedachives;
        this.avaliableacheves = avaliableacheves;
        this.score = score;
        this.messages = messages;
    }
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
    public int getBoss() {
        return bossID;
    }
    public String getBossName() {
        return bossname;
    }
    public ArrayList<String> getReachedachivesArray() {
        return reachedachives;
    }
    public ArrayList<String> getAvaliableachevesArray() {
        return avaliableacheves;
    }
    public String getReachedachives() {
        return reachedachivesID;
    }
    public String getAvaliableacheves() {
        return avaliableachevesID;
    }
    public String getUserName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getScore() {
        return score;
    }
    public String getMessages() {
        return messages;
    }
    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' + "ачивки=" + avaliableacheves +
                ", достигнутые='" + reachedachives + '\'' +
                '}';
    }
}
