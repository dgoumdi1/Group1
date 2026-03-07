
package com.example.productivitybuddy.model;

import jakarta.persistence.*;


@Entity
public class Planet {
	
	//declaring variables of planet object
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;//The Planet's name

	@Column(nullable = false)
	private Integer unlockHours;//The hours it takes to unlock planet
	
	
	public Planet() {}
	
		public Planet(String name,Integer unlockHours) {
			this.name=name;
			this.unlockHours=unlockHours;
		}
		
		public Long getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public Integer getHours() {
			return unlockHours;
		}
		
		
		public void setName(String name) {
			this.name=name;
		}
		
		public void setUnlockHours(Integer unlockHours) {
			this.unlockHours=unlockHours;
		}
		
	
}
