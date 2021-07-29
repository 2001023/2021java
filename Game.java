

import java.util.Scanner;

public class Game {
    private final int SHIP_NUM=3;
    private final int MAP_SIZE=5;
    private Ship[] ships=new Ship[SHIP_NUM];
    private Map map =new Map();


    public Game(){
        for(int i = 0;i<SHIP_NUM;i++){
            ships[i]=new Ship();
        }
    }


    public void init(){
        map.init(MAP_SIZE);
        for(int i=0; i< SHIP_NUM;i++){
            do{
                ships[i].init(MAP_SIZE);
            }while(!map.deployShip(ships[i]));
        }
    }

    public void execute(){
        int attackX;
        int attackY;

        Scanner scanner = new Scanner(System.in);
        int turn = 1;

        title();
        while(!isAllsink()){
            System.out.println("[ターン"+turn+"]");
            displaySituation();
            System.out.println("爆弾の座標Xを入力(1-"+MAP_SIZE+")");
            attackX=scanner.nextInt();
            System.out.println("爆弾の座標Yを入力(1-"+MAP_SIZE+")");
            attackY=scanner.nextInt();

            for(int i =0;i<SHIP_NUM;i++){
                int result = ships[i].check(attackX-1,attackY-1);

                doByResult(ships[i],i+1,result);
            }
            turn++;
        }
        System.out.println("全て撃沈おめでとう");

        scanner.close();
    }

    private void title(){

        System.out.println("戦艦ゲーム");

    }

    private boolean isAllsink(){
        boolean allsink=true;
        for(int i =0;i < SHIP_NUM;i++){
            if(ships[i].isAlive()){
                allsink=false;
                break;
            }
        }

        return allsink;
    }
    private void displaySituation(){
        for(int i =0;i<SHIP_NUM;i++){
            if(ships[i].isAlive()){
                System.out.println("船"+(i+1)+":生きてる");
            }else{
                System.out.println("船"+(i+1)+":撃沈済み");
            }
        }
    }

    private void doByResult(Ship ship,int no,int result){
        if(result == Ship.NO_HIT){
            System.out.println("船"+no+":はずれ!");
        }else if(result==Ship.NERE){
            System.out.println("船"+no+":波高し!");
        }else if(result==Ship.HIT){
            System.out.println("船"+no+":爆弾が当たった!しかし船はまだ沈まない!船は移動します");
            moveShip(ship);
        }else{
            System.out.println("船"+no+":爆弾が当たった!撃沈しました!");
            map.clear(ship.getPostX(),ship.getPostY());
        }
    }

    private void moveShip(Ship ship){
        map.clear(ship.getPostX(),ship.getPostY());
        do{
            ship.move(MAP_SIZE);
        }while(!map.deployShip(ship));
    }
}