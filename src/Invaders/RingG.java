package Invaders;

/**
 *
 * @author Link
 */
public class RingG extends Ring {

    public RingG(Space space){

        super(space);
        setSpriteNames(new String[]{"ringG_0.png","ringG_1.png","ringG_2.png","ringG_3.png","ringG_4.png",
        "ringG_5.png","ringG_6.png","ringG_7.png","ringG_8.png","ringG_9.png","ringG_10.png","ringG_11.png"});
        frameSpeed=3;
        Vy=2;

    }

@Override
    public void anima(){
    Vy=0;
    x=space.getPlayer().x+(space.getPlayer().getWidth ()/2)-(width/2);
    y=space.getPlayer().y+(space.getPlayer().getHeight()/2)-(height/2);
    frameSpeed=1;
    if( space.getPlayer().shield == space.getPlayer().MAX_SHIELD )  {reaper();}
}


}
