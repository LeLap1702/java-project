package FinalProject;

public abstract class Room {

    static byte countVip;
    static byte countNormal;
    private float price;
    private String type;

    //Constructor
     Room(float price, String type){
        this.price = price;
        this.type = type;
    }

    //Getter and Setter
     float getPrice() {
        return price;
    }

     void setPrice(float price) {
        this.price = price;
    }

     String getType() {
        return type;
    }

     void setType(String type) {
        this.type = type;
    }

    public abstract String perform() ;
}

class RoomVip extends Room {
    public RoomVip(){
        super(500,"VIP");
        System.out.println(perform());
        countVip++;
    }
    @Override
    public String perform(){
        return "Room "+getType()+" has price "+getPrice()+"00VND";
    }
}

class RoomNormal extends Room {
    public RoomNormal(){
        super(300,"Normal");
        System.out.println(perform());
        countNormal++;
    }

    @Override
    public String perform(){
        return "Room "+getType()+" has price "+getPrice()+"00VND";
    }
}

