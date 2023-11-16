package function;
import people.Person;
import variable.GlobalClass;

public class PrintInfo {
    public static boolean printInfo(String []arc){//打印信息
        if(!(arc.length==1||arc.length==2)){
            System.out.println("Illegal argument count");
            return false;
        }
        else if(!GlobalClass.logStatus){
            System.out.println("Please log in first");
            return false;
        }
        else if(arc.length==1){
            String message=GlobalClass.personLogged.toString();
            System.out.println(message);
            return true;
        }
        else{
            if(!GlobalClass.personLogged.getIdentify().equals("Administrator")){
                System.out.println("Permission denied");
                return false;
            }
            else if(GlobalClass.map.containsKey(arc[1])){
                Person p= GlobalClass.map.get(arc[1]);
                String message=p.toString();
                System.out.println(message);
                 return true;
            }
            else{
                System.out.println("Kakafee number not exist");
                return false;
            }
        }
    }
}
