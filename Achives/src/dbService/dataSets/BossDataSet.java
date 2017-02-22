package dbService.dataSets;



public class BossDataSet {
	private long id;
    private String name;
    private String username;
    private String password;
    private String projects;
    
    public BossDataSet(int id, String username, String password, String name, String projects) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.projects = projects;
        
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }
    public String getProjects() {
        return projects;
    }
    public String toString() {
        return "BossDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' + "username=" + username+'}';
    }
   
}
