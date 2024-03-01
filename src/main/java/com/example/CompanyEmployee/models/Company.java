package com.example.CompanyEmployee.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @Author Jafar Hussein
 * @Class Company
 * @Description: Denna klassen är en modell för företaget. Den innehåller attribut som id, företagsnamn, stad och en lista av anställda.
 * @Realtion: Klassen har en ManyToOne-relation med City-klassen och en OneToMany-relation med Employee-klassen.
 * */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @ManyToOne
    private City city;
   @OneToOne
    private User user;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company(Long id, String companyName, City city) {
        this.id = id;
        this.companyName = companyName;
        this.city = city;
    }
}
