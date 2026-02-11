package org.alumne.helena.primer_app_spring_boot.srv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.alumne.helena.primer_app_boot.model.ram.DocAlumno;
import org.springframework.stereotype.Service;

@Service
public class DocAlumnoService {
	
	private static List<DocAlumno> listdoc=new ArrayList<DocAlumno>();
	

	public List<String> listatipoDoc() {
		List <String> opcionesTipoDoc = new ArrayList<String>();
		opcionesTipoDoc.add("Certificado");
		opcionesTipoDoc.add("Justificante");
		opcionesTipoDoc.add("Solicitud");
		return opcionesTipoDoc;
	}
	
	private int siguienteIdDoc(String dni) {
		
		return  listdoc.stream().filter(p-> p.getDni().equals(dni)).map(DocAlumno::getId).max(Integer::compareTo).orElse(0)+1;
		
	}
	

	
	public void addDocAlumno(DocAlumno docAlumno) {
		int id= siguienteIdDoc(docAlumno.getDni());//calcular el siguiente id
		docAlumno.setId(id);
		listdoc.add(docAlumno);
	}
	

	
	//retorna la llista de documents de un alumne ordenat per id
	public List<DocAlumno> llistaDocsAlumnes(String dni) {
		
		return  listdoc.stream().filter(p-> p.getDni().equals(dni)).sorted(Comparator.comparingInt(DocAlumno::getId)).toList();
		
	}
	
	

	
	
	
	
}
