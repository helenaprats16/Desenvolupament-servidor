package edu.alumne.dwes_ud3_primer_jpa.helena.dwes_ud3_primer_jpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.alumne.dwes_ud3_primer_jpa.helena.dwes_ud3_primer_jpa.model.ModuloDb;
import edu.alumne.dwes_ud3_primer_jpa.helena.dwes_ud3_primer_jpa.repository.ModuloRepository;

@SpringBootApplication
public class DwesUd3PrimerJpaApplication implements CommandLineRunner{
	/*Implementacion de logging para mostrar trazas */
	private static final Logger LOG = LoggerFactory.getLogger(DwesUd3PrimerJpaApplication.class);

	@Autowired
	private ModuloRepository moduloRepository;


	public static void main(String[] args) {
		SpringApplication.run(DwesUd3PrimerJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Crear MODULOS
		ModuloDb nuevoModuloSinIdDb=  crearModulo_SinId();
		LOG.info("\n\nNuevo Modulo sin indicar el ID -> \n{}\n\n",nuevoModuloSinIdDb);
		//ModuloDb nuevoModuloConIdDb=  crearModulo_ConId();
		//LOG.info("\n\nNuevo Modulo indicando el ID -> \n{}\n\n",nuevoModuloConIdDb);

		//Leer MODULOS
		//Como el 'id' es Long es 2L
		Optional<ModuloDb> modulo2Db= moduloRepository.findById(2L);
		if (modulo2Db.isPresent())
			LOG.info("\n\nMostrar Modulo si existe -> \n{}\n\n",modulo2Db.get());
		
		//Modificar MODULOS		
		ModuloDb moduloModificadoDb=modificarModulo(nuevoModuloSinIdDb);
		LOG.info("\n\nModificar Modulo -> \n{}\n\n",moduloModificadoDb);

		//Listar MODULOS
		List<ModuloDb> listaModulos=moduloRepository.findAll();
		LOG.info("\n\nListar Modulos -> \n{}\n\n",listaModulos);

		//Borrar MODULOS utilizando registro
		borrarModuloUtilizandoRegistro(moduloModificadoDb);//Borrar módulo 4
		//Tras borrar listaModulos contiene el módulo borrado porque no se actualiza
		//por lo que debemos de volver a consultar los módulos directamente de la BD
		LOG.info("\n\nLista tras borrar módulo 4 -> \n{}\n\n",moduloRepository.findAll());
		//Borrar MODULOS utilizando id
		borrarModuloUtilizandoId(3L);//Borrar módulo 3
		//Tras borrar listaModulos contiene el módulo borrado porque no se actualiza
		//por lo que debemos de volver a consultar los módulos directamente de la BD
		LOG.info("\n\nLista tras borrar módulo 3 -> \n{}\n\n",moduloRepository.findAll());
	}

	private ModuloDb crearModulo_SinId(){
		ModuloDb nuevoModulo2Db= new ModuloDb(); //Crear modulo nuevo
		nuevoModulo2Db.setNombre("Redes de Area Local");
		nuevoModulo2Db.setAbreviatura("RAL");
		nuevoModulo2Db.setHoras(3);
		return moduloRepository.save(nuevoModulo2Db);
	}
	
	private ModuloDb crearModulo_ConId(){
		//Probamos a darle un id que no le corresponde
		ModuloDb nuevoModulo2Db= new ModuloDb(100L,"Bases de datos",
		5,"BD");
		return moduloRepository.save(nuevoModulo2Db);
	}
	
	private ModuloDb modificarModulo(ModuloDb moduloAModificar){
		moduloAModificar.setHoras(5);
		return moduloRepository.save(moduloAModificar);
	}
	
	private void borrarModuloUtilizandoRegistro(ModuloDb moduloABorrar){
		moduloRepository.delete(moduloABorrar);
	}
	
	private void borrarModuloUtilizandoId(Long id){
		if (moduloRepository.findById(id).isPresent())
			moduloRepository.deleteById(id);
		 else LOG.info("No se ha encontrado el módulo ",id);		
	}


}
