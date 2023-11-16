package variable;

import commodity.*;
import favorite.Favorite;
import people.*;
import shop.Shop;
import order.Order;

import java.io.Serializable;
import java.util.*;

public class GlobalClass implements Serializable {
    public static Map<String, Person> map = new HashMap<>();
    public static Map<String,Merchant> MerchantMap=new HashMap<>();
    public static Map<String, Customer> CustomerMap=new HashMap<>();
    public static Map<String,Administrator> AdministratorMap=new HashMap<>();

    public static Map<String, Shop>Shop=new HashMap<>();
    public static Map<String, CommodityUnit>CommodityUnits=new HashMap<>();
    public static Map<String, Order>orderMap=new LinkedHashMap<>();
    public static Map<Integer,Favorite>favorites=new LinkedHashMap<>();
    public static boolean logStatus;
    public static Person personLogged;
    public static int maxShopAmount=5;
    public static int currentShopId=0;//注销店铺，下架商品，ID号不变
    public static int currentCommodityId=0;
    public static int shopAmount=0;//注销店铺，下架商品，数量减一
    public static int commodityUnitsAmount=0;//种类数
    public static int orderAmount=0;//订单数
    public static int favoriteId=0;
    //对全局商品编号进行排序
    public static List<Map.Entry<String, CommodityUnit>> setCommodityUnitsSort(){
        List<Map.Entry<String, CommodityUnit>> list1 = new ArrayList<>(GlobalClass.CommodityUnits.entrySet());
        Collections.sort(list1, Comparator.comparingInt(o -> o.getValue().getNumberId()));
        return list1;
    }
    //对全局店铺编号排序
    public static List<Map.Entry<String, Shop>> setShopSort(){
        List<Map.Entry<String, Shop>> list2 = new ArrayList<>(GlobalClass.Shop.entrySet());
        Collections.sort(list2, Comparator.comparingInt(o -> o.getValue().getNumberId()));
        return list2;
    }

}
