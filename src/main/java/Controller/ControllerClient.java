package Controller;

import DAO.ClientsDAO;
import Interaction.InputManager;
import Model.Client;
import Model.Product;
import View.AbstractView;

import java.util.HashMap;

public class ControllerClient {
    private AbstractView view;
    private InputManager input;
    private ClientsDAO dao;

    private Client client;
    private HashMap<Product, Integer> basket = client.getBasket();

    public ControllerClient(AbstractView view, InputManager input, ClientsDAO dao) {
        this.view = view;
        this.input = input;
        this.dao = dao;
    }

    public void addToBasket(Product product, Integer quantity) {
        if (basket.containsKey(product)) {
            int count = basket.get(product) + quantity;
            basket.put(product, count);
        } else {
            basket.put(product, quantity);
        }
    }

    public void clearBasket() {
        basket.clear();
    }

    public HashMap<Product, Integer> getBasket() {
        return client.getBasket();
    }

    public boolean logIn(String login, String password) {
        if (dao.checkIsClient(login, password)) {
            client = dao.getClient(login, password);
            return true;
        } else {
            return false;
        }
    }

    public void addUser(String[] clientToAdd) {
        dao.addClient(clientToAdd);
    }

    public void removeFromBasket(Product productToRemove) {

        basket.remove(productToRemove);
    }

    public void resetPassword() {

        String newPassword = input.getStringInput("Please provide a new password: ");
        String newPasswordRepeat = input.getStringInput("Please repeat your new password: ");
        if (newPassword.equals(newPasswordRepeat)) {
            String[] data = client.getDataAboutClient();
            data[4] = newPassword;
            dao.updateClient(client.getID(), data);
            view.print("Password has changed successful.");
            return;
        }
        view.print("Passwords were different! Password hasn't changed.");
    }

    public Client getClient() {
        return client;
    }
}
