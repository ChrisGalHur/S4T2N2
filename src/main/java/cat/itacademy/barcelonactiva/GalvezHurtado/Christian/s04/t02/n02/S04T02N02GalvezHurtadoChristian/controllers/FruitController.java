package cat.itacademy.barcelonactiva.GalvezHurtado.Christian.s04.t02.n02.S04T02N02GalvezHurtadoChristian.controllers;


import cat.itacademy.barcelonactiva.GalvezHurtado.Christian.s04.t02.n02.S04T02N02GalvezHurtadoChristian.model.domain.Fruit;
import cat.itacademy.barcelonactiva.GalvezHurtado.Christian.s04.t02.n02.S04T02N02GalvezHurtadoChristian.model.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/fruit")
public class FruitController {

	@Autowired
	FruitRepository fruitRepository;

	@GetMapping("/getAll")
	public ResponseEntity<List<Fruit>> getAllFruits(@RequestParam(required = false) String name) {
		try {
			List<Fruit> fruits = new ArrayList<Fruit>();

			if (name == null)
				fruitRepository.findAll().forEach(fruits::add);
			else
				fruitRepository.findByNameContaining(name).forEach(fruits::add);
			
			if (fruits.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(fruits, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getOne/{id}")
	public ResponseEntity<Fruit> getFruitById(@PathVariable("id") int id) {
		Optional<Fruit> fruitaData = fruitRepository.findById(id);

		if (fruitaData.isPresent()) {
			return new ResponseEntity<>(fruitaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Fruit> createFruit(@RequestBody Fruit fruit) {
		try {
			Fruit fruitAdding = fruitRepository.save(new Fruit(fruit.getName(), fruit.getQuilos()));
			return new ResponseEntity<>(fruitAdding, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Fruit> updateFruit(@PathVariable("id") int id, @RequestBody Fruit fruits) {
		Optional<Fruit> fruitData = fruitRepository.findById(id);

		if (fruitData.isPresent()) {
			Fruit fruitAdding = fruitData.get();
			fruitAdding.setName(fruits.getName());
			fruitAdding.setQuilos(fruits.getQuilos());
			
			return new ResponseEntity<>(fruitRepository.save(fruitAdding), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteFruit(@PathVariable("id") int id) {
		try {
			fruitRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}