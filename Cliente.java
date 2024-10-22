import java.util.UUID;

public class Cliente {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    Cliente(String nome, String email, String telefone) {
        this.id = UUID.randomUUID();
    }

    public void fazerReserva() {

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
