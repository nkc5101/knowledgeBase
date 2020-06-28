package com.knowledge.base.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knowledge.base.model.NoteRepository;
import com.knowledge.base.model.Notes;

@Controller
@RequestMapping(path="/knowledge")
public class RestController {
	@Autowired
	private NoteRepository noteRepo;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewNote(@RequestParam String name, @RequestParam String note, @RequestParam(required = false) Iterable<File> files) {
		Notes notes = new Notes(name,note);
		noteRepo.save(notes);
		return "Saved Notes";
	}
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Notes> getAllNotes(){
		return noteRepo.findAll();
	}
	@PostMapping(path="/remove")
	public @ResponseBody String removeNote(@RequestParam int id) {
		noteRepo.deleteById(id);
		return "Deleted note";
	}
	
	@PostMapping(path="/update")
	public @ResponseBody String updateNote(@RequestParam int id, @RequestParam String name, @RequestParam String note,@RequestParam(required = false) Iterable<File> files) {
		return "testing";
	}
}
