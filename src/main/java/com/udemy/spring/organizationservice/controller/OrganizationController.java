package com.udemy.spring.organizationservice.controller;

import com.udemy.spring.organizationservice.dto.OrganizationDto;
import com.udemy.spring.organizationservice.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/organizations")
public class OrganizationController {
    private OrganizationService organizationService;

    //build and create organization REST API
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@Valid @RequestBody OrganizationDto organization){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organization);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    //build get organization by id REST API
    @GetMapping("/id/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") Long organizationId){
        OrganizationDto organizationDto = organizationService.getOrganizationById(organizationId);
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }

    //build get organization by organizationCode REST API
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }

    //build get all organizations
    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    //Build update organization REST API
    //http://localhost:8083/api/organizations/1
    @PutMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") Long organizationId,
                                                          @RequestBody @Valid OrganizationDto organization){
        organization.setId(organizationId);
        OrganizationDto updatedOrganization = organizationService.updateOrganization(organization);
        return new ResponseEntity<>(updatedOrganization, HttpStatus.OK);
    }

    //Build delete organization REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("id") Long organizationId){
        organizationService.deleteOrganization(organizationId);
        return new ResponseEntity<>("Organization sucessfully deleted!", HttpStatus.OK);
    }

}
