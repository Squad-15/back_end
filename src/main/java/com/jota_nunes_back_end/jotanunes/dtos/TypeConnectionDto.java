package com.jota_nunes_back_end.jotanunes.dtos;

public class TypeConnectionDto {
    private String label;
    private String value;

    public TypeConnectionDto(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
