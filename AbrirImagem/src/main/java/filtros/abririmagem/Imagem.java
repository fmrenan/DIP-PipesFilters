package filtros.abririmagem;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Imagem extends ImagemComponente {

    public Imagem(BufferedImage img) throws IOException, InterruptedException {
        imagem = img;
        Thread.sleep(1000);
    }

    @Override
    public BufferedImage getImagem() {
        return imagem;
    }

    @Override
    public ImagemComponente reverter() {
        return this;
    }

}
