package software;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ColorExtractor {
   private String FileName;
   private ImageInputStream is;
   private Iterator iter;
   private ImageReader imageReader;
   private BufferedImage image;
   private int height;
   private int width;
   private Map m;
   //private Color color;
   private String ColorHex;
   public ColorExtractor() throws IOException {
      ColorHex = " ";
   }
   public void inputfile() throws IOException {
      File file = new File(FileName);
        this.is = ImageIO.createImageInputStream(file);
        this.iter = ImageIO.getImageReaders(is);

        if (!iter.hasNext())
        {
            System.out.println("Cannot load the specified file "+ file);
            System.exit(1);
        }
        this.imageReader = (ImageReader)iter.next();
        imageReader.setInput(is);

        this.image = imageReader.read(0);

        this.height = image.getHeight();
        this.width = image.getWidth();

        this.m = new HashMap();
   }
   
   public Color Extract(String FileName) throws IOException {
      this.FileName = FileName;
      inputfile();
      for(int i=0; i < width ; i++)
        {
            for(int j=0; j < height ; j++)
            {
                int rgb = image.getRGB(i, j);
                int[] rgbArr = getRGBArr(rgb);                
                // Filter out grays....                
                if (!isGray(rgbArr)) {                
                        Integer counter = (Integer) m.get(rgb);   
                        if (counter == null)
                            counter = 0;
                        counter++;                                
                        m.put(rgb, counter);                
                }                
            }
        }        
      return getMostCommonColour(m);
   }
   
   public Color getMostCommonColour(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
         public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
              }
        });    
        Map.Entry me = (Map.Entry)list.get(list.size()-1);
        int[] rgb= getRGBArr((Integer)me.getKey());
        //color = new Color(rgb[0],rgb[1],rgb[2]);
        //System.out.println(rgb[0]+" "+rgb[1]+" "+rgb[2]); //code for debug
        //System.out.println(color.getRGB()); //code for debug
        ColorHex = Integer.toHexString(rgb[0])+" "+Integer.toHexString(rgb[1])+" "+Integer.toHexString(rgb[2]);        
        return new Color(rgb[0],rgb[1],rgb[2]);
        // return color;
    }    

   
   public int[] getRGBArr(int pixel) {
      int alpha = (pixel >> 24) & 0xff;
       int red = (pixel >> 16) & 0xff;
       int green = (pixel >> 8) & 0xff;
       int blue = (pixel) & 0xff;
       return new int[]{red,green,blue};
   }
    public boolean isGray(int[] rgbArr) {
       int rgDiff = rgbArr[0] - rgbArr[1];
       int rbDiff = rgbArr[0] - rgbArr[2];
       // Filter out black, white and grays...... (tolerance within 10 pixels)
       int tolerance = 10;
          if (rgDiff > tolerance || rgDiff < -tolerance) 
             if (rbDiff > tolerance || rbDiff < -tolerance) { 
                   return false;
               }                 
           return true;
   }
    public String getColorHex() {
       return ColorHex;
    }
   /* public static void main(String args[]) throws IOException {
       String FileName = "testred1.png";
       ColorExtractor CE = new ColorExtractor();
       Color color = CE.Extract("image/"+FileName);
       System.out.println(color.getRGB()); //-> int 형태의 color 값
       System.out.println(color.getRed()+" "+color.getGreen()+" "+color.getBlue());
       System.out.println(CE.getColorHex());
    }
*/
   
}