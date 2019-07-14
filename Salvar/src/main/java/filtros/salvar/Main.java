package filtros.salvar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
Autor: Renan
 */
public class Main {
    
    public static void main(String args[]){
        
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
                        System.out.println("Erro: digite algum nome válido!");
                        break;
                    case 1:
                        if(args[0].equalsIgnoreCase("/ajuda")){
                            ajuda();
                            break;
                        }   if(args[0].endsWith(".jpg")){
                            img = new SalvarImagemDecorator(img, args[0]);
                            break;
                        }else{
                            System.out.println("Erro: insira uma imagem .jpg");
                        }   
                        break;
                    case 2:
                        if(args[0].equalsIgnoreCase("-v") && args[1].endsWith(".jpg")){
                            img = new SalvarImagemDecorator(img, args[1]);
                            img.visualizar();
                        }   
                        break;
                    default:
                        System.out.println("Erro: digite algum nome válido!");
                        break;
                }
            } catch (IOException | InterruptedException ex) {
                System.err.println("Erro: "+ex);
            }           
        }      
    }  
    
    private static void ajuda(){
        System.out.println("O filtro salvar é destinado para salvar uma imagem já processada \n");
        System.out.println("Parâmetros:");
        System.out.println("     -v utilizado quando deseja visualizar uma imagem que foi carregada");
        System.out.println("     nomedaimagem.jpg o nome da imagem que deseja salvar");
        System.out.println("     Exemplo: salvar -v imagem.jpg");
        System.out.println("     Exemplo2: salvar imagem.jpg");
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
}