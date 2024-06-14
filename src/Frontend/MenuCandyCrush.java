package Frontend;

import console.Console;

import java.util.Scanner;

public class MenuCandyCrush {

    private Scanner scanner;

    public MenuCandyCrush() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        boolean sair = false;
        while (!sair) {
            Console.println("┌────────────────────────────────────┐");
            Console.println("│ Bem-vindo ao Mini Candy Crush      │");
            Console.println("│                                    │");
            Console.println("│     Escolha uma opção:             │");
            Console.println("│     1. Jogar                       │");
            Console.println("│     2. Carregar jogo               │");
            Console.println("│     3. !!INSTRUÇÕES!!              │");
            Console.println("│     4. Sair                        │");
            Console.println("└────────────────────────────────────┘");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Console.limpaTela();
                    TelaCandy telaCandy = new TelaCandy(scanner);
                    telaCandy.iniciarNovoJogo();
                    break;
                case 2:
                    Console.println("Não Implementado");
                    
                    break;
                 case 3:
                    Console.println("┌────────────────────────────────┐");
                    Console.println("│===========INSTRUÇÕES===========│");
                    Console.println("│ Para movimentar as peças,      │");
                    Console.println("│ selecione primeiro a linha e   │");
                    Console.println("│ a coluna da peça que deseja    │");
                    Console.println("│ mover, e em seguida a linha    │");
                    Console.println("│ e a coluna para onde deseja    │");
                    Console.println("│ que a peça seja movida         │"); 
                    Console.println("│ Insira P para pausar o jogo    │");
                    Console.println("│===========INSTRUÇÕES===========│");
                    Console.println("└────────────────────────────────┘");
                    break;
                case 4:
                    Console.println("Saindo do jogo.");
                    sair = true;
                    break;
                default:
                    Console.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }

        scanner.close();
    }

}
