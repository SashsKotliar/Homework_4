public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean mediator;

    public String getUsername(){
        return username;
    }

    public String getPassword (){
        return password;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public boolean getUserType (){
        return mediator;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public void setUserType(boolean mediator){
        this.mediator=mediator;
    }

    public User (String username, String password, String phoneNumber, boolean mediator){
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.mediator = mediator;
    }

    public String toString (){
        return this.username + ", "+this.phoneNumber+ " (" +(this.mediator ? "Mediator" : "Regular user") + ")";
    }
}
