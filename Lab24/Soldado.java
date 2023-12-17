public class Soldado {
    private String name;
    private int levelAttack;
    private int levelDefense;
    private int levelLife = 0;
    private int speed;
    private String posture;
    private boolean live;
    private int row;
    private int column;
    public static final int MAX_COUNT_SOLDIER = 10;
    //------constructor--------
    public Soldado(){
        this(null, 0, 0);
        this.live = true;
    }
    public Soldado (String name, int row, int column){
        this.name = name;
        this.row = row;
        this.column = column;
    }    //------set----------
    public int generateLevel(int a, int b){return (int)(Math.random()*(b-a)+(a));}
    public void setName(String name){this.name = name;}
    public void setVidaActual(int levelLife){this.levelLife = levelLife;}
    public void setNiveldeAtaque(int levelAttack){this.levelAttack = levelAttack;}
    public void setNiveldeDefensa(int levelDefense){this.levelDefense = levelDefense;}
    public void setFila(int row){this.row = row;}
    public void setColumna(int column){this.column = column;}
    //-----get-----------
    public String getName(){return name;}
    public int getVidaActual(){return levelLife;}
    public int getNiveldeAtaque(){return levelAttack;}
    public int getNiveldeDefensa(){return levelDefense;}
    public int getFila() {return row;}
    public int getColumna() {return column;}
    //-----methods-------
    public void atacar(){
        this.posture = "ofensiva";
        System.out.println("La actitud es: "+posture);
        avanzar(1);
    }
    public void defender(){
        this.posture = "defensiva";
        avanzar(0);
    }
    public void huir(){
        this.posture = "fuga";
        avanzar(2);
    }
    public void avanzar(int n){
        if(n==0){
            speed = 0;
            System.out.println("El soldado se paro");
        }else{
            speed +=n;
            System.out.println("El soldado avanza "+speed);
        }
    }
    public void retroceder(){
        if(speed>0)
            defender();
        if(speed == 0)
            speed--;
    }
    public void serAtacado(){
        levelLife--;
        if(levelLife == 0){
            morir();
        }
    }
    public void morir(){
        this.live = false;
        System.out.println("El soldado ha muerto :(");
    }
    public String toString(){
        return "Nombre: "+name+" |Nivel de vida: "+levelLife+
        "|Nivel de Ataque: "+levelAttack+" |Nivel de Defensa:"+levelDefense+"|Fila: "+row+" |Columna: "+column;
    }
}
