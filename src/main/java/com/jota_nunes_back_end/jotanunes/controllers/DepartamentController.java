package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.DepartamentDto;
import com.jota_nunes_back_end.jotanunes.enums.Departament;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departaments")

public class DepartamentController {
    @GetMapping
    public List<DepartamentDto> listDepartaments() {
        return Arrays.stream(Departament.values())
                .map(dep -> new DepartamentDto(format(dep.name()), dep.name()))
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
}
