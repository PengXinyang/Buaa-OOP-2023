package function;
import people.*;
import variable.GlobalClass;

public class Register {

    public static boolean register(String []ars){//注册

        if(ars.length!=6){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(GlobalClass.logStatus){
            System.out.println("Already logged in");
            return false;
        }
        else if(!Operator.kakafeeIdJudge(ars[1])){
            System.out.println("Illegal Kakafee number");
            return false;
        }
        else if( GlobalClass.map.containsKey(ars[1])){
            System.out.println("Kakafee number exists");
            return false;
        }
        else if (!Operator.nameJudge(ars[2])){
            System.out.println("Illegal name");
            return false;
        }
        else if(!Operator.passwordJudge(ars[3])){
            System.out.println("Illegal password");
            return false;
        }
        else if(!Operator.passwordConfirm(ars[4],ars[3])){
            System.out.println("Passwords do not match");
            return false;
        }
        else if(!Operator.identify(ars[5])){
            System.out.println("Illegal identity");
            return false;
        }
        else{
            Person p=new Person(ars[1],ars[2],ars[3],ars[5],true);
            GlobalClass.map.put(ars[1],p);
            switch(ars[5]){
                case "Merchant"->{
                    Merchant m=new Merchant(ars[1],ars[2],ars[3]);
                    GlobalClass.MerchantMap.put(ars[1],m);
                }
                case "Customer"->{
                    Customer c=new Customer(ars[1],ars[2],ars[3]);
                    GlobalClass.CustomerMap.put(ars[1], c);
                }
                case "Administrator"->{
                    Administrator A=new Administrator(ars[1],ars[2],ars[3]);
                    GlobalClass.AdministratorMap.put(ars[1],A);
                }
            }
            System.out.println("Register success");
            return true;
        }
    }
}
