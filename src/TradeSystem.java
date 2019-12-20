
import javax.swing.*;
import java.util.*;

class TradeSystem {
    static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Request> requests = new ArrayList<>();
    private static ArrayList<Request> approvedRequests = new ArrayList<>();
    static User currentUser;

    static void addUser(String name, String login, String password) {
        users.add(new Client(name, login, password));
    }

    static void addRequest(Client requester, String product, int price, int count, int type) {
        requests.add(new Request(requester, product, price, count, type));
    }

    static User findUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    static void processRequests() {
        String currentProduct;
        int currentPrice;
        int currentCount;
        boolean flag = false;
        for (int i = 0; i < requests.size(); i++) {
            currentPrice = requests.get(i).getPrice();
            currentCount = requests.get(i).getCount();
            currentProduct = requests.get(i).getProduct();
            if (requests.get(i).getType() == 0) {
                for (Request request : requests) {
                    if (flag) {
                        i--;
                        flag = false;
                    }
                    if (request.getProduct().equals(currentProduct) && request.getType() == 1) {
                        if (request.getPrice() >= currentPrice) {
                            if (currentCount - request.getCount() >= 0) {
                                currentCount -= request.getCount();
                                approvedRequests.add(request);
                                requests.remove(request);
                                if (currentCount == 0) {
                                    approvedRequests.add(requests.get(i));
                                    requests.remove(requests.get(i));
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        approvedRequests.forEach(R -> R.getRequester().takeRequest(R));
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new TradeSystem();
        new InitialWindow();
    }
}