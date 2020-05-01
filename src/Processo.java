import java.util.*;
import java.util.concurrent.Semaphore;

public class Processo {


public String estado;
public ArrayList<Processo> sincronizados;
public Semaphore mutex;
public String bloqueado;//com ele, o envio e recebimento se tornam bloqueantes

public Processo(){
this.mutex = new Semaphore(1);
this.bloqueado = "nao-bloqueante";
sincronizados = new ArrayList<Processo>();
this.definirEstado();
}

public void envio(boolean estado){
    this.estado = "envio";
    if(estado == true){
    this.bloqueado = "bloqueante";//bloqueio
    }
}

public void recebimento(boolean estado){
    this.estado = "recebimento";
    if(estado == true){
    this.bloqueado = "bloqueante";//bloqueio
    }
}

public void definirEstado(){
    Random r = new Random();//define envio ou recebimento
    Random e = new Random();//define se eh bloqueante ou nao
    boolean result = e.nextBoolean();
    boolean result2 = r.nextBoolean();
    if(result == true) this.envio(result2);
    else this.recebimento(result2);
}

public void sincronizarNovoProcesso (Processo proc){
    {
        sincronizados.add(proc);
        this.bloqueado = "nao-bloqueante";
    }
      
}

public void gerarAcao(Tanque tanque){
    
}

    public String getEstado() {
        return estado;
    }

    public String getBloqueado() {
        return bloqueado;
    }
    



}
