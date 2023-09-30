class Soldado {
    private String name;
    private int lifeN;
    private int row;
    private int column;
    public Soldado (String name, int lifeN, int row, int column){
        setName(name);
        setLifeN(lifeN);
        setFila(row);
        setColumna(column);
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLifeN(int lifeN){
        this.lifeN = lifeN;
    }
    public void setFila(int row){
        this.row = row;
    }
    public void setColumna(int column){
        this.column = column;
    }
    public String getName(){
        return name;
    }
    public int getLifeN(){
        return lifeN;
    }
    public int getFila() {
        return row;
    }
    public int getColumna() {
        return column;
    }
    public String toString(){
        return "Nombre: "+name+" |Nivel de vida: "+lifeN+" |Fila: "+row+" |Columna: "+column;
    }
}
