package FinalProject;

import java.util.*;
public class Hotel extends Person  {

    static Scanner sc = new Scanner (System.in);
    static ArrayList<Person> customers = new ArrayList<>();

    // Input customers ----------------------------------------------------------

    public void inputCustomer(){
        inputPerson();
        while (true){
            System.out.print("\nEnter 1 to choose VIP room or 2 to normal room : ");
            String roomChoice = sc.nextLine();
            if (roomChoice.equalsIgnoreCase("1")){
                setRoom(new RoomVip()) ; break;
            }
            else if (roomChoice.equalsIgnoreCase("2")){
                setRoom(new RoomNormal()); break;
            } else {
                System.out.print("\n Please enter again !");
            }
        }
        customers.add(new Person(getName(),getLastName(),getAge(),getRoom(),getId_card(),getNumberRent(),getPhone(),getHiredDate()));
    }

    // Check is the  phone exist
    public static boolean checkPhoneExist(String phone){
        boolean check = false;
        for (int i = 0; i<customers.size();i++){
            if (phone.equals(customers.get(i).getPhone())){
                check = true; break;
            }
        }return check;
    }

    // Check is the  ID card exist
    public static boolean checkIdCardExist(String id_card){
        boolean check = false;
        for (int i = 0; i<customers.size();i++){
            if (id_card.equals(customers.get(i).getId_card())){
                check = true; break;
            }
        }return check;
    }

    //Show customer in VIP room
    public void showCustomerInVipRoom(){
        if (Room.countVip==0){
            System.out.println("No one in the VIP room !");
            return;
        }
        System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n", "ORDINAL","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
        for (int i = 0; i<customers.size();i++){
            if (customers.get(i).getRoom().getType().equals("VIP")){
                System.out.printf("%-7d %-15s %-15s  %-6s %-10s %-15s %-17d %-11.3f %-14s %-10s \n",
                        (i+1),customers.get(i).getLastName(),customers.get(i).getName(),customers.get(i).getAge(),customers.get(i).getRoom().getType(),customers.get(i).getId_card(),customers.get(i).getNumberRent(),getPriceByIndex(i),getPhone(),getHiredDate());
            }
        }
    }

    //Show customer in normal room
    public void showCustomerInNormalRoom(){
        if (Room.countNormal==0){
            System.out.println("No one in the normal room !");
            return;
        }
        System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n", "ORDINAL","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
        for (int i = 0; i<customers.size();i++){
            if (customers.get(i).getRoom().getType().equals("Normal")){
                System.out.printf("%-7d %-15s %-15s  %-6s %-10s %-15s %-17d %-11.3f %-14s %-10s \n",
                        (i+1),customers.get(i).getLastName(),customers.get(i).getName(),customers.get(i).getAge(),customers.get(i).getRoom().getType(),customers.get(i).getId_card(),customers.get(i).getNumberRent(),getPriceByIndex(i),getPhone(),getHiredDate());
            }
        }
    }

