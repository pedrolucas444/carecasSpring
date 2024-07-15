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
@Table(name = "clientes")
public class Cliente {
   
    
    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id;

    public Cliente(int id, String nome) {
        try {
            validarId(id);
            validarNome(nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());            
            throw e;         }

        this.id = id;
        this.nome = nome;
    }


     /**
     * Valida se o ID do cliente é válido (maior ou igual a zero).
     *
     * @param id O ID do cliente a ser validado.
     * @throws IllegalArgumentException Se o ID fornecido for menor do que zero.
     */
    private void validarId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID não pode ser menor do que 0.");
        }
    }


     /**
     * Valida se o nome do cliente é válido (não nulo e não vazio).
     *
     * @param nome O nome do cliente a ser validado.
     * @throws IllegalArgumentException Se o nome fornecido for nulo ou vazio.
     */
    private void validarNome(String nome) {
        if (nome == null || nome.length() == 0) {
            throw new IllegalArgumentException("Informe um nome válido.");
        }
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}