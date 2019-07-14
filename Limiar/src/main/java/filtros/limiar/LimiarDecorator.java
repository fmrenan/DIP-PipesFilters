package filtros.limiar;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
Autor: Renan
 */
public class LimiarDecorator extends ImagemDecorator{
    
    private BufferedImage img;
    private final int limiar;

    public LimiarDecorator(ImagemComponente elementoDecorado, int limiar) throws InterruptedException {
        super(elementoDecorado);
        this.limiar = limiar;
    }
    
    @Override
    public BufferedImage getImagem() throws IOException {
        if (img == null) {
            return threshold();
        }
        return img;    
    }
    
    private BufferedImage threshold() throws IOException {
        imagem = elementoDecorado.getImagem();
        
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {               
                int rgb = imagem.getRGB(i, j);
                int r = (int)((rgb&0x00FF0000)>>>16);
                int g = (int)((rgb&0x0000FF00)>>>8);
                int b = (int) (rgb&0x000000FF);
                int media = (r + g + b) / 3;
                Color white = new Color(255, 255, 255);
                Color black = new Color(0, 0, 0);
                
                if (media < limiar)
                    imagem.setRGB(i, j, black.getRGB());
                else
                    imagem.setRGB(i, j, white.getRGB());
            }
        }
        return imagem;
    }
}