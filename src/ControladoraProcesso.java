
import java.util.*;
import java.io.*;
import java.util.concurrent.Semaphore;

public class ControladoraProcesso {

    private ArrayList<Processo> processos = new ArrayList<Processo>();
    public Tanque tanque;
    
    

    public ControladoraProcesso(int qtde) throws Exception{
        tanque = new Tanque();
        int cont = 0;
        
           
        while (cont != qtde) {
        Thread.sleep(1500);
            if(cont%2==0){
              Frentista proc = new Frentista();
              processos.add(proc);
              System.out.println("um frentista foi criado");
            }
            else{
             Motor proc = new Motor();
              processos.add(proc);   
              System.out.println("um motor foi criado");
            }
              cont++;
        }
    }

    public String sincronizarProcesso(int i, int j) throws Exception{
               
                int cont = 0;
                Processo objBase =  (Processo) processos.get(i);
                Processo objComp =  (Processo) processos.get(j);
                
                if(objBase.equals(objComp)) return "...";
                
                if(objBase.sincronizados.contains(objComp)) return "...";
               Thread.sleep(2000);
               System.out.println("------------------\nO processo "+ objBase+ " quer se comunicar com "+ objComp+"\n");
               Thread.sleep(2000);
               

               while (objBase.estado.equals(objComp.estado)) {
                
               System.out.println("O processo "+ objBase+" tem como estado "+ objBase.getEstado()+ " "+ objBase.getBloqueado());
               System.out.println("O processo "+ objComp+" tem como estado "+ objComp.getEstado()+ " "+ objComp.getBloqueado()+"\n");
               Thread.sleep(2000);
              
               if(cont < 2){
                objComp.definirEstado();
                System.out.println( "os processos " + objBase + " e " + objComp + " nao puderam ser sincronizados. Tentando novamente... (tentativa " + (cont+1) + ")");
                Thread.sleep(3000);
                }
                
                else {
                    
                System.out.println( "os processos " + objBase + " e " + objComp + " nao puderam ser sincronizados. Ativando deteccao de erro... (tentativa " + (cont+1) + ")");
                System.out.println( "Comunicacao atrasada detectada. Operacao de emergencia ativada...");
                objComp.definirEstado();
                objBase.definirEstado();
                Thread.sleep(3000);
                
                }
                cont++;
                }        
               
               System.out.println("O processo "+ objBase+" tem como estado "+ objBase.getEstado()+ " "+ objBase.getBloqueado());
               System.out.println("O processo "+ objComp+" tem como estado "+ objComp.getEstado()+ " "+ objComp.getBloqueado()+"\n");
               Thread.sleep(2000);
               objBase.sincronizarNovoProcesso(objComp);
                objComp.sincronizarNovoProcesso(objBase); 
                return "os processos " + objBase + " e " + objComp + " foram sincronizados com sucesso\n";     
    
    }

    public void gerarEfeito() throws Exception{
        int j = processos.size();
        while(j > 0){
            Thread.sleep(2000);
            if(tanque.getN() <= 0) {
                System.out.println("--------\nAnalisando estado do tanque...");
                Thread.sleep(2000);
                System.out.println("O tanque esta vazio");
                Thread.sleep(1000);
                System.out.println("...");
                Thread.sleep(1000);
                for(int i = 0; i<processos.size(); i++){
                    if(processos.get(i) instanceof Frentista){                                             
                        processos.get(i).gerarAcao(tanque);
                        System.out.println("O frentista " + processos.get(i)+ " colocou gasolina no tanque\n");
                        System.out.println("...");
                Thread.sleep(1000);
                        processos.remove(processos.get(i));                                             
                    }
                    else{
                        System.out.println("o Motor nao pode agir, pois nao ha gasolina. Este sera postergado\n");
                        processos.add(processos.size(), processos.get(i));
                    }
                }
            }
        
            else if(tanque.getN() > 0){
                System.out.println("--------\nAnalisando estado do tanque...");
                Thread.sleep(2000);
                System.out.println("Ha combustivel no tanque");
                Thread.sleep(1000);
                System.out.println("...");
                Thread.sleep(1000);
                for(int i = 0; i< processos.size(); i++){
                    if(processos.get(i) instanceof Motor){
                        processos.get(i).gerarAcao(tanque);
                    System.out.println("O Motor " + processos.get(i)+ " retirou gasolina do tanque\n");
                    System.out.println("...");
                Thread.sleep(1000);
                        processos.remove(processos.get(i));
                    }
                }
                
            }
         j--;       
        }
        
    }
}