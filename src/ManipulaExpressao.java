package Main;

import java.util.Stack;

public class ManipulaExpressao {

    private Node root;
    private String expFinal;

    public ManipulaExpressao() {
        this.expFinal = "";
    }

    // Função para verificar se dado char é um operador ou número
    private boolean verificaOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Retorna false se o caractere em questão não for um número válido
    private boolean verificaNumero(char c) {
        return !(c < 48 || c > 57);
    }

    // Constrói a árvore binária com os operadores e operandos
    public int constroiArvore(String exp) {
        Stack<Node> pilha = new Stack();
        Node n, n1, n2;
        char[] c;

        if (exp.length() == 0) {
            return -3;
        }

        for (int i = 0; i < exp.length(); i++) {
            c = exp.toCharArray();
            if (!verificaNumero(c[i]) && !verificaOperador(c[i])) {
                return -1;
            } else if (!verificaOperador(c[i])) {
                n = new Node(c[i]);
                pilha.push(n);
            } else {
                n = new Node(c[i]);
                try {
                    n1 = pilha.pop();
                    n2 = pilha.pop();

                    n.setNoDireita(n1);
                    n.setNoEsquerda(n2);

                    pilha.push(n);
                } catch (Exception e) {
                    return -2;
                }
            }
        }
        try {
            n = pilha.peek();
            root = n;
            pilha.pop();
        } catch (Exception e) {
            return -3;
        }

        // Verifica se a expressão construida é válida
        if (pilha.empty()) {
            // Pilha vazia, somente uma árvore, expressão correta!
            return 0;
        } else {
            // Pilha ainda contêm árvores, significa expresão inválida!
            return -4;
        }
    }

    // Função para converter um char em número
    private int toDigit(char ch) {
        return ch - '0';
    }

    // Função que resolve a equação da árvore
    public double resolver() {
        try {
            resolver(root);
        } catch (Exception e) {
            System.out.println("Erro na Resolução! Descrição: " + e + ". Programa finalizado.");
            System.exit(0);
        }
        return resolver(root);
    }

    // Executa as operações da árvore de expressões
    private double resolver(Node node) {
        if (node.getNoDireita() == null && node.getNoEsquerda() == null) {
            return toDigit(node.getValor());
        } else {
            double resultado;
            double esquerda = resolver(node.getNoEsquerda());
            double direita = resolver(node.getNoDireita());
            char operador = node.getValor();

            switch (operador) {
                case '+':
                    resultado = esquerda + direita;
                    break;
                case '-':
                    resultado = esquerda - direita;
                    break;
                case '*':
                    resultado = esquerda * direita;
                    break;
                case '/':
                    resultado = esquerda / direita;
                    break;
                default:
                    resultado = esquerda + direita;
                    break;
            }
            return resultado;
        }
    }

    // Função que exibe a expressão na forma infixa
    public String posfixaParaInfixa() {
        try {
            inOrdem(root);
        } catch (Exception e) {
            System.out.println("Erro ao Imprimir a Árvore! Descrição: " + e + ". Programa finalizado.");
            System.exit(0);
        }
        return expFinal;
    }

    // Percorre a árvore emOrdem
    private void inOrdem(Node node) {
        if (node != null) {
            expFinal += "(";
            //System.out.print("(");
            inOrdem(node.getNoEsquerda());
            //System.out.print(node.getValor());
            expFinal += node.getValor();
            //System.out.println("expFinal: "+expFinal);
            inOrdem(node.getNoDireita());
            expFinal += ")";
            //System.out.print(")");
        }
    }

    /*public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getExpFinal() {
        return expFinal;
    }*/
}
