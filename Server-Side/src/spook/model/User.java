package spook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
public class User {
	
	private Long id;
	
	private String profileName;
	
	private String userName;
	
	private String paswd;
	
	private String connectionIp;
	
	private Boolean isConnect;
		
	public User() {	}

	public User(String profileName, String userName, String paswd) {
		this.profileName = profileName;
		this.userName = userName;
		this.paswd = paswd;
	}
	
	@Column(nullable = false, unique = true)
	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	@Column(nullable = false, unique = true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(nullable = false)
	public String getPaswd() {
		return paswd;
	}

	public void setPaswd(String paswd) {
		this.paswd = paswd;
	}

	public String getConnectionIp() {
		return connectionIp;
	}

	public void setConnectionIp(String connectionIp) {
		this.connectionIp = connectionIp;
	}

	public Boolean getIsConnect() {
		return isConnect;
	}

	public void setIsConnect(Boolean isConnect) {
		this.isConnect = isConnect;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id" , nullable = false, unique = true)
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
