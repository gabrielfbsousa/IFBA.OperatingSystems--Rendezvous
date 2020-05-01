/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gabriel
 */
public class Motor extends Processo{

    public void gerarAcao(Tanque tanque){
        try {
            this.mutex.acquire();
            tanque.setN(0);
        this.mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
