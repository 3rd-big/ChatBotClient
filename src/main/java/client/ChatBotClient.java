package client;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;


public class ChatBotClient {

    final private String IP;
    final private int PORT;

    public ChatBotClient(String IP, int PORT) {
        this.IP = IP;
        this.PORT = PORT;
    }

    void start() {

        try {
            Socket clientSocket = new Socket(this.IP, this.PORT);

            InputStreamReader inK = new InputStreamReader(System.in);
            BufferedReader keyBoard = new BufferedReader(inK);

            OutputStream out = clientSocket.getOutputStream();
            OutputStreamWriter outW = new OutputStreamWriter(out);
            PrintWriter pw = new PrintWriter(outW);

            InputStream in = clientSocket.getInputStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inR);

            while (true) {
                String receivedMsg = br.readLine();
                if (receivedMsg.equals("Disconnect")) {
                    System.out.println("서버와의 연결을 종료 합니다.");
                    break;
                } else if (receivedMsg.equals("input")) {
                    String line = keyBoard.readLine();
                    if (line.equals("quit")) {
                        System.out.println("서버와의 연결을 종료 합니다.");
                        clientSocket.close();
                        break;
                    }
                    pw.println(line);
                    pw.flush();
                    continue;
                }
                System.out.println(receivedMsg);
            }

            keyBoard.close();
            pw.close();
            br.close();

        } catch (ConnectException e) {
            System.out.println("연결 포트를 확인해주세요");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
