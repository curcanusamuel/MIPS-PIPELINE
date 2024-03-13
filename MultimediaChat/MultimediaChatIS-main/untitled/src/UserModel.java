public class UserModel {
    private String userName;
    private String password;
    private String email;
    private byte[] pic;//BLOB type in sql


    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public UserModel(String userName, String password, String email, byte[] pic) {
        this.userName = userName;
        this.password=password;
        this.email=email;
        this.pic=pic;
    }
    public UserModel(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
