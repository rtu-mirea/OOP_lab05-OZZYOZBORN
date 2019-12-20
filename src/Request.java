
class Request {

    private Client requester;
    private String product;
    private int price;
    private int count;
    private int type;

    Request (Client requester, String product, int price, int count, int type) {
        this.requester = requester;
        this.product = product;
        this.price = price;
        this.count = count;
        this.type = type;
    }

    Client getRequester() {
        return requester;
    }

    String getProduct() {
        return product;
    }

    int getPrice() {
        return price;
    }

    int getCount() {
        return count;
    }

    int getType() {
        return type;
    }
}