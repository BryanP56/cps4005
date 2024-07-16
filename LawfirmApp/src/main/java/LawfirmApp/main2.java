package LawfirmApp;

import LawfirmApp.dao.ClientDAO;
import LawfirmApp.model.Client;

import java.util.List;
import java.util.Scanner;

public class main {
    private static Scanner scanner = new Scanner(System.in);
    private static ClientDAO clientDAO = new ClientDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Client");
            System.out.println("2. Retrieve Client");
            System.out.println("3. Update Client");
            System.out.println("4. Delete Client");
            System.out.println("5. List All Clients");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createClient();
                    break;
                case 2:
                    retrieveClient();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    listAllClients();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createClient() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        Client client = new Client(0, name, email);
        clientDAO.createClient(client);
    }

    private static void retrieveClient() {
        System.out.println("Enter client ID:");
        int id = scanner.nextInt();
        Client client = clientDAO.getClient(id);
        if (client != null) {
            System.out.println("Client ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Email: " + client.getEmail());
        } else {
            System.out.println("Client not found.");
        }
    }

    private static void updateClient() {
        System.out.println("Enter client ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new email:");
        String email = scanner.nextLine();
        Client client = new Client(id, name, email);
        clientDAO.updateClient(client);
    }

    private static void deleteClient() {
        System.out.println("Enter client ID:");
        int id = scanner.nextInt();
        clientDAO.deleteClient(id);
    }

    private static void listAllClients() {
        List<Client> clients = clientDAO.getAllClients();
        for (Client client : clients) {
            System.out.println("Client ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Email: " + client.getEmail());
            System.out.println("------");
        }
    }
}