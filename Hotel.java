import java.util.Map;
import java.util.HashMap;

public class Hotel {
    private String nome;
    private String endereco;
    private Map<Integer, Quarto> quartos;

    Hotel(String nome) {
        this.setNome(nome);
        this.quartos = new HashMap<Integer, Quarto>();
    }

    Hotel(String nome, String endereco) {
        this.setNome(nome);
        this.setEndereco(endereco);
        this.quartos = new HashMap<Integer, Quarto>();
    }

    @Override
    public String toString() {
        return String.format(
                "Hotel: %s - Endereço: %s - Número de quartos - %s",
                this.getNome(), this.getEndereco(), this.quartos.size());
    }

    public void adicionarQuarto(int numero, Tipo tipo, double valor) {
        if (this.quartos.containsKey(numero)) {
            System.out.println("Quarto já existe");
            return;
        }

        this.quartos.put(numero, new Quarto(numero, tipo, numero));
        System.out.println("Quarto inserido");
    }

    public void removerQuarto(int numero) {
        var quarto = this.quartos.get(numero);
        if (quarto == null) {
            System.out.println("Quarto não existe");
            return;
        }

        this.quartos.remove(numero);
    }

    public void listarQuartosDisponiveis() {
        for (var quarto : this.quartos.entrySet()) {
            if (quarto.getValue().getEstaDisponivel()) {
                System.out.println(quarto);
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Map<Integer, Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(Map<Integer, Quarto> quartos) {
        this.quartos = quartos;
    }

}
