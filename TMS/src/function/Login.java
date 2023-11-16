package function;
import people.Person;
import variable.GlobalClass;

public class Login {
    public static void setLogStatus(boolean status){//登录
        GlobalClass.logStatus=status;
    }
    public static boolean login(String []arc){
        if(arc.length!=3){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(GlobalClass.logStatus){
            System.out.println("Already logged in");
            return false;
        }
        else if(!Operator.kakafeeIdJudge(arc[1])){
            System.out.println("Illegal Kakafee number");
            return false;
        }
        else if(!GlobalClass.map.containsKey(arc[1])){
            System.out.println("Kakafee number not exists");
            return false;
        }
        else{
            Person p=GlobalClass.map.get(arc[1]);
            if(!p.getPassword().equals(arc[2])){
                System.out.println("Wrong password");
                return false;
            }
            else{
                System.out.println("Welcome to TMS");
                GlobalClass.personLogged=GlobalClass.map.get(arc[1]);
                setLogStatus(true);
                return true;
            }
        }
    }
}
