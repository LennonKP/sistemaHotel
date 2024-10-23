public class Quarto {
    private int numero;
    private Tipo tipo;
    private double valor;
    private boolean estaDisponivel;

    public Quarto(int numero, Tipo tipo, double valor) {
        this.setNumero(numero);
        this.setTipo(tipo);
        this.setValor(valor);
        this.estaDisponivel = true;
    }

    public void reservar() {
        this.estaDisponivel = false;
    }

    public void liberar() {
        this.estaDisponivel = true;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getEstaDisponivel() {
        return estaDisponivel;
    }

    public void setEstaDisponivel(boolean estaDisponivel) {
        this.estaDisponivel = estaDisponivel;
    }
}
