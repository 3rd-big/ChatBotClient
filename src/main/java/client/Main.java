package client;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java -jar ChatBotClient.jar [Server IP] [port]");
            return;
        }

        String ip = args[0];
        int port = Integer.parseInt(args[1]);

        new ChatBotClient(ip, port).start();
    }
}
