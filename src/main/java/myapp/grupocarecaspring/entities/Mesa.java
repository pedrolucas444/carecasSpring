package myapp.grupocarecaspring.entities;

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
@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private int id;
        
    @Column(name = "quantidade_cadeiras", nullable = false)
    private int capacidade;

    @Column(name = "mesa_livre", nullable = false)
    private boolean disponivel = true;



      /**
     * Construtor para inicializar uma mesa com um ID e capacidade específicos.
     *
     * @param id         O ID da mesa.
     * @param capacidade A capacidade máxima de pessoas que a mesa comporta.
     */
    public Mesa(int id, int capacidade) {
        this.id = id;
        this.capacidade = capacidade;
 
    }

    public int getId() {
        return id;
    }


    /**
     * Obtém a capacidade máxima de pessoas que a mesa comporta.
     *
     * @return A capacidade da mesa.
     */
    public int getCapacidade() {
        return capacidade;
    }


    /**
     * Verifica se a mesa está disponível para uso.
     *
     * @return true se a mesa está disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }


     /**
     * Define se a mesa está disponível para uso.
     *
     * @param disponivel true para definir a mesa como disponível, false caso contrário.
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", capacidade=" + capacidade +
                ", disponivel=" + disponivel +
                '}';
    }
}