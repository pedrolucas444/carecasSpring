package myapp.grupocarecaspring.entities;

import java.util.ArrayList;
import java.util.List;

public class MenuFechado extends Item {
    private List<Item> itensMenu;


      /**
     * Construtor para inicializar um menu fechado com um ID específico.
     *
     * @param id O ID do menu fechado.
     */
    public MenuFechado(int id) {
        super("Menu Fechado", 32.0, id);
        this.itensMenu = new ArrayList<>();
    }


      /**
     * Adiciona uma comida ao menu fechado, limitado a uma única opção.
     *
     * @param comida A comida a ser adicionada ao menu.
     */
    public void adicionarComida(Item comida) {
        if (itensMenu.size() < 1) {
            itensMenu.add(comida);
        }
    }


    /**
     * Adiciona uma bebida ao menu fechado, limitado a três opções.
     *
     * @param bebida A bebida a ser adicionada ao menu.
     */
    public void adicionarBebida(Item bebida) {
        if (itensMenu.size() < 3) {
            itensMenu.add(bebida);
        }
    }


     /**
     * Exibe as opções disponíveis de comida e bebida no menu fechado.
     */
    public void exibirOpcoesDisponiveis() {
        System.out.println("Opções disponíveis para o Menu Fechado:");
        System.out.println("Comidas:");
        System.out.println("1 - Falafel Assado");
        System.out.println("2 - Caçarola de Legumes");
        System.out.println("Bebidas:");
        System.out.println("3 - Copo de Suco");
        System.out.println("4 - Refrigerante Orgânico");
        System.out.println("5 - Cerveja Vegana");
    }


      /**
     * Obtém a lista de itens que compõem o menu fechado.
     *
     * @return A lista de itens do menu fechado.
     */
    public List<Item> getItensMenu() {
        return itensMenu;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append(", Itens do Menu: ");
        itensMenu.forEach(item -> builder.append(item.getDescricao()).append(", "));
        return builder.toString();
    }
}