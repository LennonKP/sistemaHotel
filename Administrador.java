import java.util.Map;

public class Administrador {
    private String nome;

    Administrador(String nome) {
        this.nome = nome;
    }

    public void adicionarHotel(Map<String, Hotel> hoteis, String nome, String endereco) {
        if (hoteis.containsKey(nome)) {
            System.out.println("Hotel já existe");
            return;
        }
        hoteis.put(nome, new Hotel(nome, endereco));
        System.out.println("Hotel inserido");
    }

    public void removerHotel(Map<String, Hotel> hoteis, String hotelName) {
        if (!hoteis.containsKey(nome)) {
            System.out.println("Hotel não existe");
            return;
        }
        hoteis.remove(hotelName);
        System.out.println("Hotel removido");
    }
}
