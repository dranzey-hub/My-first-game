package Invaders;

/**
 *
 * @author Link
 */
public class Ring extends Item {

    public Ring(Space space){
        super(space);
        frameSpeed=4;
        Vy=1;
    }



@Override
    public void collision(Live L){
        if(L instanceof Player){
            space.getSoundCache().playSound("ring.wav");
            anima=true;
        }
}


}
