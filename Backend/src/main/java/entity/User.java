package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name="email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="age")
    private int age;
    
    
    public User(){
    }
    
    public User(String email, String firstName, String lastName, int age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
//    @Column(name = "user_pass")
//    private String userPass;
//    @JoinTable(name = "user_roles", joinColumns = {
//        @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
//        @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
//    @ManyToMany
//    private List<Role> roleList = new ArrayList();

//    public List<String> getRolesAsStrings() {
//        if (roleList.isEmpty()) {
//            return null;
//        }
//        List<String> rolesAsStrings = new ArrayList();
//        for (Role role : roleList) {
//            rolesAsStrings.add(role.getRoleName());
//        }
//        return rolesAsStrings;
//    }

//    //TODO Change when password is hashed
//    public boolean verifyPassword(String pw) {
//        return (BCrypt.checkpw(pw, userPass));
//    }
//
//    public User(String userName, String userPass) {
//        this.userName = userName;
//        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt(10));
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserPass() {
//        return this.userPass;
//    }
//
//    public void setUserPass(String userPass) {
//        this.userPass = userPass;
//    }
//
//    public List<Role> getRoleList() {
//        return roleList;
//    }
//
//    public void setRoleList(List<Role> roleList) {
//        this.roleList = roleList;
//    }
//
//    public void addRole(Role userRole) {
//        roleList.add(userRole);
//    }


}
