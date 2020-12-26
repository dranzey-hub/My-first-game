package Invaders;
    
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Link
 */
public class Invaders extends Canvas implements Space, KeyListener{
    
    private long iterationTime,startTime;
    private BufferStrategy strategy;
    private SpriteCache spriteCache;
    private SoundCache soundCache;
    private ArrayList actors;
    private Player player;
    private Flame flame;                       //TEMP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private int t=0,T=300,bgy=-60,bgx=bgy/2,finalCount=0,shieldPos=54,shieldCount=0,shieldTime=5;
    private int ignignokts=0,errs=0,waiting=0;
    public static int time=Time;
    private byte fase=0;
    private boolean dead=false,BOSS=false,oportunity=true,                      arwingTests=false;
    private BufferedImage moon,shielding;
    private String[] shieldVector;
    static boolean END=false;

    public Invaders(){
        spriteCache = new SpriteCache();
        soundCache = new SoundCache();
        JFrame marco = new JFrame("The mOON Rulz!!!!!!!");
        JPanel panel = (JPanel)marco.getContentPane();
        setBounds(0,0,X,Y);
        panel.setPreferredSize(new Dimension (X,Y));
        panel.setLayout(null);
        panel.add(this);
        marco.setBounds(210,15,X,Y);
       // marco.setLocationRelativeTo(null);
        marco.setVisible(true);
        marco.setIgnoreRepaint(true);
        marco.addWindowListener( new WindowAdapter(){
             public void windowClosing(WindowEvent e){System.exit(0);}} );
        marco.setResizable(false);
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        requestFocus();
        addKeyListener(this);
        moon=spriteCache.getSprite("moon_3qt.png");
        shieldVector=new String [] {//"shield1.png","shield2.png","shield3.png","shield4.png","shield5.png","shield6.png","shield7.png",
       // "shield8.png","shield9.png","shield10.png","shield11.png","shield12.png","shield13.png","shield14.png","shield15.png",
        //"shield16.png","shield17.png","shield18.png","shield19.png","shield20.png","shield21.png","shield22.png","shield23.png"};
        "shield__13.png","shield__14.png","shield__15.png","shield__16.png","shield__17.png","shield__18.png","shield__19.png",
        "shield__20.png","shield__21.png","shield__22.png","shield__23.png","shield__24.png","shield__25.png","shield__26.png",  //18 EACH
        "shield__27.png","shield__28.png","shield__29.png","shield__30.png",
        "shield1__13.png","shield1__14.png","shield1__15.png","shield1__16.png","shield1__17.png","shield1__18.png","shield1__19.png",
        "shield1__20.png","shield1__21.png","shield1__22.png","shield1__23.png","shield1__24.png","shield1__25.png","shield1__26.png",
        "shield1__27.png","shield1__28.png","shield1__29.png","shield1__30.png",
        "shield2__13.png","shield2__14.png","shield2__15.png","shield2__16.png","shield2__17.png","shield2__18.png","shield2__19.png",
        "shield2__20.png","shield2__21.png","shield2__22.png","shield2__23.png","shield2__24.png","shield2__25.png","shield2__26.png",
        "shield2__27.png","shield2__28.png","shield2__29.png","shield2__30.png",
        "shield_glowing_13.png","shield_glowing_14.png","shield_glowing_15.png","shield_glowing_16.png","shield_glowing_17.png","shield_glowing_18.png","shield_glowing_19.png",
        "shield_glowing_20.png","shield_glowing_21.png","shield_glowing_22.png","shield_glowing_23.png","shield_glowing_24.png","shield_glowing_25.png","shield_glowing_26.png",
        "shield_glowing_27.png","shield_glowing_28.png","shield_glowing_29.png","shield_glowing_30.png"};
        new TimeWave(this);
        new X(this);
        //new Bomb();
        new Errignignokt(this);
        new BoostLaser(this);
        
    }


    public void createWorld(){
        actors = new ArrayList();
        if(!arwingTests){
        
        for (int i=0;i<5;i++){
            Ignignokt m = new Ignignokt(this,false);
            m.setx((int)(Math.random()*(X-200)+50));
            m.sety(i*50-(int)(Math.random()*200));
            m.setVx((int)(Math.random()*X/40 +5));
            actors.add(m);
        }
        for (int i=0;i<15;i++){
            Monster m = new Monster(this);
            m.setx((int)(Math.random()*(X-50)));
            m.sety(i*15);
            m.setVx((int)(Math.random()*X/20 +5));
            actors.add(m);
        }
        }
        player = new Player(this);
        flame = new Flame(this);
        actors.add(flame);
        player.setx(Space.X/2);
        player.sety(Space.Y-2*player.getHeight());
        //soundCache.loopSound("corneria.wav");///////////////////////////////////MUSIIKKI♪♫♪
    }


public void checkCollisions(){
        Rectangle playerBounds = player.getBounds();
        for(int i=0;i<actors.size();i++){
            Live actor0 = (Live)actors.get(i);
            Rectangle actor0Bounds = actor0.getBounds();
            if(actor0Bounds.intersects(playerBounds)){
                player.collision(actor0);
                actor0.collision(player);
            }
            for(int j=i+1;j<actors.size();j++){
                Live actor1 = (Live)actors.get(j);
                Rectangle actor1Bounds = actor1.getBounds();
                if(actor0Bounds.intersects(actor1Bounds)){
                    actor0.collision(actor1);
                    actor1.collision(actor0);
                }
            }
        }

    }                   
                        
