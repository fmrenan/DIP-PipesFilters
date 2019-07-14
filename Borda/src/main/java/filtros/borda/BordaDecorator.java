package filtros.borda;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;

/**
Autor: Renan
 */
public class BordaDecorator extends ImagemDecorator{
    
    private BufferedImage img;
    private final Color cor = new Color(0,0,0);
    private final int tamanho;

    public BordaDecorator(ImagemComponente elementoDecorado, int tamanho) throws InterruptedException {
        super(elementoDecorado);
        this.tamanho = tamanho;
    }
    
    @Override
    public BufferedImage getImagem() throws IOException {
        if (img == null) {
            return aplicarBorda();
        }
        return img;
    }
    
    private BufferedImage aplicarBorda() throws IOException{
        imagem = elementoDecorado.getImagem();
        
        Graphics2D graphics2D = imagem.createGraphics();
        graphics2D.setPaint(cor);
        //Horizontal
        graphics2D.fillRect(0, 0, imagem.getWidth(), tamanho);
        graphics2D.fillRect(0, imagem.getHeight() - tamanho, imagem.getWidth(), tamanho);
        //Vertical
        graphics2D.fillRect(0, 0, tamanho, imagem.getHeight());
        graphics2D.fillRect(imagem.getWidth() - tamanho, 0, tamanho, imagem.getHeight());
        
        return imagem;
    }
}

    

    

