package org.springframework.data.rest.example;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Version
	private Long version;
	@OneToMany
	private List<Address> addresses;
	@OneToMany
	private List<Profile> profiles;

	public Person() {
	}

	public Person(String name, List<Address> addresses, List<Profile> profiles) {
		this.name = name;
		this.addresses = addresses;
		this.profiles = profiles;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@JsonProperty("addresses")
	public List<String> getAddressIds() {
		List<String> res = new ArrayList<>();
		if (addresses != null)
			for (Address address : addresses) {
				res.add("/" + address.getId());
			}
		return res.isEmpty() ? null : res;
	}

	@JsonProperty("addresses2")
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@JsonProperty("profiles")
	public List<String> getProfileIds() {
		List<String> res = new ArrayList<>();
		if (profiles != null)
			for (Profile profile : profiles) {
				res.add("/" + profile.getId());
			}
		return res.isEmpty() ? null : res;
	}

	@JsonProperty("profiles2")
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

}
