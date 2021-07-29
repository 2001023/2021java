

public class Map {
    private final int NO_SHIP=0;
    private final int SHIP =1;
    private int[][] map;

    public Map(){
        ;
    }

    public void init(int mapSize) {
        map =new int[mapSize][mapSize];
        for(int i =0;i<mapSize;i++){
            for(int j =0;j<mapSize;j++){
                map[i][j]=NO_SHIP;
            }
        }
    }

    public boolean deployShip(Ship ship){
        int x = ship.getPostX();
        int y = ship.getPostY();

        if(x >=map.length || y >=map.length){
            return false;
        }

        if(map[x][y] !=NO_SHIP){
            return false;
        }

        map[x][y]=SHIP;

        return true;
    }

    public void clear(int x,int y){
        
        if(x >= map.length || y >= map.length){
             return;
         }

         map[x][y]=NO_SHIP;
    }
}