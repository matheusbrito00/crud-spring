package com.brito.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.brito.enums.Category;
import com.brito.enums.converters.CategoryConverter;
import com.brito.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
@SQLDelete(sql = "UPDATE courses SET status = 'INACTIVE' WHERE id = ?")
@SQLRestriction("status = 'ACTIVE'") // @Where(clause = "status = 'ACTIVE'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    // @Length(max = 10)
    // @Pattern(regexp = "BACK-END|FRONT-END")
    // @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    // @Length(max = 10)
    // @Pattern(regexp = "ACTIVE|INACTIVE")
    // @JsonIgnore
    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private String status = "ACTIVE";

}
