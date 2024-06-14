package Backend;

import java.util.Random;
import mecanicas.Tabuleiro;

public class TabuleiroMiniCandyCrush extends Tabuleiro {
    private static final int TIPOS_DE_PECAS = 4; 

    public TabuleiroMiniCandyCrush(int numLinhas, int numColunas, Peca fundo) {
        super(numLinhas, numColunas, fundo);
        inicializarTabuleiro();
    }

    public TabuleiroMiniCandyCrush() {
        super(8, 8, new Peca());
        inicializarTabuleiro();
    }
    
    public Peca setValorPessa(int x,int y , int v){
         Peca novaPeca = new Peca(v);
         setFundo(x, y, new Peca(v));
         return novaPeca;

    }

     private int pontos = 0;

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    private void inicializarTabuleiro() {
        for (int x = 0; x < getTotalColunas(); x++) {
            for (int y = 0; y < getTotalLinhas(); y++) {
                setFundo(x, y, novaPecaAleatoria());
            }
        }
    }

    private Peca novaPecaAleatoria() {
        Random rand = new Random();
        int valor = rand.nextInt(TIPOS_DE_PECAS) + 1;
        return new Peca(valor);
    }

    @Override
    public Peca getFundo(int x, int y) {
        return new Peca(super.getFundo(x, y).getFrente());
    }

    public void moverPeca(int x1, int y1, int x2, int y2) {
        Peca temp = getFundo(x1, y1);
        setFundo(x1, y1, getFundo(x2, y2));
        setFundo(x2, y2, temp);

        if (!verificarAlinhamento()) {
            temp = getFundo(x1, y1);
            setFundo(x1, y1, getFundo(x2, y2));
            setFundo(x2, y2, temp);
        } else {
            eliminarAlinhamentos();
            cairPecas();
            preencherEspacos();
        }
    }

    private boolean verificarAlinhamento() {
        for (int x = 0; x < getTotalColunas(); x++) {
            for (int y = 0; y < getTotalLinhas(); y++) {
                if (verificarAlinhamentoNaPosicao(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarAlinhamentoNaPosicao(int x, int y) {
        int valor = getFundo(x, y).getValor();
        if (x <= getTotalColunas() - 3 && 
            getFundo(x + 1, y).getValor() == valor && 
            getFundo(x + 2, y).getValor() == valor) {
            return true;
        }
        if (y <= getTotalLinhas() - 3 && 
            getFundo(x, y + 1).getValor() == valor && 
            getFundo(x, y + 2).getValor() == valor) {
            return true;
        }
        return false;
    }

    private void eliminarAlinhamentos() {
        boolean[][] paraEliminar = new boolean[getTotalColunas()][getTotalLinhas()];

        for (int x = 0; x < getTotalColunas(); x++) {
            for (int y = 0; y < getTotalLinhas(); y++) {
                int valor = getFundo(x, y).getValor();
                if (x <= getTotalColunas() - 3 && 
                    getFundo(x + 1, y).getValor() == valor && 
                    getFundo(x + 2, y).getValor() == valor) {
                    paraEliminar[x][y] = true;
                    paraEliminar[x + 1][y] = true;
                    paraEliminar[x + 2][y] = true;
                }
                if (y <= getTotalLinhas() - 3 && 
                    getFundo(x, y + 1).getValor() == valor && 
                    getFundo(x, y + 2).getValor() == valor) {
                    paraEliminar[x][y] = true;
                    paraEliminar[x][y + 1] = true;
                    paraEliminar[x][y + 2] = true;
                }
            }
        }

        for (int x = 0; x < getTotalColunas(); x++) {
            for (int y = 0; y < getTotalLinhas(); y++) {
                if (paraEliminar[x][y]) {
                    setFundo(x, y, new Peca(0));  
                }
            }
        }
    }

    private void cairPecas() {
        for (int x = 0; x < getTotalColunas(); x++) {
            for (int y = getTotalLinhas() - 1; y >= 0; y--) {
                if (getFundo(x, y).getValor() == 0) {
                    for (int k = y; k > 0; k--) {
                        setFundo(x, k, getFundo(x, k - 1));
                    }
                    setFundo(x, 0, novaPecaAleatoria());
                }
            }
        }
    }

    private void preencherEspacos() {
        for (int x = 0; x < getTotalColunas(); x++) {
            for (int y = 0; y < getTotalLinhas(); y++) {
                if (getFundo(x, y).getValor() == 0) {
                    setFundo(x, y, novaPecaAleatoria());
                }
            }
        }
    }
    public boolean temCombinacaoValida() {
    for (int x = 0; x < getTotalColunas(); x++) {
        for (int y = 0; y < getTotalLinhas(); y++) {
            if (verificarCombinacao(x, y)) {
                return true;
            }
        }
    }
    return false;
}

private boolean verificarCombinacao(int x, int y) {
    Peca peca = getFundo(x, y);
    int valor = peca.getValor();

    if (x + 2 < getTotalColunas() && 
        getFundo(x + 1, y).getValor() == valor && 
        getFundo(x + 2, y).getValor() == valor) {
        return true;
    }

    if (y + 2 < getTotalLinhas() && 
        getFundo(x, y + 1).getValor() == valor && 
        getFundo(x, y + 2).getValor() == valor) {
        return true;
    }

    return false;
}

}
