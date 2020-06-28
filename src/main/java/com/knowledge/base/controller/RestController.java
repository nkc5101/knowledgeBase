package com.knowledge.base.controller;

import java.io.File;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.knowledge.base.model.NoteRepository;
import com.knowledge.base.model.Notes;

@Controller
@RequestMapping(path="/knowledge")
public class RestController {
	@Autowired
	private NoteRepository noteRepo;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewNote(@RequestParam String name, @RequestParam String note, @RequestBody(required = false) Collection<MultipartFile> files) {
		if(files.equals(null)) {
			Notes notes = new Notes(name, note);
			noteRepo.save(notes);
			return "Saved Notes without files";
		} else {
			Notes notes = new Notes(name, note, files);
			noteRepo.save(notes);
			return "Saved Notes with files";
		}
		
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
	public @ResponseBody String updateNote(@RequestParam int id, @RequestParam String name, @RequestParam String note,@RequestParam(required = false) Collection<MultipartFile> files) {
		if (files.size() == 0) {
			noteRepo.findById(id).get().setName(name);
			noteRepo.findById(id).get().setNote(note);
			return "Note updated.";
		} else {
			noteRepo.findById(id).get().setName(name);
			noteRepo.findById(id).get().setNote(note);
			noteRepo.findById(id).get().setFiles(files);
			return "Note updated with files";
		}
	}
}
