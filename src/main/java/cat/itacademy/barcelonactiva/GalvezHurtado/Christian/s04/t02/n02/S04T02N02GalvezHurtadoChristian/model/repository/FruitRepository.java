package cat.itacademy.barcelonactiva.GalvezHurtado.Christian.s04.t02.n02.S04T02N02GalvezHurtadoChristian.model.repository;

import cat.itacademy.barcelonactiva.GalvezHurtado.Christian.s04.t02.n02.S04T02N02GalvezHurtadoChristian.model.domain.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface FruitRepository extends JpaRepository<Fruit, Integer> {

	List<Fruit> findByNameContaining(String name);
}