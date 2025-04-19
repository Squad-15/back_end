package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.*;
import com.jota_nunes_back_end.jotanunes.enums.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metadata")

public class MetadataController {
    @GetMapping("/departaments")
    public List<DepartamentDto> listDepartaments() {
        return Arrays.stream(Departament.values())
                .map(dep -> new DepartamentDto(format(dep.name()), dep.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/profiles")
    public List<JobTitleDto> jobTitleDto() {
        return Arrays.stream(ProfileName.values())
                .map(job -> new JobTitleDto(format(job.name()), job.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/typeconnection")
    public List<TypeConnectionDto> typeConnectionDto() {
        return Arrays.stream(TypeConnection.values())
                .map(job -> new TypeConnectionDto(format(job.name()), job.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/roleuser")
    public List<RoleUserDto> roleUserDto() {
        return Arrays.stream(RoleUser.values())
                .map(job -> new RoleUserDto(format(job.name()), job.name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/location")
    public List<LocationDto> locationDto() {
        return Arrays.stream(Location.values())
                .map(job -> new LocationDto(format(job.name()), job.name()))
                .collect(Collectors.toList());
    }

    private String format(String nomeEnum) {
        String formated = nomeEnum.replace("_", " ").toLowerCase();

        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : formated.toCharArray()) {
            if (capitalizeNext && Character.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(c);
            }

            if (c == ' ') {
                capitalizeNext = true;
            }
        }
        return sb.toString();
    }

    private String formatLocation(String nameEnum) {
        String formatted = nameEnum.replace("_", "-").toLowerCase();
        StringBuilder sb = new StringBuilder();

        boolean capitalizeNext = true;

        for (char c : formatted.toCharArray()) {
            if (capitalizeNext && Character.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(c);
            }

            if (c == '-') {
                capitalizeNext = true;
            }
        }

        return sb.toString();
    }
}
