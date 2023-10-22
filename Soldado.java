class Soldado {
    private String name;
    private int levelAttack;
    private int levelDefense;
    private int levelLife;
    private int speed;
    private String posture;
    private boolean live;
    private int row;
    private int column;
    //------constructor--------
    public Soldado(){
        this("", 0, 0, 0, 0, 0);
    }
    public Soldado (String name, int levelLife, int levelDefense, int levelAttack, int row, int column){
        this.name = name;
        this.levelLife = levelLife;
        this.levelDefense = levelDefense;
        this.levelAttack = levelAttack;
        this.row = row;
        this.column = column;
    }
    public Soldado (int levelLife, int levelDefense, int levelAttack){
        this.levelLife = levelLife;
        this.levelDefense = levelDefense;
        this.levelAttack = levelAttack;
    }
    //------set----------
    public void setName(String name){
        this.name = name;
    }
    public void setVidaActual(int levelLife){
        this.levelLife = levelLife;
    }
    public void setNiveldeAtaque(int levelAttack){
        this.levelAttack = levelAttack;
    }
    public void setNiveldeDefensa(int levelDefense){
        this.levelDefense = levelDefense;
    }
    public void setFila(int row){
        this.row = row;
    }
    public void setColumna(int column){
        this.column = column;
    }
    //-----get-----------
    public String getName(){
        return name;
    }
    public int getVidaActual(){
        return levelLife;
    }
    public int getFila() {
        return row;
    }
    public int getColumna() {
        return column;
    }
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
    public void data(){
        System.out.println("Actitud: "+posture+" Vida: "+levelLife+" Velocidad: "+speed); 
    }
    public String toString(){
        return "Nombre: "+name+" |Nivel de vida: "+levelLife+" |Fila: "+row+" |Columna: "+column
        +" |Nivel de Ataque: "+levelAttack+" |Nivel de Defensa:"+levelDefense;
    }
}
