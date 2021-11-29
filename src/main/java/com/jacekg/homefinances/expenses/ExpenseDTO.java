package com.jacekg.homefinances.expenses;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@JsonDeserialize(as = ConstantExpenseDTO.class)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
  @Type(value = Bike.class, name = "bike"), 
  @Type(value = Auto.class, name = "auto"), 
  @Type(value = Car.class, name = "car")
})
public abstract class ExpenseDTO {
	
	private Long id;
	
	@NotNull(message = "wymagane")
	private String name;
	
	@NotNull(message = "wymagane")
	@Min(value = 0)
	@Max(value = 1000000)
	private int plannedAmount;
	
	private int currentAmount;

}