    // Show customers ----------------------------------------------------------
    public void showCustomer(){
        while (true) {
            if (Room.countNormal==0 && Room.countVip==0){
                System.out.println("Has no one to show !");
                return;
            }
            System.out.print("""
                    Enter 1 to show all customers
                    Enter 2 to show customers in Normal room
                    Enter 3 to show customers in VIP room
                    """);
            byte choice = sc.nextByte();
            switch (choice) {
                case 1 -> {
                    System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n", "ORDINAL","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
                    for (int i = 0; i < customers.size(); i++) {
                        System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17d %-11.3f %-14s %-10s \n",
                                (i + 1), customers.get(i).getLastName(), customers.get(i).getName(), customers.get(i).getAge(), customers.get(i).getRoom().getType(), customers.get(i).getId_card(), customers.get(i).getNumberRent(), getPriceByIndex(i),customers.get(i).getPhone(),customers.get(i).getHiredDate());
                    }
                    return;
                }
                case 2 -> {
                    showCustomerInNormalRoom();
                    return;
                }
                case 3 -> {
                    showCustomerInVipRoom();
                    return;
                }
                default -> System.out.println("Invalid !");
            }
        }
    }

    // Get price by index
    public float getPriceByIndex(int index){
        return customers.get(index).getRoom().getPrice() * customers.get(index).getNumberRent();
    }

    //Check name and return index
    public int checkName(String name){
        for (int i =0; i<customers.size(); i++){
            if (customers.get(i).getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    //Check ID card and return index
    public int checkId_card(String id_card){
        for (int i =0; i<customers.size(); i++){
            if (customers.get(i).getId_card().equalsIgnoreCase(id_card)) return i;
        }
        return -1;
    }

    //Get bill ----------------------------------------------------------
    public void getBill1(){
        if (Room.countVip ==0 && Room.countNormal==0) {
            System.out.println("Has no one to get bill !");
        } else {
            float price = 0;
            System.out.print("""
                Choose 1 to get bill by name
                Choose 2 to get bill by ID card
                
                """);
            byte choice= sc.nextByte();
            switch (choice) {
                case 1 -> {
                    while (true){
                        System.out.print("Enter name: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        if (checkName(name) != -1) {
                            price = getPriceByIndex(checkName(name)); break;
                        }else {
                            System.out.print("This name doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                            String decision = sc.next();
                            if (decision.equalsIgnoreCase("y")){
                                showCustomer(); return;
                            }else if (decision.equalsIgnoreCase("n")){
                                System.out.println("Continue... ");
                            }
                        }
                    }
                }
                case 2 -> {
                    while (true){
                        System.out.print("Enter ID card: ");
                        sc.nextLine();
                        String id_card = sc.nextLine();
                        if (checkId_card(id_card) != -1) {
                            price = getPriceByIndex(checkId_card(id_card)); break;
                        }else{
                            System.out.print("This ID card doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                            String decision = sc.next();
                            if (decision.equalsIgnoreCase("y")){
                                showCustomer(); return;
                            }else if (decision.equalsIgnoreCase("n")){
                                System.out.print(" ");
                            }
                        }
                    }
                }
                default -> System.out.println("Invalid !");
            }
            System.out.printf("Bill has price %.2f0VND.",price);
        }
        }

    //Sort by date
    public void sortByDate(){
        for (int i = 0; i<customers.size(); i++){
            for (int j =i+1; j<customers.size();j++){
                if (compareDay(customers.get(i).getHiredDate(),customers.get(j).getHiredDate())==0){
                    Person temp = customers.get(i);
                    customers.set(i,customers.get(j));
                    customers.set(j,temp);
                }
            }
        }  System.out.println("Successful !");
    }
    // Sort by price
    public void sortByPrice(){
        for (int i = 0; i<customers.size(); i++){
            for (int j =i+1; j<customers.size();j++){
                if (getPriceByIndex(i)> getPriceByIndex(j)){
                    Person temp = customers.get(i);
                    customers.set(i,customers.get(j));
                    customers.set(j,temp);
                }
            }
        }  System.out.println("Successful !");
    }

    // Sort by number rent
    public void sortByNumberRent(){
        for (int i = 0; i<customers.size(); i++){
            for (int j =i+1; j<customers.size();j++){
                if (customers.get(i).getNumberRent()> customers.get(j).getNumberRent()){
                    Person temp = customers.get(i);
                    customers.set(i,customers.get(j));
                    customers.set(j,temp);
                }
            }
        } System.out.println("Successful !");
    }

    // Sort by age
    public void sortByAge(){
        for (int i = 0; i<customers.size(); i++){
            for (int j =i+1; j<customers.size();j++){
                if (customers.get(i).getAge()> customers.get(j).getAge()){
                    Person temp = customers.get(i);
                    customers.set(i,customers.get(j));
                    customers.set(j,temp);
                }
            }
        } System.out.println("Successful !");
    }

    //Sort by name
    public void sortByName(){
        for (int i = 0; i<customers.size(); i++){
            for (int j =i+1; j<customers.size();j++){
                if (customers.get(i).getName().compareToIgnoreCase(customers.get(j).getName())>0){
                    Person temp = customers.get(i);
                    customers.set(i,customers.get(j));
                    customers.set(j,temp);
                }
            }
        } System.out.println("Successful !");
    }

    // Sort by options ---------------------------------------------
    public void sortCustomers(){
        if (Room.countVip ==0 && Room.countNormal==0) {
            System.out.println("Has no one to sort !");
        } else {
            while (true) {
                System.out.print("""
                    Enter 1 to sort by price
                    Enter 2 to sort by number rent
                    Enter 3 to sort by age
                    Enter 4 to sort by name
                    Enter 5 to sort by date
                    """);
                byte choice = sc.nextByte();
                switch (choice) {
                    case 1 -> {
                        sortByPrice();
                        return;
                    }
                    case 2 -> {
                        sortByNumberRent();
                        return;
                    }
                    case 3 -> {
                        sortByAge();
                        return;
                    }
                    case 4 -> {
                        sortByName();
                        return;
                    }
                    case 5 -> {
                        sortByDate();
                        return;
                    }
                    default -> System.out.println("Please enter again ! ");
                }
            }
        }

    }

    //Delete customer by name
    public void deleteByName(){
        while (true){
            System.out.print("\nEnter name you want to delete: ");
            sc.nextLine();
            String name = sc.nextLine();
            if (checkName(name)!= -1){
                if (customers.get(checkName(name)).getRoom().getType().equals("VIP")){
                    Room.countVip--;
                } else {
                    Room.countNormal--;
                }
                customers.remove(checkName(name));
                System.out.println("Successful !");
            }else{
                System.out.print("This ID card doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                String decision = sc.next();
                if (decision.equalsIgnoreCase("y")){
                    showCustomer(); return;
                }else if (decision.equalsIgnoreCase("n")){
                    System.out.println("Continue... ");
                }
            }
        }
    }

    //Delete customer by ID card
    public void deleteByIdCard(){
        while (true){
            System.out.print("\nEnter ID card you want to delete: ");
            sc.nextLine();
            String id_card = sc.nextLine();
            if (checkId_card(id_card)!= -1){
                if (customers.get(checkId_card(id_card)).getRoom().getType().equals("VIP")){
                    Room.countVip--;
                } else {
                    Room.countNormal--;
                }
                customers.remove(checkId_card(id_card));
                System.out.println("Successful !");
            }else{
                System.out.print("This ID card doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                String decision = sc.next();
                if (decision.equalsIgnoreCase("y")){
                    showCustomer(); return;
                }else if (decision.equalsIgnoreCase("n")){
                    System.out.println("Continue... ");
                }
            }
        }
    }

    // Delete customer ---------------------------------------------
    public void deleteCustomer(){
        if (Room.countVip ==0 && Room.countNormal==0) {
            System.out.println("Has no one to delete !");
        } else {
            while (true){
                System.out.print("""
                    Enter 1 to delete by name
                    Enter 2 to delete by ID card
                    
                    """);
                String choice = sc.nextLine();
                switch (choice){
                    case "1" -> {
                        deleteByName();
                        return;
                    }
                    case "2" -> {
                        deleteByIdCard(); return;
                    }
                    default -> System.out.println("Please enter again !");
                }
            }
        }

    }

    //Edit age
    public void editAge(int index){
        System.out.print("Enter new age: ");
        customers.get(index).setAge(sc.nextByte());
        System.out.println("Successful !");
    }

    //Edit room
    public void editRoom(int index){
        while (true){
            System.out.print("Enter 1 to choose VIP room or 2 to normal room: ");
            byte room = sc.nextByte();
            if (room ==1){
                customers.get(index).setRoom(new RoomVip());
                System.out.println("Successful !");
                return;
            } else if (room ==2){
                customers.get(index).setRoom(new RoomNormal());
                System.out.println("Successful !");
                return;
            } else {
                System.out.println("Please enter again !");
            }
        }

    }

    //Edit id_card
    public void editIdCard(int index){
        sc.nextLine();
        while (true ){
            System.out.print("Enter new ID card: ");
            String idCard = sc.nextLine();
            customers.get(index).setId_card(idCard);
            if (Person.checkID.matcher(idCard) .find() && customers.get(index).getId_card() != null) {
                System.out.println("Successful !");
                break;
            }
            else System.out.println("\nInvalid !\nID card should be number and consists of 9 or 13 numbers ");
        }
        customers.get(index).setId_card(sc.nextLine());
        System.out.println("Successful !");
    }

    //Edit number rent
    public void editNumberRent(int index){
        while (true){
            System.out.print("Enter new number day for rent room: ");
            customers.get(index).setNumberRent(Integer.parseInt(sc.nextLine()));
            if (customers.get(index).getNumberRent()> 0 && customers.get(index).getNumberRent()< 31)  break;
            else System.out.println("Time for rent room from 1 to 30 day(s)");
        }
    }

    //Edit phone number
    public void editPhoneNumber(int index){
        while(true){
            System.out.println("Enter new phone number: ");
            String newPhone = sc.next();
            if (Person.checkPhone.matcher(newPhone).find()){
                customers.get(index).setPhone(newPhone);
                System.out.println("Successful !");
                break;
            } else{
                System.out.println("Enter phone number with format 0xx.xxx.xxxx !");
            }
        }
    }

    //Edit hired date
    public void editHiredDate(int index){
        while(true){
            System.out.println("Enter new hired date: ");
            String newHiredDate = sc.next();
            if (Person.checkDate.matcher(newHiredDate).find()){
                customers.get(index).setHiredDate(newHiredDate);
                System.out.println("Successful !");
                break;
            } else{
                System.out.println("Enter hired date with format yyyy-mm-dd !");
            }
        }
    }
    //Edit name
    public void editName(int index){
        sc.nextLine();
         while (true){
             while(true){
                 System.out.print("Enter new last name: ");
                 String newLastName= sc.nextLine();
                 if (Person.checkName.matcher(newLastName).find()){
                     customers.get(index).setLastName(newLastName);
                     break;
                 } else{
                     System.out.println("Name hasn't number !");
                 }
             }
             System.out.print("Enter new first name: ");
             String newFirstName = sc.nextLine();
             if (Person.checkName.matcher(newFirstName).find()){
                 customers.get(index).setName(newFirstName);
                 System.out.println("Successful !");
                 break;
             } else{
                 System.out.println("Name hasn't number !");
             }
             System.out.println("Successful !");
         }
    }

    //Menu editing
    public void menuEditing(int index){
        while (true){
            System.out.print("""
                Enter 1 to edit name
                Enter 2 to edit age
                Enter 3 to edit number rent
                Enter 4 to edit ID card
                Enter 5 to edit room
                Enter 6 to edit hired day
                Enter 7 to edit phone number
                Enter 0 to exit
                """);
            byte choice = sc.nextByte();
            switch (choice){
                case 1 -> {
                    editName(index); break;
                }
                case 2 -> {
                    editAge(index); break;
                }
                case 3 -> {
                    editNumberRent(index); break;
                }
                case 4 -> {
                    editIdCard(index); break;
                }
                case 5 -> {
                    editRoom(index); break;
                }
                case 6 -> {
                    editHiredDate(index);break;
                }
                case 7 -> {
                    editPhoneNumber(index); break;
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please enter again !");
            }
        }
    }

    //Edit customer ---------------------------------------------
    public void editCustomer(){
        if (Room.countVip ==0 && Room.countNormal==0) {
            System.out.println("Has no one to edit !");
        } else {
            while (true){
                System.out.print("Enter 1 to edit by name or 2 to edit by ID card: ");
                byte choice = sc.nextByte();
                if (choice ==1){
                    System.out.println("Search name you want to edit: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    if(checkName(name)!= -1) {
                        menuEditing(checkName(name));
                        return;
                    } else {
                        System.out.print("This name doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                        String decision = sc.next();
                        if (decision.equalsIgnoreCase("y")){
                            showCustomer(); return;
                        }else if (decision.equalsIgnoreCase("n")){
                            System.out.println("Continue... ");
                        }
                    }

                } else if (choice == 2){
                    System.out.println("Search ID card you want to edit: ");
                    sc.nextLine();
                    String id_card = sc.nextLine();
                    if(checkId_card(id_card)!= -1) {
                        menuEditing(checkId_card(id_card));
                        return;
                    } else {
                        System.out.print("This ID card doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                        String decision = sc.next();
                        if (decision.equalsIgnoreCase("y")){
                            showCustomer(); return;
                        }else if (decision.equalsIgnoreCase("n")){
                            System.out.println("Continue... ");
                        }
                    }

                } else {
                    System.out.println("Please enter again !");
                }
            }
        }
    }

    //Report revenue for date
    public void reportRevenueForDate(){
        int totalCustomer = 0;
        float totalRevenue = 0;
        String searchDate;
        while (true){
            System.out.print("\nEnter date you want to revenue: ");
            searchDate = sc.next();
            if(checkDate.matcher(searchDate).find()){
                break;
            } else {
                System.out.println("Enter date with format yyyy-mm-dd !");
            }
        }
        System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n", "ORDINAL","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
        for (int i = 0; i<customers.size();i++){
            if(compareDay( searchDate,customers.get(i).getHiredDate())==2){
                totalCustomer++;
                totalRevenue = totalRevenue+getPriceByIndex(i);
                System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17d %-11.3f %-14s %-10s \n",
                        (i + 1), customers.get(i).getLastName(), customers.get(i).getName(), customers.get(i).getAge(), customers.get(i).getRoom().getType(), customers.get(i).getId_card(), customers.get(i).getNumberRent(), getPriceByIndex(i),customers.get(i).getPhone(),customers.get(i).getHiredDate());
            }
        }
        System.out.println("\nTotal customers at day "+searchDate+" :"+totalCustomer);
        System.out.println("Total Revenue at day "+searchDate+" :"+totalRevenue);
    }
    // Report revenue by options (date or room) ---------------------------------------------
    public void reportRevenue(){
        if (Room.countVip ==0 && Room.countNormal==0) {
            System.out.println("Has no one to report !");
        } else {
            while(true){
                System.out.println("Enter 1 report revenue for date \nEnter 2 to report revenue for each room ");
                int choice = sc.nextInt();
                if (choice ==1){
                    reportRevenueForDate(); return;
                } else if (choice ==2){
                    float revenueVipRoom = 0;
                    float revenueNormalRoom = 0;
                    int countNumberRentVipRoom = 0;
                    int countNumberRentNormalRoom = 0;
                    for (int i = 0; i < customers.size(); i++){
                        if (customers.get(i).getRoom().getType().equals("VIP")) {
                            revenueVipRoom += getPriceByIndex(i);
                            countNumberRentVipRoom += customers.get(i).getNumberRent();
                        } else {
                            revenueNormalRoom += getPriceByIndex(i);
                            countNumberRentNormalRoom += customers.get(i).getNumberRent();
                        }
                    }
                    System.out.printf("\n%-6s \t %-7s \t %-20s \t %-5s ","ROOM","REVENUE(VND)","NUMBER RENT(day)","TOTAL");
                    System.out.printf("\n%-6s \t %-7.3f \t %-20d \t %-5d ","Vip",revenueVipRoom,countNumberRentVipRoom,Room.countVip);
                    System.out.printf("\n%-6s \t %-7.3f \t %-20d \t %-5d ","Normal",revenueNormalRoom,countNumberRentNormalRoom,Room.countNormal);
                    return;
                } else {
                    System.out.println("Invalid !");
                }
            }
        }
    }

    //Compare two date if date > date1 -> return 1 | if date == date1 -> return 2 | date < date1 -> return 0
    public int compareDay( String date, String date1){
        byte check = 0;
        String[] dateList = date.split("-");
        int[] dateListInt = new int[3];
        dateListInt[0]= Integer.parseInt(dateList[0]);
        dateListInt[1]= Integer.parseInt(dateList[1]);
        dateListInt[2]= Integer.parseInt(dateList[2]);

        String[] dateList1 = date1.split("-");
        int[] dateListInt1 = new int[3];
        dateListInt1[0]= Integer.parseInt(dateList1[0]);
        dateListInt1[1]= Integer.parseInt(dateList1[1]);
        dateListInt1[2]= Integer.parseInt(dateList1[2]);

        if (dateListInt[0]> dateListInt1[0]) {
            check = 1;
        } else if (dateListInt[0]== dateListInt1[0]){
            if (dateListInt[1]> dateListInt1[1]){
                check = 1;
            }else if (dateListInt[1]== dateListInt1[1]){
                if (dateListInt[2]> dateListInt1[2]){
                    check =1;
                } else if (dateListInt[2]== dateListInt1[2]){
                    check = 2;
                }
            }
        }

        return check;
    }

    //Search by ID card and return index
    public int searchIdCard(String idCard){
        int position = -1;
        for (int i = 0; i< customers.size();i++){
            if (idCard.equals(customers.get(i).getId_card())){
                position = i;
            }
        }
        return position;
    }

    //Search by phone and return index
    public int searchPhone(String phone){
        int position = -1;
        for (int i = 0; i< customers.size();i++){
            if (phone.equals(customers.get(i).getPhone())){
                position = i;
            }
        }
        return position;
    }

    //Search by name and return index
    public int searchName(String name){
        int position = -1;
        for (int i = 0; i< customers.size();i++){
            if (name.equals(customers.get(i).getName())){
                position = i;
            }
        }
        return position;
    }

    //Search by name and print
    public void searchByName(String name){
        System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n", "ORDINAL","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
        for (int i = 0; i< customers.size();i++){
            if (name.equals(customers.get(i).getName())){
                System.out.printf("%-7s %-15s %-15s %-6d %-10s %-15s %-17d %-11.3f %-14s %-10s\n", (i+1),customers.get(i).getLastName(),customers.get(i).getName(),
                        customers.get(i).getAge(), customers.get(i).getRoom().getType(), customers.get(i).getId_card(),
                        customers.get(i).getNumberRent(), getPriceByIndex(i),customers.get(i).getPhone(),customers.get(i).getHiredDate());
            }
        }
    }

    //Search customer

    public void searchCustomer(){
        if (Room.countVip ==0 && Room.countNormal==0) {
            System.out.println("Has no one to search !");
        } else{
            while(true){
                System.out.println("""
                Enter 1 to search by name
                Enter 2 to search by ID card
                Enter 3 to search by phone""");
                int choice = sc.nextInt();
                switch (choice){
                    case 1 -> {
                        sc.nextLine();
                        while(true){
                            System.out.print("Enter first name: ");
                            String firstName = sc.nextLine();
                            if (!(checkName.matcher(firstName).find())){
                                System.out.println("First name hasn't number !");
                            }else if(searchName(firstName)!= -1){
                                searchByName(firstName); return;
                            }
                            else {
                                System.out.print("This name doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                                String decision = sc.next();
                                if (decision.equalsIgnoreCase("y")){
                                    showCustomer(); return;
                                }else if (decision.equalsIgnoreCase("n")){
                                    System.out.println("Continue... ");
                                }
                            }
                        }
                    }
                    case 2 ->{
                        sc.nextLine();
                        while(true){
                            System.out.print("Enter ID card: ");
                            String id_card = sc.nextLine();
                            if (checkID.matcher(id_card).find()){
                                for (int i = 0;i<customers.size();i++){
                                    if (searchIdCard(id_card)!=-1){
                                        int j = searchIdCard(id_card);
                                        System.out.printf(" %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
                                        System.out.printf(" %-15s %-15s %-6d %-10s %-15s %-17d %-11.3f %-14s %-10s\n",customers.get(i).getLastName(),customers.get(i).getName(),
                                                customers.get(j).getAge(), customers.get(j).getRoom().getType(), customers.get(j).getId_card(),
                                                customers.get(j).getNumberRent(), getPriceByIndex(j),customers.get(j).getPhone(),customers.get(j).getHiredDate());
                                    }
                                }
                                return;
                            }else {
                                System.out.print("This ID card doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                                String decision = sc.next();
                                if (decision.equalsIgnoreCase("y")){
                                    showCustomer(); return;
                                }else if (decision.equalsIgnoreCase("n")){
                                    System.out.println("Continue... ");
                                }
                            }
                        }
                    }
                    case 3 ->{
                        sc.nextLine();
                        while(true){
                            System.out.print("Enter phone number: ");
                            String phone = sc.nextLine();
                            if (checkPhone.matcher(phone).find()){
                                for (int i = 0;i<customers.size();i++){
                                    if (searchPhone(phone)!=-1){
                                        int j = searchPhone(phone);
                                        System.out.printf("%-7s %-15s %-15s %-6s %-10s %-15s %-17s %-11s %-14s %-10s\n", "ORDINAL","LAST NAME", "FIRST NAME", "AGE", "ROOM", "ID CARD", "NUMBER RENT(day)", "PRICE(VND)","PHONE","HIRED DATE");
                                        System.out.printf("%-7s %-15s %-15s %-6d %-10s %-15s %-17d %-11.3f %-14s %-10s\n", (i+1),customers.get(j).getLastName(),customers.get(i).getName(),
                                                customers.get(j).getAge(), customers.get(j).getRoom().getType(), customers.get(j).getId_card(),
                                                customers.get(j).getNumberRent(), getPriceByIndex(j),customers.get(j).getPhone(),customers.get(j).getHiredDate());
                                    }
                                }
                                return;
                            }else {
                                System.out.print("This phone doesn't exist ! \nDo you want to show customers? \nEnter \"y\" to show customer, \"n\" to stay: ");
                                String decision = sc.next();
                                if (decision.equalsIgnoreCase("y")){
                                    showCustomer(); return;
                                }else if (decision.equalsIgnoreCase("n")){
                                    System.out.println("Continue... ");
                                }
                            }
                        }
                    }
                    default -> {
                        System.out.println("Invalid !");
                    }
                }
            }
        }
    }
    // Menu options----------------------------------------------------------
    public void menu() {
        while (true){
            System.out.println();
            System.out.print("""        
                WELCOME PARADISE HOTEL
                1. Input customer
                2. Show customers
                3. Get bill for customers
                4. Sort customers
                5. Delete customer
                6. Edit customer
                7. Report revenue
                8. Search customer
                0. Exit
                Please enter your choice:""");
            try{
                byte choice = sc.nextByte();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        inputCustomer(); break;
                    case 2:
                        showCustomer(); break;
                    case 3:
                        getBill1(); break;
                    case 4:
                        sortCustomers(); break;
                    case 5:
                        deleteCustomer(); break;
                    case 6:
                        editCustomer(); break;
                    case 7:
                        reportRevenue(); break;
                    case 8:
                        searchCustomer(); break;
                    case 0:
                        return;
                    default:
                        System.err.println("\nInvalid !");
                }
            } catch (InputMismatchException e){
                System.err.println("\n Invalid !");
                sc.nextLine();
            }

        }
    }

    //Method nhập cứng
//    public void nhapCung(){
//        customers.add(new Person("Dat","Nguyen Quoc",10,new RoomNormal(),"321654987",5,"032.236.1238","2021-7-5"));
//        customers.add(new Person("Huy","Nguyen Quoc",20,new RoomVip(),"325654987",3,"032.256.1235","2021-7-8"));
//        customers.add(new Person("Duong","A",19,new RoomNormal(),"123456789",9,"052.326.3265","2021-7-5"));
//        customers.add(new Person("Quynh","Ho Van",15,new RoomVip(),"523521548",4,"032.415.1422","2021-7-20"));
//    }
}
