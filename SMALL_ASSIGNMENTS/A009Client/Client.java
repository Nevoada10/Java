package A009Client;

public class Client {

    private String accountNum;
    private String name;
    private String surname;
    private String nationality = "ES";
    private String phone = "NOT_PROVIDED";
    private String dni;
    private byte age = 18;
    private float debts = 0;
    private boolean active = true;

    // Constructor
    public Client(String accountNum, String name, String surname, String nationality, 
        String phone, String dni, byte age)  
    {
        this.accountNum = accountNum;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.phone = phone;
        this.dni = dni;
        this.age = age;
        this.debts = 0;
        this.active = true;
    }

    public Client(String accountNum, String name, String surname, String dni) {
        this.accountNum = accountNum;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
    }


     // Getters y Setters
     public String getAccount() {
        return accountNum;
    }
    
    public void setAccount(String accountNum) {
        this.accountNum = accountNum;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public byte getAge() {
        return age;
    }
    
    public void setAge(byte age) {
        this.age = age;
    }
    
    public float getDebts() {
        return debts;
    }
    
    public void setDebts(float debts) {
        this.debts = debts;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean cancelDebts() {
        if (active == true) {
            debts = 0;
            return true;
        }
        return false;
    }


    public boolean reduceDebts(float amount) {
        if (active == true && amount >=0 ) {
            if (amount >= debts) {
                debts = 0;
                return true;
            }
            else {
                debts -= amount;
            }
            return true;
        }
        return false;
    }


    public boolean increaseDebts (float amount) {
        if (active == true && amount > 0 ) {
            debts += amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Client [accountNum=" + accountNum + ", name=" + name + 
               ", surname=" + surname + ", nationality=" + nationality + 
               ", phone=" + phone + ", dni=" + dni + ", age=" + age + 
               ", debts=" + debts + ", active=" + active + "]";
    }

}
