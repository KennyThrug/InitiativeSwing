package data;

public class GamePiece implements Comparable<GamePiece>{

    public GamePiece(String name, int initiative){
        this.name = name;
        this.initiative = initiative;
    }
    public int getInitiative(){
        return initiative;
    }
    public String getName(){
        return name;
    }
    public String getData(){
        return data;
    }
    public void setData(String data){
        this.data = data;
    }

    int initiative;
    String data;
    String name;

    @Override
    public int compareTo(GamePiece o) {
        return o.getInitiative() - getInitiative();
    }
}
