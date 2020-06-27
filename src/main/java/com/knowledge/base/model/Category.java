package com.knowledge.base.model;

import java.util.ArrayList;

public class Category {
	private String name;
	private ArrayList<Notes> notes;
	
	
	public Category(String name) {
		this.name = name;
		this.notes = new ArrayList<>();
	}
	
	public Category(String name, ArrayList<Notes> notes) {
		this.name = name;
		this.notes = notes;
	}
	
	public void addNotes(Notes note) {
		this.notes.add(note);
	}
	
	public void removeNotes(String name) {	
		
		for(int i = 0; i < notes.size(); i ++) {
			if (name.equals(notes.get(i).getName())){
				notes.remove(i);
			}
		}
	}
	
	public ArrayList<Notes> getNotes(){
		return notes;
	}
}
