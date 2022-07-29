package nl.nutrition.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "nutrition")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "productcode")
  private Long productCode;

  @NonNull
  @Column(name = "productgroepcode")
  private Integer productGroupCode;

  @NonNull
  @Column(name = "productgroep_oms")
  private String productGroupDescription;

  @NonNull
  @Column(name = "product_omschrijving")
  private String productDescriptionNl;

  @NonNull
  @Column(name = "product_description")
  private String productDescriptionEn;

  @NonNull
  @Column(name = "product_synoniemen")
  private String productSynonym;

  @NonNull
  @Column(name = "meeteenheid")
  private String measureUnit;

  @NonNull
  @Column(name = "hoeveelheid")
  private Double quantity;

  @NonNull
  @Column(name = "enercc_kcal")
  private Double kcal;

  @NonNull
  @Column(name = "prot_g")
  private Double proteinTotal;

  @NonNull
  @Column(name = "protpl_g")
  private Double proteinPlantBased;

  @NonNull
  @Column(name = "protan_g")
  private Double proteinAnimalBased;

  @NonNull
  @Column(name = "cho_g")
  private Double carbsTotal;

  @NonNull
  @Column(name = "sugar_g")
  private Double carbsSugar;

  @NonNull
  @Column(name = "starch_g")
  private Double carbsStarch;

  @NonNull
  @Column(name = "FIBT_g")
  private Double fiber;

  @NonNull
  @Column(name = "fat_g")
  private Double fatTotal;

  @NonNull
  @Column(name = "facid_g")
  private Double fattyAcidsTotal;

  @NonNull
  @Column(name = "fasat_g")
  private Double fatSaturated;

  @NonNull
  @Column(name = "famscis_g")
  private Double fatUnsaturatedMono;

  @NonNull
  @Column(name = "fapu_g")
  private Double fatUnsaturatedPoly;

  @NonNull
  @Column(name = "FAPUN3_g")
  private Double fatOmega3;

  @NonNull
  @Column(name = "FAPUN6_g")
  private Double fatOmega6;

  @NonNull
  @Column(name = "FATRS_g")
  private Double transfets;
}
