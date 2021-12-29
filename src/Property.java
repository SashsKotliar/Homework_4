import java.util.Objects;

public class Property {
    private int roomsNumber;
    private int price;
    private String type;
    private boolean forRent;
    private int houseNumber;
    private int floorNumber;
    private Address address;
    private User user;

    public int getRoomsNumber(){
        return roomsNumber;
    }

    public int getPrice(){
        return price;
    }

    public String getType(){
        return type;
    }

    public boolean getForRent(){
        return forRent;
    }

    public int getHouseNumber (){
        return houseNumber;
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    public Address getAddress(){
        return address;
    }

    public User getUser (){
        return user;
    }

    public void setRoomsNumber(int roomsNumber){
        this.roomsNumber = roomsNumber;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setForRent(boolean forRent){
        this.forRent = forRent;
    }

    public void setHouseNumber (int houseNumber){
        this.houseNumber = houseNumber;
    }

    public void setFloorNumber(int floorNumber){
        this.floorNumber = floorNumber;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public void setUser (User user){
        this.user = user;
    }

    public Property (String type, int floorNumber, int roomsNumber, int houseNumber, boolean forRent, int price,
                      Address address, User user){
       this.roomsNumber = roomsNumber;
       this.price = price;
       this.type = type;
       this.forRent = forRent;
       this.houseNumber = houseNumber;
       this.floorNumber = floorNumber;
       this.address = address;
       this.user = user;
    }

    public String toString (){
        String str;
        if (Objects.equals(type, "private house")){
            str = "Type of apartment: " + this.type + " - " + (this.forRent ? "for rent":"for sale")+
                    ", rooms number: " + this.roomsNumber + "." +
                    "\n Price: " + this.price + "$" +
                    "\n Contact info: " + this.user;
        } else {
            str = "Type of apartment: " + this.type + " - " + (this.forRent ? "for rent":"for sale")+
                    ", floor number: " + this.floorNumber + ", rooms number: " +
                    this.roomsNumber + "." + "\n Price: " + this.price + "$" +
                    "\n Contact info: " + this.user;
        }
        return str;
    }

}
