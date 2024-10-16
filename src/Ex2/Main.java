package Ex2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int qnt = 6;
        for (int i=1; i<qnt; i++){

            String img = "src/Ex2/Sujas/img"+i+".jpg";

            BufferedImage image = ImageIO.read(new File(img));

            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage novaImagem3x3 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            BufferedImage novaImagem5x5 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            BufferedImage novaImagem7x7 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            BufferedImage novaImagem9x9 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


            double[][] filtro3x3 = {
                    {0.0625, 0.125, 0.0625},
                    {0.125, 0.15, 0.125},
                    {0.0625, 0.125, 0.0625}
            };

            double[][] filtro5x5 = {
                    {0.0039, 0.0156, 0.0234, 0.0156, 0.0039},
                    {0.0156, 0.0625, 0.0938, 0.0625, 0.0156},
                    {0.0234, 0.0938, 0.1406, 0.0938, 0.0234},
                    {0.0156, 0.0625, 0.0938, 0.0625, 0.0156},
                    {0.0039, 0.0156, 0.0234, 0.0156, 0.0039}
            };

            double[][] filtro7x7 = {
                    {0.0000, 0.0000, 0.0010, 0.0020, 0.0010, 0.0000, 0.0000},
                    {0.0000, 0.0030, 0.0129, 0.0219, 0.0129, 0.0030, 0.0000},
                    {0.0010, 0.0129, 0.0588, 0.0967, 0.0588, 0.0129, 0.0010},
                    {0.0020, 0.0219, 0.0967, 0.1585, 0.0967, 0.0219, 0.0020},
                    {0.0010, 0.0129, 0.0588, 0.0967, 0.0588, 0.0129, 0.0010},
                    {0.0000, 0.0030, 0.0129, 0.0219, 0.0129, 0.0030, 0.0000},
                    {0.0000, 0.0000, 0.0010, 0.0020, 0.0010, 0.0000, 0.0000}
            };

            double[][] filtro9x9 = {
                    {0.0071, 0.0071, 0.0143, 0.0143, 0.0214, 0.0143, 0.0143, 0.0071, 0.0071},
                    {0.0071, 0.0143, 0.0214, 0.0357, 0.0429, 0.0357, 0.0214, 0.0143, 0.0071},
                    {0.0143, 0.0214, 0.0429, 0.0500, 0.0714, 0.0500, 0.0429, 0.0214, 0.0143},
                    {0.0143, 0.0357, 0.0500, 0.0786, 0.0929, 0.0786, 0.0500, 0.0357, 0.0143},
                    {0.0214, 0.0429, 0.0714, 0.0929, 0.1143, 0.0929, 0.0714, 0.0429, 0.0214},
                    {0.0143, 0.0357, 0.0500, 0.0786, 0.0929, 0.0786, 0.0500, 0.0357, 0.0143},
                    {0.0143, 0.0214, 0.0429, 0.0500, 0.0714, 0.0500, 0.0429, 0.0214, 0.0143},
                    {0.0071, 0.0143, 0.0214, 0.0357, 0.0429, 0.0357, 0.0214, 0.0143, 0.0071},
                    {0.0071, 0.0071, 0.0143, 0.0143, 0.0214, 0.0143, 0.0143, 0.0071, 0.0071}
            };



            int tamanhoKernel = filtro3x3.length;
            int desvioVizinhança = tamanhoKernel / 2;

            int tamanhoKernel5x5 = (int) Math.sqrt(filtro5x5.length);
            int desvioVizinhança5x5 = tamanhoKernel / 2;

            int tamanhoKernel7x7 = (int) Math.sqrt(filtro7x7.length);
            int desvioVizinhança7x7 = tamanhoKernel / 2;

            int tamanhoKernel9x9 = (int) Math.sqrt(filtro9x9.length);
            int desvioVizinhança9x9 = tamanhoKernel / 2;



            for (int linha=desvioVizinhança; linha<width-desvioVizinhança; linha++){
                for (int coluna=desvioVizinhança; coluna<height-desvioVizinhança; coluna++){

                    int valorR = 0;
                    int valorG = 0;             //Filtro 3x3
                    int valorB = 0;

                    for(int filtroLinha = 0; filtroLinha<tamanhoKernel; filtroLinha++){
                        for (int filtroColuna = 0; filtroColuna<tamanhoKernel; filtroColuna++){

                         Color pixel = new Color(image.getRGB(linha+filtroLinha-desvioVizinhança, coluna+filtroColuna-desvioVizinhança));

                         valorR += filtro3x3[filtroLinha][filtroColuna] * pixel.getRed();
                         valorG += filtro3x3[filtroLinha][filtroColuna] * pixel.getGreen();
                         valorB += filtro3x3[filtroLinha][filtroColuna] * pixel.getBlue();


                        }
                    }

                    if (valorR > 255) {
                        valorR = 255;
                    }
                    if (valorG > 255) {
                        valorG = 255;
                    }
                    if (valorB > 255) {
                        valorB = 255;
                    }

                    if (valorR < 0) {
                        valorR = 0;
                    }
                    if (valorG < 0) {
                        valorG = 0;
                    }
                    if (valorB < 0) {
                        valorB = 0;
                    }

                    Color novaCor = new Color(valorR, valorG, valorB);

                    novaImagem3x3.setRGB(linha, coluna, novaCor.getRGB());

                }
            }

            for (int linha=desvioVizinhança5x5; linha<width-desvioVizinhança5x5; linha++){
                for (int coluna=desvioVizinhança5x5; coluna<height-desvioVizinhança5x5; coluna++){

                    int valorR = 0;
                    int valorG = 0;         //Filtro5x5
                    int valorB = 0;

                    for(int filtroLinha = 0; filtroLinha<tamanhoKernel5x5; filtroLinha++){
                        for (int filtroColuna = 0; filtroColuna<tamanhoKernel5x5; filtroColuna++){

                            Color pixel = new Color(image.getRGB(linha+filtroLinha-desvioVizinhança5x5, coluna+filtroColuna-desvioVizinhança5x5));

                            valorR += filtro5x5[filtroLinha][filtroColuna] * pixel.getRed();
                            valorG += filtro5x5[filtroLinha][filtroColuna] * pixel.getGreen();
                            valorB += filtro5x5[filtroLinha][filtroColuna] * pixel.getBlue();


                        }
                    }

                    if (valorR > 255) {
                        valorR = 255;
                    }
                    if (valorG > 255) {
                        valorG = 255;
                    }
                    if (valorB > 255) {
                        valorB = 255;
                    }

                    if (valorR < 0) {
                        valorR = 0;
                    }
                    if (valorG < 0) {
                        valorG = 0;
                    }
                    if (valorB < 0) {
                        valorB = 0;
                    }

                    Color novaCor = new Color(valorR, valorG, valorB);

                    novaImagem5x5.setRGB(linha, coluna, novaCor.getRGB());

                }
            }

            for (int linha=desvioVizinhança7x7; linha<width-desvioVizinhança7x7; linha++){
                for (int coluna=desvioVizinhança7x7; coluna<height-desvioVizinhança7x7; coluna++){

                    int valorR = 0;
                    int valorG = 0;     //Filtro 7x7
                    int valorB = 0;

                    for(int filtroLinha = 0; filtroLinha<tamanhoKernel7x7; filtroLinha++){
                        for (int filtroColuna = 0; filtroColuna<tamanhoKernel7x7; filtroColuna++){

                            Color pixel = new Color(image.getRGB(linha+filtroLinha-desvioVizinhança7x7, coluna+filtroColuna-desvioVizinhança7x7));

                            valorR += filtro7x7[filtroLinha][filtroColuna] * pixel.getRed();
                            valorG += filtro7x7[filtroLinha][filtroColuna] * pixel.getGreen();
                            valorB += filtro7x7[filtroLinha][filtroColuna] * pixel.getBlue();


                        }
                    }

                    if (valorR > 255) {
                        valorR = 255;
                    }
                    if (valorG > 255) {
                        valorG = 255;
                    }
                    if (valorB > 255) {
                        valorB = 255;
                    }

                    if (valorR < 0) {
                        valorR = 0;
                    }
                    if (valorG < 0) {
                        valorG = 0;
                    }
                    if (valorB < 0) {
                        valorB = 0;
                    }

                    Color novaCor = new Color(valorR, valorG, valorB);

                    novaImagem7x7.setRGB(linha, coluna, novaCor.getRGB());

                }
            }

            for (int linha=desvioVizinhança9x9; linha<width-desvioVizinhança9x9; linha++){
                for (int coluna=desvioVizinhança9x9; coluna<height-desvioVizinhança9x9; coluna++){

                    int valorR = 0;
                    int valorG = 0;     //Filtro9x9
                    int valorB = 0;

                    for(int filtroLinha = 0; filtroLinha<tamanhoKernel9x9; filtroLinha++){
                        for (int filtroColuna = 0; filtroColuna<tamanhoKernel9x9; filtroColuna++){

                            Color pixel = new Color(image.getRGB(linha+filtroLinha-desvioVizinhança9x9, coluna+filtroColuna-desvioVizinhança9x9));

                            valorR += filtro9x9[filtroLinha][filtroColuna] * pixel.getRed();
                            valorG += filtro9x9[filtroLinha][filtroColuna] * pixel.getGreen();
                            valorB += filtro9x9[filtroLinha][filtroColuna] * pixel.getBlue();


                        }
                    }

                    if (valorR > 255) {
                        valorR = 255;
                    }
                    if (valorG > 255) {
                        valorG = 255;
                    }
                    if (valorB > 255) {
                        valorB = 255;
                    }

                    if (valorR < 0) {
                        valorR = 0;
                    }
                    if (valorG < 0) {
                        valorG = 0;
                    }
                    if (valorB < 0) {
                        valorB = 0;
                    }

                    Color novaCor = new Color(valorR, valorG, valorB);

                    novaImagem9x9.setRGB(linha, coluna, novaCor.getRGB());

                }
            }
            ImageIO.write(novaImagem3x3,"png",new File("src/Ex2/Limpas/NOVAimagem3x3--"+i+".png"));
            ImageIO.write(novaImagem5x5,"png",new File("src/Ex2/Limpas/NOVAimagem5x5--"+i+".png"));
            ImageIO.write(novaImagem7x7,"png",new File("src/Ex2/Limpas/NOVAimagem7x7--"+i+".png"));
            ImageIO.write(novaImagem9x9,"png",new File("src/Ex2/Limpas/NOVAimagem9x9--"+i+".png"));

        }
    }
}