    public void paintStatus(Graphics2D g){
        paintShield(g);
        paintX(g);
        paintScore(g);
        paintAmmo(g);
    }

     public void shield(Graphics2D g){
       if(!dead){
       if(player.shield>4) {

         shieldCount++;  if(shieldCount%shieldTime==0){
         shieldPos++;
            if(player.shield==200)                          {if(shieldPos>shieldVector.length-1)shieldPos=54;}
            else if(player.shield>99)                       {if(shieldPos>17)shieldPos=0;}
            else if(player.shield<100 && player.shield>20)  {if(shieldPos>35)shieldPos=18;}
            else if(player.shield<=20)                      {if(shieldPos>53)shieldPos=36;}
            
            shielding=spriteCache.getSprite(shieldVector[shieldPos]);
         }  
         g.drawImage(shielding,(int)(player.x-1),(int)(player.y-2),this); }
      }
    }

    public void paintWorld(){
        Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, X, Y);
        if(!END)g.drawImage(moon,bgx,bgy,this);
        if(!END)player.paint(g);
        for(int i=0;i<actors.size();i++){
            Live m = (Live)actors.get(i);
            m.paint(g);
        }
        if(player.hit||player.manualShield) shield(g);
        if(!END)paintStatus(g);
        
     strategy.show();
    }



/*~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~*/


    public void updateWorld(){
        ignignokts=0; errs=0;
        xtra();
        if(!END)player.act();
        for(int i=0;i<actors.size();i++){
            Live m = (Live)actors.get(i);
            //      THE REAPER  _/
            if(m.isMarked()){
                actors.remove(i); i--;
            }
            // ACTION!!
            else{m.act();  //MONSTER COUNTER
                 if(m instanceof Ignignokt){
                     ignignokts++;}
                 else if(m instanceof Monster){
                     errs++;}
            }
        }    /////////////////////////////////////////////////////////////FASE 0
        if(fase<1&&!END  &&!arwingTests){
            if(errs<15){   int random=(int)(Math.random()*50);
            Monster spawn = new Monster(this);
            spawn.x=(int)(Math.random()*(X-2*spawn.width));
            spawn.y=-37-random;
            spawn.Vx=(int)((Math.random()*50)+5);
            spawn.HP=20;
            actors.add(spawn);
            }
            if(ignignokts<4){
             for(int i=0;i<actors.size();i++){
            if(actors.get(i)instanceof Ignignokt){
                Ignignokt I = (Ignignokt)actors.get(i);
                int x = (int)(I.x);
                int y = (int)(I.y);
                Ignignokt II = new Ignignokt(this,true);
                actors.remove(i);
                II.setx(x);II.sety(y);
                actors.add(i,II);
                fase=1;
            }
          }
        }
        }                           //////////////////////////////////////FASE 1
        else if(fase<2&&!END  &&!arwingTests){
           if(ignignokts<1){
               if(errs==1){
                 for(int i=0;i<actors.size();i++){
                   if(actors.get(i)instanceof Monster){
                   Monster Err = (Monster)actors.get(i);
                   int x = (int)(Err.x); int y = (int)(Err.y);
                   Err.reaper();
                   Animation anim = new Animation(this,x,y);actors.add(anim); fase=2;time = Time;}
                 }
               }
               else if(errs<1){
                 Animation anim = new Animation(this,400,200);actors.add(anim);fase=2;time = Time;
               }
           }
        }                                               //////////////////FASE 2
        else if(fase<3&&!END  &&!arwingTests){
          waiting++;
          //soundCache.stopSound("corneria.wav");
          
          if(waiting>1300 && !BOSS){  //soundCache.playSound("26_boss_b.wav");
              Errignignokt ei = new Errignignokt(this);
              actors.add(ei);
              BOSS=true;
              ei.x=X/2; ei.y=Y/3;
          }
          //for(int i=0;i<actors.size();i++){
            //  if(actors.get(i)instanceof Errignignokt){

             // } //else END=true;                                    /////THE END
         // }
        }
        
    }

