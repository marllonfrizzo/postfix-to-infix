package Main;

public class Node {
    private char valor;
    private Node nodeEsquerda;
    private Node nodeDireita;

    public Node(){
    }
        
    public Node(char valor) {
        this.valor = valor;
    }

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public Node getNoEsquerda() {
        return nodeEsquerda;
    }

    public void setNoEsquerda(Node noEsquerda) {
        this.nodeEsquerda = noEsquerda;
    }

    public Node getNoDireita() {
        return nodeDireita;
    }

    public void setNoDireita(Node noDireita) {
        this.nodeDireita = noDireita;
    }

    @Override
    public String toString() {
        return ""+valor;
    }
}
