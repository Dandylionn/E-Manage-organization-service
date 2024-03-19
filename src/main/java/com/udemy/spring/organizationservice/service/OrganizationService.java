package com.udemy.spring.organizationservice.service;

import com.udemy.spring.organizationservice.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService
{
    OrganizationDto saveOrganization(OrganizationDto organization);
    OrganizationDto getOrganizationById(Long organizationId);
    List<OrganizationDto> getAllOrganizations();
    OrganizationDto updateOrganization(OrganizationDto organization);

    OrganizationDto getOrganizationByCode(String organizationCode);

    void deleteOrganization(Long organizationId);
}
