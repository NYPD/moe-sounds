package com.moesounds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HomeRestController {

	@RequestMapping(value = "form-save", method = RequestMethod.POST)
	public @ResponseBody String doUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("name") String name) {                 
	    return "Uploaded: " + multipartFile.getSize() + " bytes" + " with the name of: " + name;
	}
}