//-------------------------------ITEMS & STUFF----------------------------------

    public void xtra(){if(!END){
        t++;
        if(t%T==0)
        {
          double x = Math.random();
          bgy++;
          bgx=bgy/2;

          if(x<.075 && x>.025){
             RingS Rs=new RingS(this);
             Rs.x=(int)(Math.random()*(X-Rs.width));
             Rs.y=-35;
             actors.add(Rs);
             } else if(x<=.025 && fase>1){
                  RingG Rg=new RingG(this);
                  Rg.x=(int)(Math.random()*(X-Rg.width));
                  Rg.y=-25;
                  actors.add(Rg);
               }

          if(player.score>500 && player.laser==0 && oportunity){
              BoostLaser B = new BoostLaser(this);              
              B.x=(int)(x*(X-(2*B.width)));
              actors.add(B);
              oportunity=false;
          }

          if(x>.95){
              X xXx = new X(this);
              xXx.x=(int)(Math.random()*(X-60));
              xXx.y=-xXx.height;
              actors.add(xXx);
          }

          t=0;
          }
     }}


/* ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ */






//= = = = = = = = = = = = = = = = = = = = GAME ON!! = = = = = = = = = = = = = = = = = = = = = =


    public void game(){
        
        createWorld();
        //while(!player.start){}  <-    MENU HERE
          while (/*isVisible() && */!dead){
            
            startTime=System.currentTimeMillis();           
            player.recharge--;            
            try{checkCollisions();
               }catch(NullPointerException n){}
            updateWorld();
            paintWorld();
            //iterationTime=System.currentTimeMillis()-startTime;
            //try{Thread.sleep(Time);
            //}catch(InterruptedException e){}
            do{
                Thread.yield();
            }while(System.currentTimeMillis()-startTime < time);
        }
        
        while (finalCount<900 && dead){

            if(!END){                
                //soundCache.stopSound("corneria.wav");
                //soundCache.stopSound("26_boss_b.wav");
                Ending ending = new Ending(this);
                ending.x = 150; ending.y = 100;
                actors.clear();
                actors.add(ending);
                END=true;
            }
            startTime=System.currentTimeMillis();
            updateWorld();            
            paintWorld();
            finalCount++;
            do{
                Thread.yield();
            }while(System.currentTimeMillis()-startTime < 10);

        }System.exit(0);
    }



//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-





    public void gameOver(){
        dead=true;player.hit=true;
    }
    public Player getPlayer(){
        return player;
    }

    public void addActor(Live a){
        actors.add(a);
    }

    public SpriteCache getSpriteCache(){
        return spriteCache;
    }

    public SoundCache getSoundCache(){
        return soundCache;
    }

    public void keyPressed(KeyEvent e){
        player.keyPressed(e);
}
    public void keyReleased(KeyEvent e){
        player.keyReleased(e);
    }
    public void keyTyped(KeyEvent e){}






             public static void main (String[] args){

                  Invaders undefined = new Invaders();
                  undefined.game();
             }









 //STATISTICS
                        public void paintShield(Graphics2D g){
                          g.setFont(new Font("Miriam Fixed",Font.PLAIN,17));
                          g.setPaint(Color.WHITE);
                          g.drawString("Shield", 10, playgroundBottom);
                          if(player.shield>80){
                          GradientPaint shieldPaint = new GradientPaint(10,playgroundBottom,Color.red,10+player.shield,playgroundBottom,Color.BLUE);
                          g.setPaint(shieldPaint);
                          g.fillRect(10,playgroundBottom+8, 10+player.shield,playgroundBottom+20);
                          }
                          else {
                          GradientPaint shieldPaint = new GradientPaint(30,playgroundBottom,Color.red,30+player.shield,playgroundBottom,Color.magenta);
                          g.setPaint(shieldPaint);
                          g.fillRect(10,playgroundBottom+8, 10+player.shield,playgroundBottom+20);
                          }
                         }
                        public void paintX(Graphics2D g){
                            g.setPaint(Color.DARK_GRAY);
                            g.fillRoundRect(865, 220, 20, 180, 4, 4);
                            g.setPaint(Color.CYAN);
                            for(int i=395,j=0;j<player.bulletTime;j++,i=i-18){g.fillRoundRect(866,i-8,18,8,4,4);}
                        }
                        public void paintScore(Graphics2D g){
                            g.setFont(new Font("Miriam Fixed",Font.PLAIN,20));
                            g.setPaint(Color.LIGHT_GRAY);
                            g.drawString("Score ", 10, 20);
                            g.setPaint(Color.red);
                            g.drawString(String.valueOf(player.score), 82, 22);
                        }
                        public void paintAmmo(Graphics2D g){
                            int x = Space.X-Space.X/6;
                            BufferedImage bomb = spriteCache.getSprite("bombUR.gif");
                            for(int i=0;i<player.bombs;i++){
                                g.drawImage(bomb, x+i*bomb.getWidth(), playgroundBottom, this);
                            }
                        }




}



