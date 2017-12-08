package oauth.entity;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "oauth_user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;

    private String username;

    private String password;

    private String status;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name="oauth_user_role",
            joinColumns={ @JoinColumn(name="uid",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="rid",referencedColumnName="id")})
    private List<Role> list = new ArrayList<Role>();


    public List<Role> getList() {
        return list;
    }

    public void setList(List<Role> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
