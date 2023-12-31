package com.example.be.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.be.dto.ClinicRequest;
import com.example.be.payload.DataResponse;
import com.example.be.security.CurrentUser;
import com.example.be.security.UserPrincipal;
import com.example.be.services.ClinicService;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {
	
	@Autowired
	ClinicService ClinicService;
	
	@PreAuthorize("hasRole('EXPERT')")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public DataResponse registerDoctor(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ClinicRequest clinicRequest){
		return ClinicService.addClinicCurrentDoctor(currentUser, clinicRequest);
	}
	
	@PreAuthorize("hasRole('EXPERT')")
	@RequestMapping(value= "{idClinic}/{usernameOrEmail}",method = RequestMethod.PUT, produces = "application/json")
	public DataResponse addDoctorIntoClinic(@CurrentUser UserPrincipal currentUser,@PathVariable("idClinic") String idClinic, @PathVariable("usernameOrEmail") String usernameOrEmail ){
		return ClinicService.addDoctorIntoClinic(currentUser, idClinic,usernameOrEmail);
	}
	
	@RequestMapping(value= "all",method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllClinics(){
		return ClinicService.getClinicAll();
	}
}
