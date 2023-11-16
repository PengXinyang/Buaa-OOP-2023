package function;

import commodity.Commodity;
import commodity.CommodityUnit;
import favorite.Favorite;
import order.Order;
import people.Customer;
import people.Merchant;
import shop.Shop;
import variable.GlobalClass;

public class FavoriteCommodity {
    public static boolean favoriteCommodity(String[]arc){
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
                    System.out.println("Illegal commodity quantity");
                    return false;
                }
                else{
                    int number=Integer.parseInt(arc[3]);
                    int r=0;
                    for(Favorite f:customer.getFavorites()){
                        Commodity C=f.getCommodity();
                        if(C.getShopId().equals(arc[1])&&C.getCommodityUnit().getId().equals(arc[2])){//说明之前已经收藏过
                            f.setNumber(f.getNumber()+number);
                            r=1;
                            break;
                        }
                    }
                    if(r==0){//没收藏过
                        Favorite f=new Favorite(c0,number,customer);
                        customer.getFavorites().add(f);
                        GlobalClass.favorites.put(f.getId(),f);
                    }
                    System.out.println("Favorite commodity success");
                    return true;
                }
            }
        }
    }
}
