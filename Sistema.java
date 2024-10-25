import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
    private Map<String, Hotel> hoteis;
    private Map<UUID, Reserva> reservas;
    private List<Cliente> clientes;
    private Administrador admin;

    public Sistema() {
        this.hoteis = new HashMap<String, Hotel>();
        this.reservas = new HashMap<UUID, Reserva>();
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
            System.out.println();
            this.handleOption(option, scanner);
            System.out.println();
        } while (option != 9);

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
        System.out.println("8. Adicionar Cliente");
        System.out.println("9. Sair");
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
            case 8 -> adicionarCliente(scanner);
            case 9 -> System.out.println("Saindo do sistema...");
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
        String nomeCliente = ScannerUtils.getStringEntry(scanner, "Informe o nome do Cliente: ");
        Cliente cliente = null;
        for (var c : this.clientes) {
            if (c.getNome().equals(nomeCliente)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
            return;
        }

        String nomeHotel = ScannerUtils.getStringEntry(scanner, "Informe o nome do Hotel: ");
        var hotel = hoteis.get(nomeHotel);
        if (hotel == null) {
            System.out.println("Hotel não existe");
            return;
        }

        int numeroQuarto = ScannerUtils.getIntegerEntry(scanner, "Informe o número do Quarto: ", i -> i > 0);
        var quarto = hotel.getQuartos().get(numeroQuarto);
        if (quarto == null) {
            System.out.println("Quarto não existe");
            return;
        }

        var dataIn = ScannerUtils.getDateEntry(scanner, "Informe a data de CheckIn", d -> d.compareTo(new Date()) >= 0);
        var dataOut = ScannerUtils.getDateEntry(scanner, "Informe a data de CheckOut", d -> d.after(dataIn));
        cliente.fazerReserva(this.reservas, quarto, dataIn, dataOut);
    }

    /**
     * Solicita o número da reserva e realiza o cancelamento.
     * Lennon: e o cliente? tem o método de cancelar reserva e não estamos pegando o
     * cliente aqui
     * 
     * @param scanner
     */
    private void cancelarReserva(Scanner scanner) {
        var numeroReserva = UUID.fromString(ScannerUtils.getStringEntry(scanner, "Informe o número da reserva: "));
        var reserva = this.reservas.get(numeroReserva);
        if (reserva == null) {
            System.out.println("Reserva não existe");
            return;
        }

        reserva.cancelar();
        System.out.println("Reserva cancelada");
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

        int numReservas = 0;
        for (var reserva : this.reservas.entrySet()) {
            var reservaValue = reserva.getValue();
            if (reservaValue.getCliente() == clienteReserva) {
                numReservas++;
                System.out.println(reservaValue);
            }
        }

        if (numReservas == 0) {
            System.out.println("Nenhuma reserva");
        }
    }

    private void adicionarCliente(Scanner scanner) {
        String nomeCliente = ScannerUtils.getStringEntry(scanner, "Informe o nome do Cliente: ");

        this.clientes.add(new Cliente(nomeCliente));
    }
}
