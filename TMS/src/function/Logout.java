package function;
import people.Person;
import function.Login;
import variable.GlobalClass;

public class Logout {
    public static boolean logout(String []arc){
        if(arc.length!=1){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else{
            System.out.println("Bye~");
            Login.setLogStatus(false);
            return true;
        }
    }
}
