package br.com.deltainf.removequebra;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Douglas Rauber <douglas.rauber@gmail.com>
 */
public class RemoveQuebra {

    public static void main(String[] args) {
        // delimited file
        File file = new File("/home/douglas/tmp/usuario_cadsus_202005191020_1177.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            // Check if there is another line of input
            int pipes = 0;
            String linhaCompleta = "";
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                Scanner scannerLinha = new Scanner(str);
                scannerLinha.useDelimiter("[|]");
                // Check if there is another line of input
                while (scannerLinha.hasNext()) {
                    pipes++;
                    linhaCompleta += scannerLinha.next()+"|";
                }
                scannerLinha.close();
                
                if(pipes >= 93) {
                    linhaCompleta = linhaCompleta.replace("\n", " ");
                    linhaCompleta = linhaCompleta.substring(0, linhaCompleta.length()-1);
                    linhaCompleta += "\n";
                    writeStringToFile(linhaCompleta);
                    pipes = 0;
                    linhaCompleta = "";
                }
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        scanner.close();
    }
    
    public static void writeStringToFile(String texto) {
        try {
            FileUtils.writeStringToFile(new File("/home/douglas/tmp/usuario_cadsus_202005191020_1177_tratado.csv"), texto, "UTF-8", true);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
