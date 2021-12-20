package FinalProject;
import java.util.*;
import java.util.regex.Pattern;

public class Person   {

    private String name;
    private String lastName ;
    private int age;
    private String id_card;
    private int numberRent;
    private Room room;
    private String phone;
    private String hiredDate;

    //Use Pattern to formatting input values
    static Pattern checkDate = Pattern.compile("^(20[0123][0-9])-(0?(([13578])|10|12))-(0?[1-9]|[12][0-9]|3[0-1])$|^(20[0123][0-9])-(0?(([469])|11))-(0?[1-9]|[12][0-9]|30)$|^((200[048])|(201[26])|(202[048])|(203[26]))-2-(0?[1-9]|[12][0-9])$|^(((200[1-3|5-7])|2009)|(2011|(201[3-5|7-9]))|((202[1-3|5-7])|2029)|(2031|(203[3-5|7-9])))-2-(0?[1-9]|[12][0-8])$");
    static Pattern checkName = Pattern.compile("^[a-z A-z]{1,50}+$");
    static Pattern checkPhone = Pattern.compile("^0[0-9]{2}.[0-9]{3}.[0-9]{4}+$");
    static Pattern checkID = Pattern.compile("^[0-9]+$");

    Scanner sc = new Scanner(System.in);
//Constructor
    public Person(String name,String lastName, int age, Room room, String id_card, int numberRent, String phone, String hiredDate){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.room = room;
        this.id_card = id_card;
        this.numberRent = numberRent;
        this.phone = phone;
        this.hiredDate = hiredDate;
    }

    public Person(){ };
//Setter and Getter

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Room getRoom() {
        return room;
    }

     void setRoom(Room room) {
        this.room = room;
    }

     String getName() {
        return name;
    }

     void setName(String name) {
        this.name = name;
    }

     int getAge() {
        return age;
    }

     void setAge(int age) {
        if(age >0 && age <123) this.age = age;
    }

     String getId_card() {
        return id_card;
    }

     void setId_card(String id_card) {
        if (id_card.length() == 9  || id_card.length() == 13 )this.id_card = id_card;
    }

     int getNumberRent() {
        return numberRent;
    }

     void setNumberRent(int numberRent) {
        this.numberRent = numberRent;
    }

     String getLastName() {
        return lastName;
    }

     void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Method input person
     void inputPerson() {

        while(true){
            System.out.print("Enter first name of person: ");
            String nameInput = sc.nextLine();
            if (checkName.matcher(nameInput).find()){
                setName(nameInput); break;
            } else System.out.println("\nFirst name hasn't number !");
        }
        while(true){
            System.out.print("Enter last name of person: ");
            String lastNameInput = sc.nextLine();
            if (checkName.matcher(lastNameInput).find()){
                setLastName(lastNameInput); break;
            }else System.out.println("\nLast name hasn't number !");
        }

        while (true){
            try{
                System.out.print("Enter age of person: ");
                setAge(Integer.parseInt(sc.nextLine()));
                if(getAge()!=0) break;
                else System.out.println("Invalid !");
            }catch ( InputMismatchException e){
                System.out.println("Invalid !");
            }catch (NumberFormatException e2){
                System.out.println("Invalid  !");
            }
        }
        while (true ){
            System.out.print("Enter ID card of person: ");
            String idCard = sc.nextLine();
            setId_card(idCard);
            if (Hotel.checkIdCardExist(idCard)){
                System.out.println("This ID card has exist !");
            }
            else if (checkID.matcher(idCard) .find() && getId_card() != null) break;
            else System.out.println("\nInvalid !\nID card should be number and consists of 9 or 13 numbers ");
        }
        while (true){
            try{
                System.out.print("Enter number day for rent room: ");
                setNumberRent(Integer.parseInt(sc.nextLine()));
                if (getNumberRent()> 0 && getNumberRent()< 31)  break;
                else System.out.println("Time for rent room from 1 to 30 day(s)");
            }catch (InputMismatchException e){
                System.out.println("Invalid !");
            } catch (NumberFormatException e3){
                System.out.println("Invalid  !");
            }
        }
        while (true){
            System.out.print("Enter phone number: ");
            String phone = sc.nextLine();
            if (Hotel.checkPhoneExist(phone)){
                System.out.println("This phone has exist !");
            }
            else if (checkPhone.matcher(phone ).find()) {

                setPhone(phone);
                break;
            }
            else System.out.println("\nInvalid ! \nPhone number must has format 0xx.xxx.xxxx !");
        }
        while (true){
            System.out.print("Enter the date for which the room was reserved: ");
            String date = sc.nextLine();
            if (checkDate.matcher(date).find()) {
                setHiredDate(date);
                break;
            }
            else System.out.println("\nInvalid !\nEnter date with format yyyy-mm-dd !");
        }
    }

}
