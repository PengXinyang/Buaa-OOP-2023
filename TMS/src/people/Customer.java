package people;

import favorite.Favorite;
import order.Order;
import shop.Shop;
import variable.GlobalClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Customer extends Person implements Serializable {
    private Map<String, Order>orderMap=new LinkedHashMap<>();//每个顾客的订单
    private ArrayList<Favorite>favorites=new ArrayList<>();
    public Customer(){
        super();
    }

    public ArrayList<Favorite> getFavorites() {
        return favorites;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }
    public void setOrders(Order order) {
        orderMap.put(order.getOrderId(),order);
    }
    public Customer(String kakafeeCode, String name, String password){
        super(kakafeeCode, name, password, "Customer", true);
    }
    public static void listShops(){//查看商店
        List<Map.Entry<String,Shop>> s1=GlobalClass.setShopSort();
        for(Map.Entry<String, Shop> s: s1){//输入时应能保证全局商店按照id顺序输入，所以不用排序
            if(s.getValue().isStatus())System.out.println(s.getKey()+" "+s.getValue().getShopName());
        }
    }
}
