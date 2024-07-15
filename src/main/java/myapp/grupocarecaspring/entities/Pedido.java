package myapp.grupocarecaspring.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data

@Entity
@Table(name = "pedidos")
public class Pedido {
    private final double TAXA = 0.1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @OneToMany(mappedBy = "pedido")
    private final List<Item> itens;


    public Pedido() {
        this.itens = new ArrayList<>();
    }

     /**
     * Adiciona um item ao pedido.
     * @param item Item a ser adicionado ao pedido.
     */
    public void adicionarItem(Item item) {
        itens.add(item);
    }

     /**
     * Calcula o valor total dos itens no pedido.
     * @return O valor total dos itens.
     */
    public double calcularValorTotal() {
        return itens.stream()
                .mapToDouble(Item::getPreco)
                .sum();
    }

     /**
     * Calcula o valor total do pedido incluindo a taxa de serviço.
     * @return O valor total do pedido com a taxa de serviço.
     */
    public double calcularValorTotalComTaxa() {
        double valorTotal = calcularValorTotal();
        return valorTotal + (valorTotal * 0.10);
    }

     /**
     * Calcula o valor por pessoa, dividindo o valor total com taxa pelo número de pessoas.
     * @param numeroDePessoas Número de pessoas para dividir o valor.
     * @return O valor por pessoa.
     */
    public double calcularValorPorPessoa(int numeroDePessoas) {
        return calcularValorTotalComTaxa() / numeroDePessoas;
    }

     /**
     * Lista os itens do pedido formatados em uma string.
     * @return Uma string contendo a lista de itens do pedido com seus preços.
     */
    public String listarItens() {
        StringBuilder builder = new StringBuilder();
        itens.forEach(item -> {
            builder.append(item.getDescricao()).append(" - R$ ").append(String.format("%.2f", item.getPreco()))
                    .append("\n");
        });
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "itens=" + itens +
                '}';
    }
}