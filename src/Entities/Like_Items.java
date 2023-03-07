package Entities;

public class Like_Items {
    private int id_user ;
    private int id_item ;

    public Like_Items() {
    }

    public Like_Items(int id_user, int id_item) {
        this.id_item = id_item;
        this.id_user = id_user;
    }


    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id_user=" + id_user +
                ", id_item=" + id_item +
                '}';
    }
}
