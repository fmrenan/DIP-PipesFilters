package filtros.rgb;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
Autor: Renan
 */
public class Main {
    public static void main(String args[]) {
        
        Scanner scan = new Scanner(System.in);
        String objeto = scan.next();
        ImagemComponente img;
        
        if(objeto.contains("erro")){
            System.err.println("Não foi possível abrir a imagem");
            System.out.println("erro");
        }else{            
            try {
                img = carregarImagem(objeto);
                
                switch (args.length) {
                    case 0:
                        System.out.println("Erro: digite algum parâmetro válido /ajuda para mais!");
                        break;
                    case 1:
                        if(args[0].equalsIgnoreCase("/ajuda")){
                            ajuda();
                            break;
                        }
                        if(args[0].equalsIgnoreCase("vermelho")){
                            img = new VermelhoDecorator(img);
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                            break;
                        }else if(args[0].equalsIgnoreCase("verde")){
                            img = new VerdeDecorator(img);
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                            break;
                        }else if(args[0].equalsIgnoreCase("azul")){
                            img = new AzulDecorator(img);
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                            break;
                        } 
                        break;
                    case 2:
                        if(args[0].equalsIgnoreCase("-v") && args[1].equalsIgnoreCase("vermelho")){
                            img = new VermelhoDecorator(img);
                            img.visualizar();
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                            break;
                        }else if(args[0].equalsIgnoreCase("-v") && args[1].equalsIgnoreCase("verde")){
                            img = new VerdeDecorator(img);
                            img.visualizar();
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                            break;
                        }else if(args[0].equalsIgnoreCase("-v") && args[1].equalsIgnoreCase("azul")){
                            img = new AzulDecorator(img);
                            img.visualizar();
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                            break;
                        } 
                        break;
                    default:
                        System.out.println("Erro: digite algum parâmetro válido!");
                        break;
                }
            
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        }
    }
    
    private static void ajuda(){
        System.out.println("O filtro RGB é destinado a realizar a coloração de uma imagem em três cores base: Vermelho, verde e azul \n");
        System.out.println("Parâmetros:");
        System.out.println("     -v utilizado quando deseja visualizar uma imagem que foi processada");
        System.out.println("     vermelho utilizado para a coloração da imagem em vermelho");
        System.out.println("     verde utilizado para a coloração da imagem em verde");
        System.out.println("     azul utilizado para a coloração da imagem em azul");
        System.out.println("     Exemplo: rgb -v azul");
        System.out.println("     Exemplo2: rgb azul");
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
