package filtros.salvar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class SalvarImagemDecorator extends ImagemDecorator {

    private String nome;

    public SalvarImagemDecorator(ImagemComponente elementoDecorado, String nome) throws InterruptedException, IOException {
        super(elementoDecorado);
        this.nome = nome;
        salvarImagem();
    }

    @Override
    public BufferedImage getImagem() throws IOException {
        return salvarImagem();
    }

    private BufferedImage salvarImagem() throws IOException {

        imagem = elementoDecorado.getImagem();
        
        BufferedImage novaimg = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
        novaimg.getGraphics().drawImage(imagem, 0, 0, null);
        
        File arquivo = new File(nome);
              
        ImageIO.write(novaimg, "jpg", arquivo);
        
        return novaimg;

    }

    @Override
    public ImagemComponente reverter() {
        throw new IllegalArgumentException("A imagem foi salva, nao e possivel reverter");
    }

}
