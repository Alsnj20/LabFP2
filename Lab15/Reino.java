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
    public void fillArmy(Ejercito eje1){
        for (int i = 0; i < generateArmies(); i++) {
            kingdom.add(eje1);
        }
    }
    public String generateKingdomName(){
        String[] nameReino= {"Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros"};
        int n = (int)(Math.random()*nameReino.length-1);
        return nameReino[n];
    }
    public String getNameKingdom(){
        return nameKingdom;
    }
    public String toString(){
        return "Reino "+nameKingdom;
    }
    public int countArmies(){
        return kingdom.size(); 
    }
    public ArrayList<Ejercito> getArmies(){
        return kingdom;
    }
}
