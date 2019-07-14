package filtros.borda;

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
        
        if(objeto.contains("erro")){
            System.err.println("Não foi possível abrir a imagem");
            System.out.println("erro");
        }else{            
            try {
                img = carregarImagem(objeto);
                
                switch (args.length) {
                    case 0:
                        System.out.println("Erro: digite algum parâmetro válido. /ajuda para mais!");
                        break;
                    case 1:
                        if(args[0].equalsIgnoreCase("/ajuda")){
                            ajuda();
                            break;
                        }
                        else{
                            if(verificaEntrada(args[0])){
                                int borda = Integer.parseInt(args[0]);
                                img = new BordaDecorator(img, borda);
                                String imgCodificada = codificarImagem(img.getImagem());
                                saidaImagem(imgCodificada);
                            }else{
                                System.out.println("Erro: digite algum parâmetro válido. /ajuda para mais!");
                            }                            
                        }
                        break;
                    case 2:
                        if(args[0].equalsIgnoreCase("-v") && verificaEntrada(args[1])){
                            int borda = Integer.parseInt(args[1]);
                            img = new BordaDecorator(img, borda);
                            img.visualizar();
                            String imgCodificada = codificarImagem(img.getImagem());
                            saidaImagem(imgCodificada);
                        }
                        else{
                            System.out.println("Erro: digite algum parâmetro válido. /ajuda para mais!");
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
    
    private static boolean verificaEntrada(String entrada){
        try{
            Integer.parseInt(entrada);
            return true;
	}catch (NumberFormatException nfex) {
            return false;
	}
    }
    
    private static void ajuda(){
        System.out.println("O filtro adiciona uma borda preta ao redor da imagem \n");
        System.out.println("Parâmetros:");
        System.out.println("     -v utilizado quando deseja visualizar uma imagem que foi processada");
        System.out.println("     1 a n valor da espessura da borda");
        System.out.println("     Exemplo: borda -v 4");
        System.out.println("     Exemplo2: borda 3");
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