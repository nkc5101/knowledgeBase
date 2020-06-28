package com.knowledge.base.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;
@Entity
public class Notes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	private String name;
	private String note;
	private ArrayList<URL> links;
	private ArrayList<File> files;
	
	public Notes() {
		
	}
	
	
	public Notes(String name, String note) {
		this.name=name;
		this.note=note;
		try {
			this.links = findLinks(note);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Notes(String name, String note, Collection<MultipartFile> files) {
		this.name = name;
		this.note = note;
		this.files = convertFiles(files);
		try {
			this.links = findLinks(note);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
		try {
			this.links = findLinks(note);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<URL> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<URL> links) {
		this.links = links;
	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(Collection<MultipartFile> files) {
		
		this.files = convertFiles(files);
	}

	private ArrayList<URL> findLinks(String note) throws MalformedURLException {
		ArrayList<URL> found = new ArrayList<>();
		String[] words = note.split(" ");
		for (int i = 0; i < words.length; i++) {
			if(words[i].contains("http://") || words[i].contains("https://") ) {
				found.add(new URL(words[i]));
			}
		}
		return found;
	}
	
	public File convert(MultipartFile file) throws IOException
	{    
	  File convFile = new File(file.getOriginalFilename());
	  convFile.createNewFile(); 
	  FileOutputStream fos = new FileOutputStream(convFile); 
	  fos.write(file.getBytes());
	  fos.close(); 
	  return convFile;
	}
	
	public ArrayList<File> convertFiles(Collection<MultipartFile> files){
		ArrayList<File> convertFile = new ArrayList<>();
		ArrayList<MultipartFile> toBeFiles = new ArrayList<>(files);
		for (int i = 0; i < toBeFiles.size(); i++) {
			try {
				convertFile.add(convert(toBeFiles.get(i)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return convertFile;
	}
	
}
