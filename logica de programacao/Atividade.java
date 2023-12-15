import java.util.Scanner;

public class Atividade {

    public static void main(String[] args) {
        int[] filaProducao = new int[10];
        int tamanhoFila = 0;
        int inicioFila = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("###### LANCHONETE ######");
            System.out.println("# Escolha uma opção:   #");
            System.out.println("# 1. Incluir Pedido    #");
            System.out.println("# 2. Atender Pedido    #");
            System.out.println("# 3. Listar Pedidos    #");
            System.out.println("# 4. Pesquisar Pedido  #");
            System.out.println("# 5. Encerrar          #");
            System.out.println("# 6. Sair              #");
            System.out.println("########################");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if (tamanhoFila < filaProducao.length) {
                        System.out.println("Digite o número do pedido:");
                        int numeroPedido = scanner.nextInt();
                        incluirPedido(filaProducao, tamanhoFila, numeroPedido);
                        tamanhoFila++;
                        System.out.println("Pedido incluído na fila.");
                    } else {
                        System.out.println("A fila de produção está cheia. Não é possível incluir mais pedidos.");
                    }
                    break;
                case 2:
                    if (tamanhoFila > 0) {
                        int pedidoAtendido = atenderPedido(filaProducao, tamanhoFila, inicioFila);
                        inicioFila++;
                        tamanhoFila--;
                        System.out.println("Pedido " + pedidoAtendido + " atendido.");
                    } else {
                        System.out.println("A fila de produção está vazia. Não há pedidos para atender.");
                    }
                    break;
                case 3:
                    listarPedidos(filaProducao, tamanhoFila, inicioFila);
                    break;
                case 4:
                    System.out.println("Digite o número do pedido a ser pesquisado:");
                    int numeroPesquisa = scanner.nextInt();
                    pesquisarPedido(filaProducao, tamanhoFila, inicioFila, numeroPesquisa);
                    break;
                case 5:
                    encerrar(filaProducao, tamanhoFila);
                    break;
                case 6:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void incluirPedido(int[] filaProducao, int tamanhoFila, int numeroPedido) {
        filaProducao[(tamanhoFila) % filaProducao.length] = numeroPedido;
    }

    private static int atenderPedido(int[] filaProducao, int tamanhoFila, int inicioFila) {
        return filaProducao[inicioFila % filaProducao.length];
    }

    private static void listarPedidos(int[] filaProducao, int tamanhoFila, int inicioFila) {
        if (tamanhoFila > 0) {
            System.out.println("Pedidos pendentes:");
            for (int i = 0; i < tamanhoFila; i++) {
                int indice = (inicioFila + i) % filaProducao.length;
                System.out.println("Pedido " + filaProducao[indice]);
            }
        } else {
            System.out.println("A fila de produção está vazia. Não há pedidos pendentes.");
        }
    }

    private static void pesquisarPedido(int[] filaProducao, int tamanhoFila, int inicioFila, int numeroPesquisa) {
        for (int i = 0; i < tamanhoFila; i++) {
            int indice = (inicioFila + i) % filaProducao.length;
            if (filaProducao[indice] == numeroPesquisa) {
                System.out.println("Pedido " + numeroPesquisa + " encontrado na posição " + i + " da fila.");
                return;
            }
        }
        System.out.println("Pedido " + numeroPesquisa + " não encontrado na fila.");
    }

    private static void encerrar(int[] filaProducao, int tamanhoFila) {
        if (tamanhoFila == 0) {
            System.out.println("Encerrando o programa. Não há pedidos pendentes.");
            System.exit(0);
        } else {
            System.out.println("Ainda existem pedidos pendentes. Não é possível encerrar o programa.");
        }
    }
}
