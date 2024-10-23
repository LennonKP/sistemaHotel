import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
    private Map<String, Hotel> hoteis;
    private Map<Integer, Reserva> reservas;
    private List<Cliente> clientes;
    private Administrador admin;

    Sistema() {
        this.hoteis = new HashMap<String, Hotel>();
        this.reservas = new HashMap<Integer, Reserva>();
        this.clientes = new ArrayList<Cliente>();
        this.admin = new Administrador("Lennon");
    }

    public void run() {
        var scanner = new Scanner(System.in);
        int option;
        do {
            this.displayMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            this.handleOption(option, scanner);
        } while (option != 8);

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("===== Menu Principal =====");
        System.out.println("1. Adicionar Novo Hotel");
        System.out.println("2. Listar Todos os Hotéis");
        System.out.println("3. Adicionar Quarto a um Hotel");
        System.out.println("4. Listar Quartos Disponíveis em um Hotel");
        System.out.println("5. Fazer Reserva");
        System.out.println("6. Cancelar Reserva");
        System.out.println("7. Listar Reservas de um Cliente");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void handleOption(int option, Scanner scanner) {
        switch (option) {
            case 1 -> adicionarHotel(scanner);
            case 2 -> listarHoteis();
            case 3 -> adicionarQuarto(scanner);
            case 4 -> listarQuartosDisponiveis(scanner);
            case 5 -> fazerReserva(scanner);
            case 6 -> cancelarReserva(scanner);
            case 7 -> listarReservasCliente(scanner);
            case 8 -> System.out.println("Saindo do sistema...");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    /**
     * Solicita o nome e endereço do hotel, e o adiciona à lista de hotéis.
     * 
     * @param scanner
     */
    private void adicionarHotel(Scanner scanner) {
        String nome = ScannerUtils.getStringEntry(scanner, "Informe o nome do novo Hotel: ");
        String endereco = ScannerUtils.getStringEntry(scanner, "Informe o endereço do novo Hotel: ");
        this.admin.adicionarHotel(hoteis, nome, endereco);
    }

    /**
     * Lista os hotéis registrados no sistema.
     */
    private void listarHoteis() {
        for (var hotel : this.hoteis.entrySet()) {
            System.out.println(hotel);
        }
    }

    /**
     * Solicita o nome do hotel, número do quarto, tipo, e preço, e adiciona o
     * quarto ao hotel selecionado.
     * 
     * @param scanner
     */
    private void adicionarQuarto(Scanner scanner) {
        String nomeHotel = ScannerUtils.getStringEntry(scanner, "Informe o nome do Hotel: ");
        var hotel = hoteis.get(nomeHotel);
        if (hotel == null) {
            System.out.println("Hotel não existe");
            return;
        }

        int numeroQuarto = ScannerUtils.getIntegerEntry(scanner, "Informe o número do quarto: ", input -> input > 0);
        Tipo tipo = ScannerUtils.getEnumEntry(scanner, "Informe o tipo do quarto: ", Tipo.class);
        Double valor = ScannerUtils.getDoubleEntry(scanner, "Informe o valor do quarto: ", input -> input > 0);
        hotel.adicionarQuarto(numeroQuarto, tipo, valor);
    }

    /**
     * Solicita o nome do hotel e lista todos os quartos disponíveis.
     * 
     * @param scanner
     */
    private void listarQuartosDisponiveis(Scanner scanner) {
        String nomeHotel = ScannerUtils.getStringEntry(scanner, "Informe o nome do Hotel: ");
        var hotel = hoteis.get(nomeHotel);
        if (hotel == null) {
            System.out.println("Hotel não existe");
            return;
        }

        hotel.listarQuartosDisponiveis();
    }

    /**
     * Solicita informações do cliente e detalhes da reserva, como hotel, número do
     * quarto, data de check-in e check-out.
     * 
     * @param scanner
     */
    private void fazerReserva(Scanner scanner) {
        String nomeHotel = ScannerUtils.getStringEntry(scanner, "Informe o nome do Hotel: ");
        var hotel = hoteis.get(nomeHotel);
        if (hotel == null) {
            System.out.println("Hotel não existe");
            return;
        }

        // continuar
    }

    /**
     * Solicita o número da reserva e realiza o cancelamento.
     * 
     * @param scanner
     */
    private void cancelarReserva(Scanner scanner) {
        int numeroReserva = ScannerUtils.getIntegerEntry(scanner, "Informe o número da reserva:",
                input -> input == input);
        throw new UnsupportedOperationException("Unimplemented method 'cancelarReserva'");
    }

    /**
     * Solicita o nome do cliente e lista todas as reservas associadas.
     * 
     * @param scanner
     */
    private void listarReservasCliente(Scanner scanner) {
        String nomeCliente = ScannerUtils.getStringEntry(scanner, "Informe o nome do Cliente: ");
        Cliente clienteReserva = null;
        for (var cliente : this.clientes) {
            if (cliente.getNome().equals(nomeCliente)) {
                clienteReserva = cliente;
            }
        }
        if (clienteReserva == null) {
            System.out.println("Cliente não encontrado");
            return;

        }

        for (var reserva : this.reservas.entrySet()) {
            var reservaValue = reserva.getValue();
            if (reservaValue.getCliente() == clienteReserva) {
                System.out.println(reservaValue);
            }
        }
    }
}
