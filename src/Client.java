

class Client extends User {

    Client(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    String getMsg() {
        return this.msg;
    }

    void takeRequest(Request request) {
        if (request.getType() == 0)
            msg += "Уважаемый " + request.getRequester().getName() + ", ваша заявка на продажу продукта "
                    + request.getProduct() + " была удовлетворена\n";
        else
            msg += "Уважаемый " + request.getRequester().getName() + ", ваша заявка на покупку продукта "
                    + request.getProduct() + " была удовлетворена\n";
    }
}