package com.udemy.spring.organizationservice.service.impl;


import com.udemy.spring.organizationservice.dto.OrganizationDto;
import com.udemy.spring.organizationservice.entity.Organization;
import com.udemy.spring.organizationservice.exception.ResourceNotFoundException;
import com.udemy.spring.organizationservice.mapper.AutoOrganizationMapper;
import com.udemy.spring.organizationservice.repository.OrganizationRepository;
import com.udemy.spring.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

//    private RestTemplate restTemplate;
    private WebClient webClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        //Convert UserDto into User JPA Entity
        //Mapstruct method
        Organization organization = AutoOrganizationMapper.MAPPER.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);

        //Convert User JPA entity to UserDto
        //Mapstruct method
        OrganizationDto savedOrganizationDto = AutoOrganizationMapper.MAPPER.mapToOrganizationDto(savedOrganization);
        return savedOrganizationDto;
    }

    @Override
    public OrganizationDto getOrganizationById(Long organizationId) {

        LOGGER.info("inside getOrganizationById() method");

        Organization organization = organizationRepository.findById(organizationId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", organizationId)
        );

        //Mapstruct method
        OrganizationDto organizationDto = AutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization);


        return organizationDto;
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();

        //Mapstruct method
        return organizations.stream().map((organization) -> AutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto updateOrganization(OrganizationDto organization) {
        Organization existingOrganization = organizationRepository.findById
                (organization.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Organization", "id", organization.getId())
        );
        existingOrganization.setOrganizationName(organization.getOrganizationName());
        existingOrganization.setOrganizationDescription(organization.getOrganizationDescription());
        existingOrganization.setOrganizationCode(organization.getOrganizationCode());
        Organization updatedOrganization = organizationRepository.save(existingOrganization);

        //Mapstruct method
        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(updatedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return AutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }


    @Override
    public void deleteOrganization(Long organizationId) {
        Organization existingOrganization = organizationRepository.findById(organizationId).orElseThrow(
                () -> new ResourceNotFoundException("Organization", "id", organizationId)
        );
        organizationRepository.deleteById(organizationId);
    }
}
