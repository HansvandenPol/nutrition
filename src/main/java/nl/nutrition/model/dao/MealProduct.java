package nl.nutrition.model.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MealProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NonNull
  @Min(0)
  @Max(100000)
  private double quantity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "meal_id", nullable = false)
  private Meal meal;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "product_id", referencedColumnName = "productcode")
  private Product product;
}
