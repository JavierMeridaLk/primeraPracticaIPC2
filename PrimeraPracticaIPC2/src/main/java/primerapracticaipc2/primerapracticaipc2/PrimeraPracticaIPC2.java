/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package primerapracticaipc2.primerapracticaipc2;

import Backend.Gestor;
import Fronted.FramePrincipal;

/**
 *
 * @author xavi
 */
public class PrimeraPracticaIPC2 {

    public static void main(String[] args) {
        
        Gestor gestor = new Gestor();
        FramePrincipal frame = new FramePrincipal(gestor);
        frame.setVisible(true);
    }
}
