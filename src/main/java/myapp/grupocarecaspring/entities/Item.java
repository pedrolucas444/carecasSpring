package myapp.grupocarecaspring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor


@Entity
@Table(name = "itens")
public class Item {

    @Column(name = "descricao_item", nullable = false, length = 100)
    private String descricao;

    @Column(name = "preco_item", nullable = false)
    private double preco;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private int id;

    
    //Provavelmente essa associação ta errada
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


      /**
     * Construtor para inicializar um item com descrição, preço e ID.
     *
     * @param descricao A descrição do item.
     * @param preco     O preço do item.
     * @param id        O ID do item.
     */
    public Item(String descricao, double preco, int id) {     
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
    }

      /**
     * Obtém a descrição do item.
     *
     * @return A descrição do item.
     */
    public String getDescricao() {
        return descricao;
    }


     /**
     * Obtém o preço do item.
     *
     * @return O preço do item.
     */
    public double getPreco() {
        return preco;
    }


      /**
     * Obtém o ID do item.
     *
     * @return O ID do item.
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " - " + descricao + '\'' +
                " -  preco: R$ " + preco;
    }

}