package cat.itacademy.barcelonactiva.GalvezHurtado.Christian.s04.t02.n02.S04T02N02GalvezHurtadoChristian.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "fruits")
public class Fruit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "quilos")
	private int quilos;

	public Fruit() {
    }

    public Fruit(String name, int quilos) {
        this.name = name;
        this.quilos = quilos;
    }

    //getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getQuilos() {
    	return quilos;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setQuilos(int quilos) {
    	this.quilos = quilos;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quilos=" + quilos +
                '}';
    }
}