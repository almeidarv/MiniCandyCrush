package Frontend;

import console.Console;

import java.util.Scanner;

public class TelaPause {

    private Scanner scanner;

    public TelaPause(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean exibirMenuPausa() {
        boolean pausaAtiva = true;
        while (pausaAtiva) {
            Console.limpaTela();
            Console.println("┌──────────────────────────────────────┐");
            Console.println("│ MENU DE PAUSA                        │");
            Console.println("│                                      │");
            Console.println("│ 1. Continuar jogo                    │");
            Console.println("│ 2. Iniciar novo jogo                 │");
            Console.println("│ 3. Carregar partida de um arquivo    │");
            Console.println("│ 4. Voltar ao menu principal          │");
            Console.println("│ 5. Sair do jogo                      │");
            Console.println("│ Escolha uma opção:                   │");
            Console.println("│                                      │");
            Console.println("│ MINI-CANDY-CRUSH                     │");
            Console.println("└──────────────────────────────────────┘");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Console.limpaTela();
                    pausaAtiva = false;
                    break;
                case 2:
                    Console.limpaTela();
                    return false;  
                case 3:
                    Console.limpaTela();
                    Console.println ("Não implementado");
                    return false; 
                case 4:
                    Console.limpaTela();
                    Console.limpaTela();
                    sairDoJogo();
                    return true;
                    case 5:
                    Console.limpaTela();
                    Console.limpaTela();
                    sairDoJogo();
                    return true;
                default:
                    Console.println("Opção inválida. Tente novamente.");
            }
        }
        return false;
    }

    private void sairDoJogo() {
        Console.println("Saindo do jogo...");
        System.exit(0);
    }
}
