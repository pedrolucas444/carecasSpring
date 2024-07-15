package myapp.grupocarecaspring.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Restaurante {
    private final List<Mesa> mesas;
    private final List<Cliente> clientes;
    private final Cardapio cardapio;
    private final Queue<Requisicao> filaDeEspera;
    private final List<Requisicao> requisicoesAtivas;

    public Restaurante(Cardapio cardapio) {
        this.cardapio = cardapio;
        this.clientes = new ArrayList<>();
        this.mesas = new ArrayList<>();
        this.filaDeEspera = new LinkedList<>();
        this.requisicoesAtivas = new ArrayList<>();
        inicializarMesas();
    }

       /**
     * Inicializa as mesas do restaurante com base nos intervalos de ID e capacidade.
     */
    private void inicializarMesas() {
        mesas.addAll(criarMesas(1, 4, 4));
        mesas.addAll(criarMesas(5, 8, 6));
        mesas.addAll(criarMesas(9, 10, 8));
    }


     /**
     * Cria mesas com IDs sequenciais e capacidade especificada.
     * @param idInicio ID inicial das mesas a serem criadas.
     * @param idFim ID final das mesas a serem criadas.
     * @param capacidade Capacidade de pessoas da mesa.
     * @return Lista de mesas criadas.
     */
    private List<Mesa> criarMesas(int idInicio, int idFim, int capacidade) {
        return IntStream.rangeClosed(idInicio, idFim)
                .mapToObj(id -> new Mesa(id, capacidade))
                .collect(Collectors.toList());
    }

     /**
     * Registra um novo cliente no restaurante.
     * @param cliente Cliente a ser registrado.
     */
    public void registrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

     /**
     * Busca uma mesa disponível com capacidade mínima especificada.
     * @param capacidade Capacidade mínima desejada da mesa.
     * @return Optional contendo a primeira mesa disponível encontrada.
     */
    public Optional<Mesa> buscarMesaDisponivel(int capacidade) {
        return mesas.stream()
                .filter(m -> m.isDisponivel() && m.getCapacidade() >= capacidade)
                .findFirst();
    }

     /**
     * Cria uma requisição para atender um cliente em uma mesa específica.
     * @param mesaId ID da mesa onde o cliente será atendido.
     * @param clienteId ID do cliente a ser atendido.
     * @param numeroDePessoas Número de pessoas na requisição.
     * @return A requisição criada para o atendimento.
     * @throws IllegalArgumentException Se a mesa ou cliente não forem encontrados ou não estiverem disponíveis.
     */
    public Requisicao criarRequisicao(int mesaId, int clienteId, int numeroDePessoas) {
        Optional<Mesa> mesaOpt = mesas.stream()
                .filter(m -> m.getId() == mesaId && m.isDisponivel())
                .findFirst();
        Optional<Cliente> clienteOpt = clientes.stream()
                .filter(c -> c.getId() == clienteId)
                .findFirst();

        if (mesaOpt.isPresent() && clienteOpt.isPresent()) {
            Mesa mesa = mesaOpt.get();
            Cliente cliente = clienteOpt.get();
            mesa.setDisponivel(false);
            Requisicao requisicao = new Requisicao(mesa, cliente, numeroDePessoas);
            requisicoesAtivas.add(requisicao);
            return requisicao;
        } else {
            throw new IllegalArgumentException("Mesa ou Cliente não encontrados ou indisponíveis.");
        }
    }

      /**
     * Verifica a fila de espera e atende as requisições pendentes com mesas disponíveis.
     */
    public void verificarRequisicoes() {
        while (!filaDeEspera.isEmpty()) {
            Requisicao proximaRequisicao = filaDeEspera.poll();
            Optional<Mesa> mesaOpt = buscarMesaDisponivel(proximaRequisicao.getNumeroDePessoas());
            if (mesaOpt.isPresent()) {
                Mesa mesa = mesaOpt.get();
                atenderRequisicao(mesa, proximaRequisicao);
            } else {
                filaDeEspera.offer(proximaRequisicao);
                break;
            }
        }
    }

     /**
     * Atende uma requisição, atribuindo uma mesa e registrando-a como ativa.
     * @param mesa Mesa onde a requisição será atendida.
     * @param requisicao Requisição a ser atendida.
     */
    private void atenderRequisicao(Mesa mesa, Requisicao requisicao) {
        mesa.setDisponivel(false);
        requisicao.setMesa(mesa);
        requisicoesAtivas.add(requisicao);
    }


      /**
     * Adiciona uma requisição à fila de espera para atendimento futuro.
     * @param requisicao Requisição a ser adicionada à fila de espera.
     */
    public void adicionarRequisicao(Requisicao requisicao) {
        filaDeEspera.offer(requisicao);
        verificarRequisicoes();
    }


      /**
     * Obtém a lista de todas as mesas do restaurante.
     * @return Lista de mesas do restaurante.
     */
    public List<Mesa> getMesas() {
        return mesas;
    }



    /**
     * Exibe o cardápio do restaurante na saída padrão.
     */
    public void exibirCardapio() {
        cardapio.exibirItens();
    }


     /**
     * Busca um cliente pelo seu ID.
     * @param clienteId ID do cliente a ser buscado.
     * @return Optional contendo o cliente encontrado, se existir.
     */
    public Optional<Cliente> buscarClientePorId(int clienteId) {
        return clientes.stream()
                .filter(c -> c.getId() == clienteId)
                .findFirst();
    }

     /**
     * Busca uma mesa pelo seu ID.
     * @param mesaId ID da mesa a ser buscada.
     * @return Optional contendo a mesa encontrada, se existir.
     */
    public Optional<Mesa> buscarMesaPorId(int mesaId) {
        return mesas.stream()
                .filter(m -> m.getId() == mesaId)
                .findFirst();
    }


     /**
     * Adiciona um item ao pedido de uma requisição específica.
     * @param requisicao Requisição onde o item será adicionado.
     * @param itemId ID do item a ser adicionado ao pedido.
     * @throws IllegalArgumentException Se o item não for encontrado no cardápio.
     */
    public void adicionarItemAoPedido(Requisicao requisicao, int itemId) {
        Optional<Item> itemOpt = cardapio.buscarItemPorId(itemId);
        if (itemOpt.isPresent()) {
            requisicao.adicionarItemAoPedido(itemOpt.get());
        } else {
            throw new IllegalArgumentException("Item não encontrado no cardápio.");
        }
    }


     /**
     * Obtém a lista de todas as requisições ativas em mesas.
     * @return Lista de requisições ativas.
     */
    public List<Requisicao> getRequisicoesEmMesas() {
        return requisicoesAtivas.stream()
                .filter(requisicao -> requisicao.getMesa() != null)
                .collect(Collectors.toList());
    }


     /**
     * Encerra o atendimento de uma requisição, liberando a mesa e exibindo um relatório.
     * @param requisicao Requisição que será encerrada.
     */
    public void encerrarAtendimento(Requisicao requisicao) {
        requisicao.getMesa().setDisponivel(true);
        requisicao.encerrar();
        requisicoesAtivas.remove(requisicao);
        System.out.println(requisicao.gerarRelatorio());
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "mesas=" + mesas +
                ", clientes=" + clientes +
                ", cardapio=" + cardapio +
                '}';
    }
}