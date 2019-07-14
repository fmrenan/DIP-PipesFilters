package filtros.abririmagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

/**
Autor: Renan
 */
public class Main {
    
    public static void main(String args[]){
       
        switch (args.length) {
            case 0:
                System.out.println("Erro: digite algum nome válido!");
                break;
            case 1:
                if(args[0].equalsIgnoreCase("/ajuda")){
                    ajuda();
                    break;
                }   if(args[0].endsWith(".jpg")){
                    carregarImagem(args[0]);
                    break;
                }else{
                    System.out.println("Erro: insira uma imagem .jpg");
                }   
                break;
            case 2:
                if(args[0].equalsIgnoreCase("-v") && args[1].endsWith(".jpg")){
                    carregarImagem(args[1]);
                    visualizar(args[1]);                    
                }   
                break;
            default:
                System.out.println("Erro: digite algum nome válido!");
                break;
        }      
    }  
    
    private static void ajuda(){
        System.out.println("O filtro abrirImagem é destinado a realizar o carregamento e visualização de uma imagem \n");
        System.out.println("Parâmetros:");
        System.out.println("     -v utilizado quando deseja visualizar uma imagem que foi carregada");
        System.out.println("     nomedaimagem.jpg o nome da imagem que deseja abrir");
        System.out.println("     Exemplo: abrirImagem -v imagem.jpg");
        System.out.println("     Exemplo2: abrirImagem imagem.jpg");
    }
    
    private static void visualizar(String caminho){
        try {
            BufferedImage imagem = ImageIO.read(new File(caminho));
            ImagemComponente img = new Imagem(imagem);
            img.visualizar();
        } catch (IOException | InterruptedException ex) {
             System.out.println("Erro: "+ex);
        }        
    }
    
    private static void carregarImagem(String imagem){
        boolean erro = false;
        
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(imagem));
        } catch (IOException ex) {
            erro = true;
            System.err.print("Erro: arquivo não existe!");
            System.out.println("erro");            
        }
        if(!erro){
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            System.out.print(encodedString);
        }
    }
}