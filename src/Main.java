/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
import java.util.*;

public class Main {
    
    private static Scanner scanner = new Scanner (System.in);
    private ControladoraProcesso cp;
    
    public static void main(String[] arg) throws Exception {
       System.out.println("Digite a quantidade de processos desejada");
       String input = scanner.nextLine();
       int valor = Integer.parseInt(input); 
       System.out.println("Serao criados " + input + " processos");
       Thread.sleep(1000);
       System.out.println("...");
       Thread.sleep(1000);
       System.out.println("...");
       Thread.sleep(1000);
       System.out.println("...");
       Thread.sleep(2000);
       ControladoraProcesso cp = new ControladoraProcesso(valor);
       System.out.println("\naperte enter para dar inicio a sincronizacao dos processos");
       input = scanner.nextLine();
       System.out.println("\nSincronizando processos...\n");
       for(int i=0; i<valor; i++){
           Thread.sleep(1000);
            for(int j=0; j< valor; j++){
                Thread.sleep(1000);
             String resultado = cp.sincronizarProcesso(i, j);
             System.out.println(resultado);
             
       }
        
       }
       Thread.sleep(2000);
       for(int j= 0; j<50; j++) System.out.println();
       System.out.println("Os processos comecarao a agir...\n");
       Thread.sleep(1000);
       cp.gerarEfeito();
    }
}
