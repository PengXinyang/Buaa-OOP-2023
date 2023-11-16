package favorite;

import commodity.Commodity;
import people.Customer;
import variable.GlobalClass;

import java.io.Serializable;

public class Favorite implements Serializable {
    private Commodity commodity;//记录收藏了哪一家的哪一个商品
    private int number;//收藏数量
    private Customer customer;//记录是哪位顾客收藏的
    private int id;
    private boolean status;//判断是否被取消
    public Favorite(Commodity commodity, int number, Customer customer) {
        this.commodity = commodity;
        this.number = number;
        this.customer = customer;
        setId();
        status=true;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id= GlobalClass.favoriteId;
        GlobalClass.favoriteId++;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void printMessage(){
        System.out.print(commodity.getShopId()+": "+commodity.getCommodityUnit().getId()+" "+number+" "+String.format("%.2f",commodity.getCommodityUnit().getPrice())+"yuan\n");
    }
}
