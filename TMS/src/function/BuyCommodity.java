package function;

import commodity.Commodity;
import commodity.CommodityUnit;
import order.Order;
import people.Customer;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

public class BuyCommodity {
    public static boolean buyCommodity(String[] arc){
        if(arc.length!=4){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(!GlobalClass.personLogged.getIdentify().equals("Customer")){
            System.out.println("Permission denied");
            return false;
        }
        else if(!Operator.shopIdJudge(arc[1])){
            System.out.println("Illegal shop id");
            return false;
        }
        else if(!GlobalClass.Shop.containsKey(arc[1])||!GlobalClass.Shop.get(arc[1]).isStatus()){
            System.out.println("Shop id not exists");
            return false;
        }
        else if(!Operator.commodityIdJudge(arc[2])){
            System.out.println("Illegal commodity id");
            return false;
        }
        else if(!GlobalClass.CommodityUnits.containsKey(arc[2])||!GlobalClass.CommodityUnits.get(arc[2]).isStatus()){
            System.out.println("Commodity id not exists");
            return false;
        }
        else{//先判断商店是否存在这个商品
            CommodityUnit cu=GlobalClass.CommodityUnits.get(arc[2]);
            Shop s=GlobalClass.Shop.get(arc[1]);
            Customer customer=GlobalClass.CustomerMap.get(GlobalClass.personLogged.getKakafeeCode());
            Commodity c0=new Commodity();
            int flag=0;
            for(Commodity c:s.getCommodities()){
                if(c.getCommodityUnit().equals(cu)){
                    c0=c;
                    flag=1;break;
                }
            }
            if(flag==0){
                System.out.println("Commodity id not exists");
                return false;
            }
            else{//再判断这个商品数量是否符合要求
                if(!Operator.commodityNumberJudge(arc[3])){
                    System.out.println("Illegal buy quantity");
                    return false;
                }
                else{
                    int number=Integer.parseInt(arc[3]);
                    if(number>c0.getCount()){
                        System.out.println("Illegal buy quantity");
                        return false;
                    }
                    else{
                        c0.setCount(c0.getCount()-number);
                        Order o=new Order(c0,number,s,customer,"pending");
                        customer.getOrderMap().put(o.getOrderId(),o);
                        Merchant m=s.getMerchant();
                        m.setOrders(o);
                        GlobalClass.orderMap.put(o.getOrderId(),o);
                        s.getOrderMap().put(o.getOrderId(),o);
                        System.out.println("Buy commodity success (orderId: O-"+o.getId()+")");
                        return true;
                    }
                }
            }
        }
    }
}
