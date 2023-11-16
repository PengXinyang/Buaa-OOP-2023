package file;

public class OverridePath {
    public static String overridePath(String arc){
        if(arc.startsWith("./")){
            String s2=arc.substring(2);
            return "./data/"+s2;
        }
        else return "./data/"+arc;
    }
}
