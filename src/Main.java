public class Main {
    public static void main(String[] args) {        /*

        RegistrationForm myForm = new RegistrationForm(null);
        User user = myForm.user;
        if (user != null){
            System.out.println("Succesful registration of: " + user.name);
        }else {
            System.out.println("Registration canceled");
        }         */

        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if(user != null){
            System.out.println("Successful Authentication of: ");
            System.out.println("     Name:      " + user.name);
            System.out.println("     Email:     " + user.email);
            System.out.println("     Password:  " + user.password);
        }else{
            System.out.println("Authentication canceled");
        }

    }
}
