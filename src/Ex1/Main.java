package Ex1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        int qnt = 6;
        for (int i=1; i<qnt; i++){ //for para percorrer todas as imagens da pasta de forma automatica

            String img = "src/Ex1/Limpas/img"+i+".jpg";
            BufferedImage image = ImageIO.read(new File(img)); //carrego a imagem

            int width = image.getWidth(); //coleto altura e largura
            int height = image.getHeight();

            BufferedImage novaImagem = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //instancio uma imagem vazia

            int nivelRuido = 5; //defino o nível do ruido
            Random random = new Random(); //varivel para gerar numero aleatorio

            for (int linha=0; linha < width; linha++){
                for (int coluna=0; coluna<height; coluna++){

                    int num = random.nextInt(0,100); //pega um numero de 0 a 100

                    if (num<nivelRuido){ //se o numero for menor do que o nivel de Ruido

                        int num2 = random.nextInt(0,2); //pega um numero entre 0 e 2

                        if (num2 == 1){

                            Color cor = new Color(255,255,255); 
                            image.setRGB(linha,coluna,cor.getRGB()); //"pinta o pixel com a cor escolhida (branco)"

                        }else{

                            Color cor = new Color(0,0,0);
                            image.setRGB(linha,coluna,cor.getRGB()); //"pinta o pixel com a cor escolhida (preto)"

                        }

                    }


                    novaImagem.setRGB(linha,coluna, new Color(image.getRGB(linha,coluna)).getRGB()); //salva a imagem na variavel com as cores do pixel da imagem alterada

                }
            }

            ImageIO.write(novaImagem,"jpg", new File("src/Ex1/Sujas/NOVAimagem"+i+".jpg")); //salva a imagem
        }
    }
}