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
  private Integer protein;

  @Column(name = "protpl_g")
  private Integer protplG;

  @Column(name = "protan_g")
  private Integer protanG;

  @Column(name = "cho_g")
  private Integer choG;

  @Column(name = "sugar_g")
  private Integer sugarG;

  @Column(name = "starch_g")
  private Integer starchG;

  @Column(name = "FIBT_g")
  private Integer fibG;

  @Column(name = "fat_g")
  private Integer fatG;

  @Column(name = "facid_g")
  private Integer facidG;

  @Column(name = "famscis_g")
  private Integer famscisG;

  @Column(name = "fapu_g")
  private Integer fapuG;

  @Column(name = "FAPUN3_g")
  private Integer fapun3G;

  @Column(name = "FAPUN6_g")
  private Integer fapun6G;

  @Column(name = "FATRS_g")
  private Integer fatrsG;

}
