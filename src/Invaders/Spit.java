package Invaders;

/**
 *
 * @author Link
 */
public class Spit extends Live{
    protected int spitSpeed;
    protected int damage;
    protected int HP;


    public Spit(Space space,int speed,int damage,int frame){
        super(space);
        setSpriteNames(new String[]{"Err_Spit.png","Ignignokt_Spit.png"});
        spitSpeed=speed;
        this.damage=damage;
        currentFrame=frame;
        HP=1;
    }


@Override
    public void act(){
        y=y+spitSpeed;
        if(y>Space.Y||HP<0){
            reaper();
        }
}
@Override
    public void collision(Live L){
        if(L instanceof Bullet){
            HP--;
        }
        else if(L instanceof Player || L instanceof Bomb){
            reaper();
        }
}

public int getDamage(){
    return damage;
}
}
