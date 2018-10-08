package poly.agile.webapp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), 
	inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles;

	@Column(name = "FULL_NAME", length = 45)
	private String fullname;

	@Column(name = "USERNAME", length = 45, unique = true)
	private String username;

	@Column(name = "PASSWORD", length = 128)
	private String password;

	@Column(name = "EMAIL", length = 45, unique = true)
	private String email;

	@Column(name = "ADDRESS", length = 255)
	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Column(name = "ENABLED", length = 45)
	private Boolean enabled;

	@Column(name = "GENDER")
	private Boolean gender;

	@Column(name = "PHONE_NUMBER", length=15)
	private String phoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", insertable = false, updatable = false)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TIME", insertable = false, updatable = false)
	private Date updatedTime;

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Order> orders;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		roles.forEach(e -> authorities.add(new SimpleGrantedAuthority("ROLE_" + e.getName())));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}