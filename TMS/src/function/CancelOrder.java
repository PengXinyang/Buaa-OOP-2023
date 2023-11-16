package function;

import order.Order;
import people.Customer;
import variable.GlobalClass;

public class CancelOrder {
    public static boolean cancelOrder(String[] arc){
        if(arc.length!=2){
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
        Customer customer=GlobalClass.CustomerMap.get(GlobalClass.personLogged.getKakafeeCode());
        if(!Operator.orderIdJudge(arc[1])){
            System.out.println("Illegal order id");
            return false;
        }
        else if(!GlobalClass.orderMap.containsKey(arc[1])||!customer.getOrderMap().containsKey(arc[1])){
            System.out.println("Order id not exists");
            return false;
        }
        Order order=customer.getOrderMap().get(arc[1]);
        if(order.getStatus().equals("canceled")){
            System.out.println("Order already canceled");
            return false;
        }
        else if(order.getStatus().equals("finished")){
            System.out.println("Order already finished");
            return false;
        }
        else{
            order.getCommodity().setCount(order.getCommodity().getCount()+ order.getNumber());
            order.setStatus("canceled");
            System.out.println("Cancel order success");
            return true;
        }
    }
}
