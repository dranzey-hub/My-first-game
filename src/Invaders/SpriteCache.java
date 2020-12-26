package Invaders;


import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 *
 * @author Link
 */
public class SpriteCache extends ResourceCache {

    protected Object loadResource(URL url){
        try {
          return ImageIO.read(url);
        } catch (Exception e) {
          System.out.println("No se pudo cargar la imagen "+url);
          System.out.println(e.getClass().getName()+"  "+e.getMessage());
          System.exit(0);
          return null;
        }
      }

   
    public BufferedImage getSprite(String sprite){
        return (BufferedImage)getResource(sprite);
        
    }


}
