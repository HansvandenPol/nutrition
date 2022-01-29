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
  private Integer quantity;

  @Column(name = "enercc_kcal")
  private Integer kcal;

  @Column(name = "prot_g")
  private Integer proteinTotal;

  @Column(name = "protpl_g")
  private Integer proteinPlantBased;

  @Column(name = "protan_g")
  private Integer proteinAnimalBased;

  @Column(name = "cho_g")
  private Integer carbsTotal;

  @Column(name = "sugar_g")
  private Integer carbsSugar;

  @Column(name = "starch_g")
  private Integer carbsStarch;

  @Column(name = "FIBT_g")
  private Integer fiber;

  @Column(name = "fat_g")
  private Integer fatTotal;

  @Column(name = "facid_g")
  private Integer fattyAcidsTotal;

  @Column(name = "fasat_g")
  private Integer fatSaturated;

  @Column(name = "famscis_g")
  private Integer fatUnsaturatedMono;

  @Column(name = "fapu_g")
  private Integer fatUnsaturatedPoly;

  @Column(name = "FAPUN3_g")
  private Integer fatOmega3;

  @Column(name = "FAPUN6_g")
  private Integer fatOmega6;

  @Column(name = "FATRS_g")
  private Integer transfets;

}
