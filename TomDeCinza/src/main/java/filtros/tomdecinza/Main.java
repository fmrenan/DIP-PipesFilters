package filtros.tomdecinza;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
Autor: Renan
 */
public class Main {
    public static void main(String args[]) {
        
        Scanner scan = new Scanner(System.in);
        String objeto = scan.next();
        ImagemComponente img;
        String imgCodificada;
        
        if(objeto.contains("erro")){
            System.err.println("Não foi possível abrir a imagem");
            System.out.println("erro");
        }else{            
            try {
                img = carregarImagem(objeto);
                
                switch (args.length) {
                    case 0:
                        img = new TomDeCinzaDecorator(img);
                        imgCodificada = codificarImagem(img.getImagem());
                        saidaImagem(imgCodificada);
                        break;
                    case 1:
                        if(args[0].equalsIgnoreCase("/ajuda")){
                            ajuda();
                            break;
                        }else if(args[0].equalsIgnoreCase("-v")){
                            img = new TomDeCinzaDecorator(img);
                            img.visualizar();
                            imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                        }else{
                            System.out.println("Erro: digite algum parâmetro válido!");
                        }
                        break;
                    default:
                        System.out.println("Erro: digite algum parâmetro válido!");
                        break;
                }
                
            
            } catch (IOException | InterruptedException ex) {
                System.err.println("Erro: "+ex);
                System.out.println("erro");
            }        
        }
    }
    
    private static void ajuda(){
        System.out.println("O filtro transforma uma imagem colorida em tons de cinza \n");
        System.out.println("Parâmetros:");
        System.out.println("     -v utilizado quando deseja visualizar uma imagem que foi processada");
        System.out.println("     Exemplo: tomdecinza -v");
        System.out.println("     Exemplo2: tomdecinza");
    }
    
    private static void saidaImagem(String imagem){
        System.out.println(imagem);
    }
    
    private static ImagemComponente carregarImagem(String objeto) throws IOException, InterruptedException{
        byte[] decodedBytes = Base64.getDecoder().decode(objeto);
        BufferedImage imagem;
        
        try (ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes)) {
            imagem = ImageIO.read(bis);
        }
        
        ImagemComponente img = new Imagem(imagem);
        
        return img;
    }
    
    private static String codificarImagem(BufferedImage imagem) {
        String imagemString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(imagem, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();

            Base64.Encoder encoder = Base64.getEncoder();
            imagemString = encoder.encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
        }

        return imagemString;
    }
}