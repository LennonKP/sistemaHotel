import java.util.Date;
import java.util.UUID;
import java.util.Map;

public class Cliente {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public Cliente(String nome, String email, String cpf, String telefone) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.setTelefone(telefone);
    }

    public Cliente(String nome) {
        this.setNome(nome);
    }

    public void fazerReserva(Map<UUID, Reserva> reservas, Quarto quarto, Date dataCheckin, Date dataCheckout) {
        var reserva = new Reserva(this, quarto, dataCheckin, dataCheckout);
        reservas.put(reserva.getNumero(), reserva);
        quarto.reservar();
        System.out.println("Reserva inserida!");
    }

    public void cancelarReserva() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
