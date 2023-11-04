package Lab14;
import java.util.ArrayList;
public class Reino {
    private String nameKingdom;
    private ArrayList<Ejercito> kingdom = new ArrayList<>();
    public Reino(){
        nameKingdom = generateKingdomName();
    }
    public static int generateArmies(){
        return (int)(Math.random()*10+1);
    }
    public String generateKingdomName(){
        String[] nameReino= {"Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros"};
        int n = (int)(Math.random()*nameReino.length);
        return nameReino[n];
    }
    public String getNameKingdom(){
        return nameKingdom;
    }
    public ArrayList<Ejercito> getArmies(){
        return kingdom;
    }
    public int countArmies(){
        return kingdom.size(); 
    }
    public String toString(){
        return "Reino "+nameKingdom;
    }  
}
