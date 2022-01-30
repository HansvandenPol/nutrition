package nl.nutrition.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "nutrition")
public class Product {
  @Id
  @Column(name = "productcode")
  private Long productCode;

  @Column(name = "productgroepcode")
  private Integer productGroupCode;

  @Column(name = "productgroep_oms")
  private String productGroupDescription;

  @Column(name = "product_omschrijving")
  private String productDescriptionNl;

  @Column(name = "product_description")
  private String productDescriptionEn;

  @Column(name = "product_synoniemen")
  private String productSynonym;

  @Column(name = "meeteenheid")
  private String measureUnit;

  @Column(name = "hoeveelheid")
  private Double quantity;

  @Column(name = "enercc_kcal")
  private Double kcal;

  @Column(name = "prot_g")
  private Double proteinTotal;

  @Column(name = "protpl_g")
  private Double proteinPlantBased;

  @Column(name = "protan_g")
  private Double proteinAnimalBased;

  @Column(name = "cho_g")
  private Double carbsTotal;

  @Column(name = "sugar_g")
  private Double carbsSugar;

  @Column(name = "starch_g")
  private Double carbsStarch;

  @Column(name = "FIBT_g")
  private Double fiber;

  @Column(name = "fat_g")
  private Double fatTotal;

  @Column(name = "facid_g")
  private Double fattyAcidsTotal;

  @Column(name = "fasat_g")
  private Double fatSaturated;

  @Column(name = "famscis_g")
  private Double fatUnsaturatedMono;

  @Column(name = "fapu_g")
  private Double fatUnsaturatedPoly;

  @Column(name = "FAPUN3_g")
  private Double fatOmega3;

  @Column(name = "FAPUN6_g")
  private Double fatOmega6;

  @Column(name = "FATRS_g")
  private Double transfets;

}
