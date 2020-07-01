package com.knowledge.base.controller;

import java.io.File;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.knowledge.base.model.NoteRepository;
import com.knowledge.base.model.Notes;
import com.sun.tools.javac.util.JCDiagnostic.Note;

@Controller
@RequestMapping(path="/knowledge")
public class RestController {
	@Autowired
	private NoteRepository noteRepo;
	
	@GetMapping(path="/newNote")
	public String getNewNote(Model model) {
		model.addAttribute(new Notes());
		return "add";
	}
	@PostMapping(path="/add")
	public @ResponseBody String addNewNote(@RequestParam String name, @RequestParam String note, @RequestBody(required = false) Collection<MultipartFile> files, RedirectAttributes redirectAttr) {
		if(files.equals(null)) {
			Notes notes = new Notes(name, note);
			noteRepo.save(notes);
			return "index";
		} else {
			Notes notes = new Notes(name, note, files);
			noteRepo.save(notes);
			return "index";
		}
		
	}
	@GetMapping(path="/all")
	public  String getAllNotes(Model model){
		model.addAttribute("notes", noteRepo.findAll());
		return "note";
	}
	@PostMapping(path="/remove")
	public @ResponseBody String removeNote(@RequestParam int id) {
		noteRepo.deleteById(id);
		return "index";
	}
	
	@PostMapping(path="/update")
	public @ResponseBody String updateNote(@RequestParam int id, @RequestParam String name, @RequestParam String note,@RequestParam(required = false) Collection<MultipartFile> files) {
		if (files.size() == 0) {
			noteRepo.findById(id).get().setName(name);
			noteRepo.findById(id).get().setNote(note);
			noteRepo.save(noteRepo.findById(id).get());
			return "Note updated.";
		} else {
			noteRepo.findById(id).get().setName(name);
			noteRepo.findById(id).get().setNote(note);
			noteRepo.findById(id).get().setFiles(files);
			noteRepo.save(noteRepo.findById(id).get());
			return "Note updated with files";
		}
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody Notes getNote(@PathVariable int id) {
		return noteRepo.findById(id).get();
	}
	@GetMapping(path="/")
	public String getBase(Model model) {
		model.addAttribute("notes", noteRepo.findAll());
		return "note";
	}
}
