package Lab3;

import java.io.Serializable;

class User implements Serializable {

    String msg = "";
    String name;
    String login;
    String password;

    User () {}

    boolean Enter (String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    String getLogin() {
        return this.login;
    }

    String getName() {
        return this.name;
    }

    String getMsg() {
        return this.msg;
    }
}