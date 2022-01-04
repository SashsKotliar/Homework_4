import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class RealEstate {
    public static final int SKIP = -999;
    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int REGULAR_USER_LIMIT = 3;
    public static final int MEDIATOR_LIMIT = 10;
    public static final int MEDIATOR = 1;
    public static final int REGULAR_USER = 2;
    public static final int REGULAR_APARTMENT = 1;
    public static final int PENTHOUSE = 2;
    public static final int PRIVATE_HOUSE = 3;
    public static final int FOR_RENT = 1;
    public static final int FOR_SALE = 2;
    public static final int POST_NEW_PROPERTY = 1;
    public static final int DELETE_POSTED_PROPERTY = 2;
    public static final int SHOW_ALL_PROPERTIES = 3;
    public static final int SHOW_ALL_USERS_PROPERTIES = 4;
    public static final int SEARCH_FOR_PROPERTY = 5;
    public static final int GO_TO_MAIN_MENU = 6;
    private User[] users;
    private Property[] properties;
    private final Address[] addresses;


    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[10];
        this.addresses[0] = new Address("Tel Aviv", "Dizingof");
        this.addresses[1] = new Address("Tel Aviv", "Rotshild");
        this.addresses[2] = new Address("Tel Aviv", "Linkoln");
        this.addresses[3] = new Address("Ashdod", "Rotshild");
        this.addresses[4] = new Address("Ashdod", "Hakalanit");
        this.addresses[5] = new Address("Ashdod", "Herzel");
        this.addresses[6] = new Address("Ashkelon", "Shabazi");
        this.addresses[7] = new Address("Ashkelon", "Ben Gurion");
        this.addresses[8] = new Address("Ashkelon", "Exodus");
        this.addresses[9] = new Address("Jerusalem", "King George");

    }

    public void createUser() {
        User user = new User(createUsername(), createPassword(), addPhoneNumber(), ifMediator());
        addUserToArray(user);
        System.out.println(user);
    }

    public String createUsername() {
        Scanner scanner = new Scanner(System.in);
        String username;
        do {
            System.out.println("Please enter username: ");
            username = scanner.nextLine();
            if (usernameExists(username)) {
                System.out.println("The username " + username + " already exists");
            }
        } while (usernameExists(username));
        return username;
    }

    public boolean usernameExists(String usernameToCheck) {
        boolean exists = false;
        for (int i = 0; i < this.users.length; i++) {
            User currentUser = this.users[i];
            if (currentUser.getUsername().equals(usernameToCheck)) {
                exists = true;
            }
        }
        return exists;
    }

    public String createPassword() {
        Scanner scanner = new Scanner(System.in);
        String password;
        do {
            System.out.println("Choose a password: ");
            password = scanner.nextLine();
            if (!isStrongPassword(password)) {
                System.out.println("Your password is not strong enough.");
            }
        } while (!isStrongPassword(password));
        return password;
    }

    public boolean isStrongPassword(String passwordToCheck) {
        boolean specialSign = false;
        boolean number = false;
        boolean strongPassword = false;
        for (int i = 0; i < passwordToCheck.length(); i++) {
            char symbol = passwordToCheck.charAt(i);
            if (Character.isDigit(symbol)) {
                number = true;
                break;
            }
        }
        for (int i = 0; i < passwordToCheck.length(); i++) {
            if (passwordToCheck.charAt(i) == '_' || passwordToCheck.charAt(i) == '&' ||
                    passwordToCheck.charAt(i) == '%') {
                specialSign = true;
            }
        }
        if (number && specialSign) {
            strongPassword = true;
        }
        return strongPassword;
    }

    public String addPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.println("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
            if (!validNumber(phoneNumber)) {
                System.out.println("Invalid number!");
            }
        } while (!validNumber(phoneNumber));
        return phoneNumber;
    }

    public boolean validNumber(String phoneNumberToCheck) {
        boolean validNumber = true;
        if (phoneNumberToCheck.length() != PHONE_NUMBER_LENGTH) {
            validNumber = false;
        } else if (phoneNumberToCheck.charAt(0) != '0' || phoneNumberToCheck.charAt(1) != '5') {
            validNumber = false;
        } else {
            for (int i = 2; i < phoneNumberToCheck.length(); i++) {
                char c = phoneNumberToCheck.charAt(i);
                if (!Character.isDigit(c)) {
                    validNumber = false;
                    break;
                }
            }
        }
        return validNumber;
    }

    public boolean ifMediator() {
        Scanner scanner = new Scanner(System.in);
        boolean mediator = false;
        System.out.println("Are you a regular user or mediator?");
        System.out.println("For mediator - press 1.");
        System.out.println("For regular user - press 2.");
        int userChoice = scanner.nextInt();
        if (userChoice == MEDIATOR) {
            mediator = true;
        } else if (userChoice != REGULAR_USER) {
            System.out.println("Invalid choice.");
            ifMediator();
        }
        return mediator;
    }

    public void addUserToArray(User user) {
        User[] newArrayOfUsers = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            newArrayOfUsers[i] = this.users[i];
        }
        newArrayOfUsers[this.users.length] = user;
        this.users = newArrayOfUsers;
    }

    public String toString() {
        return "All users: " + Arrays.toString(this.users);
    }

    public User userLogIn() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        int choice;
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        if (usernameExists(username)) {
            user = ifUserExist(username, password);
        }
        if (user == null) {
            System.out.println("The user does not exist.");
        } else {
            do {
                System.out.println("To post a new property - press 1."); //v
                System.out.println("To delete a posted property - press 2.");
                System.out.println("To show all properties - press 3."); //v
                System.out.println("To show all user's properties - press 4.");
                System.out.println("To search for the property - press 5.");
                System.out.println("To log out and go to main menu - press 6.");
                choice = scanner.nextInt();
                switch (choice) {
                    case POST_NEW_PROPERTY:
                        addNewProperty(user);
                        break;
                    case DELETE_POSTED_PROPERTY: removeProperty(user);
                        break;
                    case SHOW_ALL_PROPERTIES:
                        printAllProperties();
                        break;
                    case SHOW_ALL_USERS_PROPERTIES: printUserProperties(user);
                        break;
                    case SEARCH_FOR_PROPERTY: search();
                        break;
                }
            } while (choice != GO_TO_MAIN_MENU);
        }
        return user;
    }

    public User ifUserExist(String usernameToCheck, String passwordToCheck) {
        Scanner scanner = new Scanner(System.in);
        int i;
        for (i = 0; i < this.users.length; i++) {
            if (usernameToCheck.equals(this.users[i].getUsername())) {
                if (!passwordToCheck.equals(this.users[i].getPassword())) {
                    do {
                        System.out.println("Invalid password. Try again: ");
                        passwordToCheck = scanner.nextLine();
                    } while (!passwordToCheck.equals(this.users[i].getPassword()));
                }
                break;
            }
        }
        return users[i];
    }

    public String propertyType() {
        Scanner scanner = new Scanner(System.in);
        String propertyType = "";
        int type;
        do {
            System.out.println("What type of property would you like to post?");
            System.out.println("For regular apartment press 1");
            System.out.println("For penthouse press 2");
            System.out.println("For private house press 3");
            type = scanner.nextInt();
            if (type == REGULAR_APARTMENT) {
                propertyType = "regular apartment";
            } else if (type == PENTHOUSE) {
                propertyType = "penthouse";
            } else if (type == PRIVATE_HOUSE) {
                propertyType = "private house";
            } else {
                System.out.println("Invalid type. Enter your choice again.");
            }
        } while (type != REGULAR_APARTMENT && type != PENTHOUSE && type != PRIVATE_HOUSE);
        return propertyType;
    }

    public boolean forRent() {
        Scanner scanner = new Scanner(System.in);
        boolean forRent = false;
        String forRentOrForSale;
        do {
            System.out.println("Is the property for rent or for sale?");
            forRentOrForSale = scanner.nextLine();
            if (!Objects.equals(forRentOrForSale, "for rent") && !Objects.equals(forRentOrForSale, "for sale")) {
                System.out.println("Invalid answer.");
            }
        } while (!Objects.equals(forRentOrForSale, "for rent") && !Objects.equals(forRentOrForSale, "for sale"));
        if (Objects.equals(forRentOrForSale, "for rent")) {
            forRent = true;
        }
        return forRent;
    }

    public int numberOfRooms() {
        Scanner scanner = new Scanner(System.in);
        int roomsNumber;
        do {
            System.out.println("How many rooms are in your property?");
            roomsNumber = scanner.nextInt();
            if (roomsNumber < 1) {
                System.out.println("Invalid number");
            }
        } while (roomsNumber < 1);
        return roomsNumber;
    }

    public int propertyPrice() {
        Scanner scanner = new Scanner(System.in);
        int price;
        do {
            System.out.println("What is the price?");
            price = scanner.nextInt();
            if (price < 0) {
                System.out.println("Invalid price");
            }
        } while (price < 0);
        return price;
    }

    public int houseNumber() {
        Scanner scanner = new Scanner(System.in);
        int houseNumber;
        do {
            System.out.println("What is the building's number?");
            houseNumber = scanner.nextInt();
            if (houseNumber < 1) {
                System.out.println("Invalid number");
            }
        } while (houseNumber < 1);
        return houseNumber;
    }

    public int floorNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's floor?");
        int floorNumber = scanner.nextInt();
        return floorNumber;
    }

    public boolean postNewProperty(User user) {
        boolean allowedToAddProperty = true;
        if (Objects.equals(user.getUserType(), "Mediator")) {
            if (usersPropertiesCounter(user) == MEDIATOR_LIMIT) {
                allowedToAddProperty = false;
            }
        } else {
            if (usersPropertiesCounter(user) == REGULAR_USER_LIMIT) {
                allowedToAddProperty = false;
            }
        }
        return allowedToAddProperty;
    }

    public int usersPropertiesCounter(User user) {
        int propertiesPosted = 0;
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getUser() == user) {
                propertiesPosted++;
            }
        }
        return propertiesPosted;
    }

    public void addNewProperty(User user) {
        Scanner scanner = new Scanner(System.in);
        if (postNewProperty(user)) {
            System.out.println("Choose a city.");
            System.out.println("Options:");
            System.out.println(Arrays.toString(availableCities(this.addresses)));
            String city = scanner.nextLine();
            if (strExist(availableCities(this.addresses), city)){
                System.out.println("Choose a street.");
                System.out.println("Options:");
                System.out.println(Arrays.toString(streetsOfTheCity(city)));
                String street = scanner.nextLine();
                if (strExist(streetsOfTheCity(city), street)){
                    String type = propertyType();
                    int floor = 0;
                    if (!Objects.equals(type, "private house")) {
                        floor = floorNumber();
                    }
                    int rooms = numberOfRooms();
                    int buildingNumber = houseNumber();
                    boolean forRent = forRent();
                    int price = propertyPrice();
                    Address address = this.addresses[setTheAddress(city, street)];
                    Property property = new Property(type, floor, rooms, buildingNumber, forRent, price, address , user);
                    addPropertyToArray(property);
                    if (propertyAddedSuccessfully(property)){
                        System.out.println("Property added successfully!");
                    }
                }
            }
        } else {
            System.out.println("You're not available to post a new property");
        }
    }

    public void printAllProperties(){
        System.out.println("All properties: " + Arrays.toString(this.properties));
    }

    public void printUserProperties(User user){
        System.out.println("Your all properties: ");
        int number = 1;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i].getUser() == user){
                System.out.println(number + "." + this.properties[i]);
                number++;
            }
        }
    }

    public void addPropertyToArray (Property property) {
        Property[] newArrayOfProperties = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            newArrayOfProperties[i] = this.properties[i];
        }
        newArrayOfProperties[this.properties.length] = property;
        this.properties = newArrayOfProperties;
    }

    public boolean propertyAddedSuccessfully(Property property){
        boolean added = false;
        for (int i = 0; i < this.properties.length; i++){
            if (this.properties[i] == property){
                added = true;
            }
        }
        return added;
    }

    public int setTheAddress (String city, String street){
        int i;
        for (i = 0; i < this.addresses.length; i++){
            if (Objects.equals(this.addresses[i].getCity(), city) && Objects.equals(this.addresses[i].getStreet(), street)){
                break;
            }
        }
        return i;
    }

    public String [] streetsOfTheCity(String city){
        int counter = 0;
        for (int i = 0; i < this.addresses.length; i++){
            if (Objects.equals(this.addresses[i].getCity(), city)){
                counter++;
            }
        }
        int index = 0;
        String [] streets = new String[counter];
        for (int i = 0; i < this.addresses.length; i++){
            if (index == counter){
                break;
            } else if (Objects.equals(this.addresses[i].getCity(), city)){
                streets[index] = this.addresses[i].getStreet();
                index++;
            }
        }
        return streets;
    }

    public boolean strExist (String[] strings, String str){
        boolean exist = false;
        for (int i = 0; i < strings.length; i++){
            if (Objects.equals(strings[i], str)){
                exist = true;
                break;
            }
        }
        return exist;
    }

    public String [] availableCities(Address[] addresses) {
        int counter = 0;
        for (int i = 0; i < this.addresses.length; i++) {
            for (int k = i+1; k < this.addresses.length; k++) {
                if (Objects.equals(addresses[i].getCity(), this.addresses[k].getCity())){
                    counter++;
                    break;
                }
            }
        }
        int index = 0;
        String[]cities = new String[addresses.length - counter];
        for (int i = 0; i < addresses.length; i ++){
            if (index == cities.length){
                break;
            }
            for (int k = 0; k < cities.length; k++) {
                if (Objects.equals(addresses[i].getCity(), cities[k]) && k <= index) {
                    break;
                } else if (!Objects.equals(addresses[i].getCity(), cities[k]) && k == index) {
                    cities[index] = addresses[i].getCity();
                    index++;
                    break;
                }
            }
        }
        return cities;
    }

    public Property[] usersProperties (User user){
        int index = 0;
        Property[] usersPropsArray = new Property[usersPropertiesCounter(user)];
        for (int i = 0; i < this.properties.length; i++) {
            if (index == usersPropsArray.length){
                break;
            } else if (this.properties[i].getUser() == user) {
                usersPropsArray[index] = this.properties[i];
                index++;
            }
        }
        return usersPropsArray;
    }

    public void removeProperty(User user){
        Scanner scanner = new Scanner(System.in);
        int number;
        if (usersPropertiesCounter(user) == 0){
            System.out.println("You didn't post any property so you can to remove it");
        } else {
            printUserProperties(user);
            do {
                System.out.println("Enter the number of the property you want to delete: ");
                number = scanner.nextInt();
            } while (number < 1 || number > usersProperties(user).length);
            Property propertyToDelete = usersProperties(user)[number - 1];
            Property[] newProperties = new Property[this.properties.length - 1];
            int index = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (this.properties[i] != propertyToDelete){
                    newProperties[index] = this.properties[i];
                    index++;
                }
            }
           this.properties = newProperties;
        }
    }

    public Property[] search(){
        Scanner scanner = new Scanner(System.in);
        Property [] wantedProperties = this.properties;
        System.out.println("Do you search a property for rent or for sale?");
        System.out.println("For rent - press 1, for sale - press 2");
        System.out.println("If you don't care - press -999");
        int forRentChoice;
        do {
            forRentChoice= scanner.nextInt();
            if (forRentChoice != FOR_RENT && forRentChoice != FOR_SALE && forRentChoice != SKIP){
                System.out.println("Invalid choice");
            }
        } while (forRentChoice != FOR_RENT && forRentChoice != FOR_SALE && forRentChoice != SKIP);
        if (forRentChoice == FOR_RENT){
            int counter = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (this.properties[i].getForRent()){
                    counter++;
                }
            }
            Property [] forRentProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (index == counter){
                    break;
                }else if (this.properties[i].getForRent()){
                    forRentProperties[index] = this.properties[i];
                    index++;
                }
            }
                wantedProperties = forRentProperties;
        } else if (forRentChoice == FOR_SALE){
            int counter = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (!this.properties[i].getForRent()){
                    counter++;
                }
            }
            Property [] forSaleProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < this.properties.length; i++){
                if (index == counter){
                    break;
                }else if (!this.properties[i].getForRent()){
                    forSaleProperties[index] = this.properties[i];
                    index++;
                }
            }
            wantedProperties = forSaleProperties;
        }
        System.out.println("What type of the property do you search?");
        int typeChoice;
        System.out.println("For regular apartment - press 1");
        System.out.println("For penthouse - press 2");
        System.out.println("For private house - press 3");
        System.out.println("If you don't care - press -999");
        do {
            typeChoice = scanner.nextInt();
            if (typeChoice != REGULAR_APARTMENT && typeChoice != PENTHOUSE && typeChoice != PRIVATE_HOUSE && typeChoice != SKIP){
                System.out.println("Invalid choice");
            }
        } while (typeChoice != REGULAR_APARTMENT && typeChoice != PENTHOUSE && typeChoice != PRIVATE_HOUSE && typeChoice != SKIP);

        if (typeChoice == REGULAR_APARTMENT){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (Objects.equals(wantedProperties[i].getType(), "regular apartment")){
                    counter++;
                }
            }
            Property [] regularApartments = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                }else if (Objects.equals(wantedProperties[i].getType(), "regular apartment")){
                    regularApartments[index] = wantedProperties[i];
                    index++;
                }
            }
            wantedProperties = regularApartments;
        } else if (typeChoice == PENTHOUSE){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (Objects.equals(wantedProperties[i].getType(), "penthouse")){
                    counter++;
                }
            }
            Property [] penthouses = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                }else if (Objects.equals(wantedProperties[i].getType(), "penthouse")){
                    penthouses[index] = wantedProperties[i];
                    index++;
                }
            }
            wantedProperties = penthouses;
        } else if (typeChoice == PRIVATE_HOUSE){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (Objects.equals(wantedProperties[i].getType(), "private house")){
                    counter++;
                }
            }
            Property [] privateHouses = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                }else if (Objects.equals(wantedProperties[i].getType(), "private house")){
                    privateHouses[index] = wantedProperties[i];
                    index++;
                }
            }
            wantedProperties = privateHouses;
        }
        System.out.println("What number of rooms are you searching for?");
        System.out.println("If you don't care - press -999");
        int numberOfRooms;
        do {
            numberOfRooms = scanner.nextInt();
            if (numberOfRooms < 1 && numberOfRooms != SKIP){
                System.out.println("Invalid number");
            }
        } while (numberOfRooms < 1 && numberOfRooms != SKIP);
        if (numberOfRooms != SKIP){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (wantedProperties[i].getRoomsNumber() == numberOfRooms){
                    counter++;
                }
            }
            Property[] wantedRoomsNumberProps = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++) {
                if (index == counter){
                    break;
                } else if (wantedProperties[i].getRoomsNumber() == numberOfRooms) {
                    wantedRoomsNumberProps[index] = wantedProperties[i];
                    index++;
                }
            }
            wantedProperties = wantedRoomsNumberProps;
        }
        System.out.println("What is minimum price?");
        int minPrice;
        do {
            minPrice = scanner.nextInt();
            if (minPrice < 0 && minPrice != SKIP){
                System.out.println("Invalid price");
            }
        } while (minPrice < 0 && minPrice != SKIP);
        System.out.println("What is maximum price?");
        int maxPrice;
        do {
            maxPrice = scanner.nextInt();
            if (maxPrice < minPrice && maxPrice != SKIP){
                System.out.println("Invalid price");
            }
        } while (maxPrice < minPrice && maxPrice != SKIP);

        if (minPrice >= 0){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if(wantedProperties[i].getPrice() >= minPrice){
                    counter++;
                }
            }
            Property [] notCheaperThanMinPricesProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                } else if (wantedProperties[i].getPrice() >= minPrice){
                    notCheaperThanMinPricesProperties[index] = wantedProperties[i];
                    index++;
                }
            }
            wantedProperties = notCheaperThanMinPricesProperties;
        }

        if (maxPrice >= minPrice &&(minPrice != SKIP || maxPrice != SKIP)){
            int counter = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if(wantedProperties[i].getPrice() <= maxPrice){
                    counter++;
                }
            }
            Property [] lessOrEqualsMaxPriceProperties = new Property[counter];
            int index = 0;
            for (int i = 0; i < wantedProperties.length; i++){
                if (index == counter){
                    break;
                } else if (wantedProperties[i].getPrice() <= maxPrice){
                    lessOrEqualsMaxPriceProperties[index] = wantedProperties[i];
                    index++;
                }
            }
            wantedProperties = lessOrEqualsMaxPriceProperties;
        }
        System.out.println(Arrays.toString(wantedProperties));
        return wantedProperties;
    }
}
