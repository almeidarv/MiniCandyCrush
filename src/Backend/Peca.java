package Backend;

import cores.StringColorida;
import mecanicas.Carta;


public class Peca extends Carta {
    public Peca() {
        super(new StringColorida("0", getCor(0), getCorFundo(0)));
    }
public Peca(StringColorida frente) {
        super(frente);
    }

    public Peca(int z) {
        super(new StringColorida(String.valueOf(z), getCor(z)));
    }

    public int getValor() {
        return Integer.parseInt(getFrente().getString());
    }
    
    private static String getCor(int z){
        
        String resultado = "Branco";
        switch(z){
        
                case 1 -> resultado = "BRANCO";
                case 2 -> resultado = "PRETO";
                case 3 -> resultado = "BRANCO";
                case 4 -> resultado = "BRANCO";
        
                        }
        return resultado;
    }
    private static String getCorFundo (int f){
        
        String resultado = "Preto";
            
        switch(f){
        
                case 1 -> resultado = "Vermelho";
                case 2 -> resultado = "Amarelo";
                case 3 -> resultado = "Roxo";
                case 4 -> resultado = "Azul";
        }
        return resultado;
    }
    public StringColorida getTexto() {
        int z = getValor();

        return new StringColorida(String.format("%2d", z), getCor(z), getCorFundo(z));
    }
}
