package poly.agile.webapp.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Profile {
	
	private String fullname;

	private String address;
	
	private Date birthdate;
	
	private Boolean gender;

}
