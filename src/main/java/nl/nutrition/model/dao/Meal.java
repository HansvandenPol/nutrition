package nl.nutrition.model.dao;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "meal")
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "user_id")
  @NonNull
  private int userId;

  @Column(name = "meal_name")
  @Size(min = 1, max = 30)
  @NonNull
  private String mealName;

  @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private List<MealProduct> mealProducts;
}
