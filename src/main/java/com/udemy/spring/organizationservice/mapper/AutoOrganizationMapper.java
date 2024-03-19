package com.udemy.spring.organizationservice.mapper;

import com.udemy.spring.organizationservice.dto.OrganizationDto;
import com.udemy.spring.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoOrganizationMapper {

    AutoOrganizationMapper MAPPER = Mappers.getMapper(AutoOrganizationMapper.class);
    OrganizationDto mapToOrganizationDto(Organization organization);
    Organization mapToOrganization(OrganizationDto organizationDto);
}
