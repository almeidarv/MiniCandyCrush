package Frontend;

import Backend.TabuleiroMiniCandyCrush;
import Backend.Peca;
import console.Console;

import java.util.Scanner;

public class TelaCandy {

    private Scanner scanner;

    public TelaCandy(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciarNovoJogo() {
        Console.println("Digite o número máximo de jogadas permitidas:");
        int jogadasMaximas = scanner.nextInt();
        scanner.nextLine();

        TabuleiroMiniCandyCrush tabuleiro = new TabuleiroMiniCandyCrush();
        boolean jogoAtivo = true;
        int jogadasRestantes = jogadasMaximas;

        while (jogoAtivo && jogadasRestantes > 0) {
            exibirTabuleiro(tabuleiro);

            Console.println("Jogadas restantes: " + jogadasRestantes);
            Console.println("Digite as coordenadas da peça a ser movida (linha1 coluna1 linha2 coluna2) ou 'sair' para sair. Pressione 'P' para pausar:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("sair")) {
                jogoAtivo = false;
                continue;
            } else if (input.equalsIgnoreCase("P")) {
                TelaPause telaPause = new TelaPause(scanner);
                if (telaPause.exibirMenuPausa()) {
                    jogoAtivo = false;
                }
                continue;
            }

            String[] partes = input.split(" ");
            if (partes.length != 4) {
                Console.println("Entrada inválida. Digite no formato: linha1 coluna1 linha2 coluna2");
                continue;
            }

            try {
                int linha1 = Integer.parseInt(partes[0]);
                int coluna1 = Integer.parseInt(partes[1]);
                int linha2 = Integer.parseInt(partes[2]);
                int coluna2 = Integer.parseInt(partes[3]);

                if (isAdjacent(linha1, coluna1, linha2, coluna2)) {
                    if (isMoveValid(tabuleiro, linha1, coluna1, linha2, coluna2)) {
                        tabuleiro.moverPeca(linha1, coluna1, linha2, coluna2);
                        jogadasRestantes--; 
                    } else {
                        Console.println("Movimento inválido. Não resulta em combinação válida.");
                    }
                } else {
                    Console.println("As peças selecionadas não são adjacentes. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                Console.println("Entrada inválida. Certifique-se de digitar números.");
            } catch (IndexOutOfBoundsException e) {
                Console.println("Movimento fora dos limites do tabuleiro.");
            }
        }

        if (jogadasRestantes == 0) {
            Console.println("Você atingiu o número máximo de jogadas!");
        }
    }

    private boolean isAdjacent(int linha1, int coluna1, int linha2, int coluna2) {
        return (Math.abs(linha1 - linha2) == 1 && coluna1 == coluna2) ||
                (Math.abs(coluna1 - coluna2) == 1 && linha1 == linha2);
    }

    private boolean isMoveValid(TabuleiroMiniCandyCrush tabuleiro, int linha1, int coluna1, int linha2, int coluna2) {
        tabuleiro.moverPeca(linha1, coluna1, linha2, coluna2);

        boolean valid = tabuleiro.temCombinacaoValida();

        tabuleiro.moverPeca(linha2, coluna2, linha1, coluna1);

        return valid;
    }
        
    
        
    private void exibirTabuleiro(TabuleiroMiniCandyCrush tabuleiro) {
       
        Console.println("  ┌──────────────────────────┐");
        Console.println("  │ ┌──────────────────────┐ │");
        Console.println("  │ │   Mini-Candy-Crush   │ │");
        Console.println("  │ │                      │ │");
         for (int y = 0; y < tabuleiro.getTotalLinhas(); y++) {
             Console.print("  │ │   ");
            for (int x = 0; x < tabuleiro.getTotalColunas(); x++) {
                Peca peca = tabuleiro.getFundo(x, y);
                Console.print(peca.getTexto());
            } Console.print("   │ │   ");
            Console.println("    ");
        }
         Console.println("  │ │                      │ │");
         Console.println("  │ └──────────────────────┘ │");
         Console.println("  └──────────────────────────┘");
    }
}
