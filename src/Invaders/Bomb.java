package Invaders;
/**
 *
 * @author Link
 */
public class Bomb extends Live {
    public static final int U=0;
    public static final int D=1;
    public static final int R=2;
    public static final int L=3;
    public static final int UR=4;
    public static final int UL=5;
    public static final int DR=6;
    public static final int DL=7;
    protected static final int bombSpeed=4;
    protected static final float bombSpeedDiag=2.83f;//3.535534 for 5, 2.82843 for 4
    protected float Vx,Vy,xfloat,yfloat;

    public Bomb(Space space, int direction, float x, float y){
        super(space);
        this.x=x+7;
        this.y=y+10;
        String sprite=null;
        switch(direction){
            case U:  Vx=0; Vy=-bombSpeed; sprite="bombU.gif";break;
            case D:  Vx=0; Vy=bombSpeed; sprite="bombD.gif";break;
            case R:  Vx=bombSpeed; Vy=0; sprite="bombR.gif";break;
            case L:  Vx=-bombSpeed; Vy=0; sprite="bombL.gif";break;
            case UR: Vx=bombSpeedDiag; Vy=-bombSpeedDiag; sprite="bombUR.gif";break;
            case UL: Vx=-bombSpeedDiag; Vy=-bombSpeedDiag; sprite="bombUL.gif";break;
            case DR: Vx=bombSpeedDiag; Vy=bombSpeedDiag; sprite="bombDR.gif";break;
            case DL: Vx=-bombSpeedDiag; Vy=bombSpeedDiag; sprite="bombDL.gif";break;
        }
        setSpriteNames(new String[]{sprite});
    }
@Override
    public void act(){
        super.act();
        y+=Vy;
        x+=Vx;
        if(x<0 || x>Space.X || y<0 || y>Space.Y){
            reaper();
        }
    }
@Override
    public void collision(Live L){
        if(L instanceof Ignignokt||L instanceof Monster){
            reaper();
        }
}

}
