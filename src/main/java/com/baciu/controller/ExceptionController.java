package com.baciu.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(MultipartException.class)
	public String multipartExHandle(MultipartException e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "Zbyt duży plik");
		return "redirect:/changeAvatar";
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String uploadedFileToolarge(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "Zbyt duży plik");
		return "redirect:/changeAvatar";
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String mismatchHandle(MethodArgumentTypeMismatchException e) {
		return "redirect:/main";
	}

}
