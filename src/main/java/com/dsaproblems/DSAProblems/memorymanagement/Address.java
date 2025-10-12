package com.dsaproblems.DSAProblems.memorymanagement;

import java.util.Objects;

public class Address {

	private String city;
	private String country;
	private String number;
	private String streeName;
	private String zipCode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreeName() {
		return streeName;
	}

	public void setStreeName(String streeName) {
		this.streeName = streeName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, number, streeName, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(number, other.number) && Objects.equals(streeName, other.streeName)
				&& Objects.equals(zipCode, other.zipCode);
	}

}
