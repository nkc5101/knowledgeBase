package com.knowledge.base.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Notes {
	@Id
	private String name;
	private String note;
	private ArrayList<URL> links;
	private ArrayList<File> files;
	
	
	public Notes(String name, String note) {
		this.name=name;
		this.note=note;
	}
	
	public Notes(String name, String note, ArrayList<File> files) {
		this.name = name;
		this.note = note;
		this.files = files;
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

	public void setFiles(ArrayList<File> files) {
		this.files = files;
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
	
}
