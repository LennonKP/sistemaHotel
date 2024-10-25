import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Reserva {
    private UUID numero;
    private Cliente cliente;
    private Quarto quarto;
    private Date dataCheckin;
    private Date dataCheckout;
    private Status status;

    public Reserva(Cliente cliente, Quarto quarto, Date dataCheckin, Date dataCheckout) {
        this.setCliente(cliente);
        this.setNumero(UUID.randomUUID());
        this.setQuarto(quarto);
        this.setDataCheckin(dataCheckin);
        this.setDataCheckout(dataCheckout);
        this.setStatus(status);
    }

    @Override
    public String toString() {
        var sdf = new SimpleDateFormat();
        return String.format(
                "Cliente: %s - Número: %s - CheckIn - %s - CheckOut - %s",
                this.getCliente().getNome(),
                this.getQuarto().getNumero(),
                sdf.format(this.getDataCheckin()),
                sdf.format(this.getDataCheckout()));
    }

    // Não sabia o que caracterizava confirmar e cancelar então criei um STATUS
    public void confirmar() {
        this.status = Status.CONFIRMADA;
    }

    public void cancelar() {
        this.status = Status.CANCELADA;
    }

    public UUID getNumero() {
        return numero;
    }

    public void setNumero(UUID numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
