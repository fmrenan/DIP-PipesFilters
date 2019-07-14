package filtros.nitidez;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;

/**
Autor: Renan
 */
public class NitidezDecorator extends ImagemDecorator{
    
    private BufferedImage img;

    public NitidezDecorator(ImagemComponente elementoDecorado) throws InterruptedException {
        super(elementoDecorado);
    }
    
    @Override
    public BufferedImage getImagem() throws IOException {
        if (img == null) {
            return efeitoNitidez();
        }
        return img;
    }
    
    float[] sharpKernel = {
     0.0f, -1.0f, 0.0f,
     -1.0f, 5.0f, -1.0f,
     0.0f, -1.0f, 0.0f
    };
    
    private BufferedImage efeitoNitidez() throws IOException {
        imagem = elementoDecorado.getImagem();
        
        BufferedImageOp sharpen = new ConvolveOp(
        new Kernel(3, 3, sharpKernel),
        ConvolveOp.EDGE_NO_OP, null);
        
        imagem = sharpen.filter(imagem, null);
        
        return imagem;
    }
}