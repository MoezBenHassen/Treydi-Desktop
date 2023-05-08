package Entities;

public class Like_Items {
    private int userid ;
    private int itemid ;

    public Like_Items() {
    }

    public Like_Items(int userid, int itemid) {
        this.itemid = itemid;
        this.userid = userid;
    }


    public int getId_item() {
        return itemid;
    }

    public void setId_item(int id_item) {
        this.itemid = itemid;
    }

    public int getId_user() {
        return userid;
    }

    public void setId_user(int id_user) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id_user=" + userid +
                ", id_item=" + itemid +
                '}';
    }
}
