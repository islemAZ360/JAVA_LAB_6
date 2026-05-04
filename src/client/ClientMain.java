package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import common.Response;
import common.models.Const;

public class ClientMain {
    public static void main(String[] args) {
        // Use try-with-resources to auto close socket when error
        // Init output first to avoid Deadlock (server and client will be waiting header for each other => all hang)
        try (Socket socket = new Socket(Const.host, Const.port);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             // Only this one communicates with Socket (read header and data => ois)
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             RequestSender reqSender = new RequestSender(dos, dis);
             Scanner scanner = new Scanner(System.in)) {

            InputManager inputMng = new InputManager(scanner, reqSender);

            System.out.println("✅ Connected to server successfully!");
            System.out.printf(Const.GREEN + Const.cat + Const.RESET);

            // 2. Send Header instantly to server to get handshake response
            // Translate byte[] to obj in deserialize
            // ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            // oos.flush();

            while (true) {
                System.out.println("\nПрограмма готова к работе! Введите 'help' для подержки || 'exit' для выхода.");
                System.out.println(">>> \uD83E\uDD16 Что вы думаете? Чем я могу помочь?");
                System.out.print(">>> ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) break;

                // Handles one or multiple spaces perfectly
//                String[] arguments = input.split("\\s+");

//  ===========================================================================================
                Response resp = inputMng.handleCommand(input);

//                // 1. Create Request object
//                Request req = new Request(
//                        arguments[0],
//                        arguments.length>1? arguments[1]:null,
//                        "hello");
//
//                Response resp = reqSender.sendRequest(req);
                System.out.println("📩 Server response:\n" + resp.getMessage());
//  ===========================================================================================
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.err.println("❌ Connecting error: Server busy or not response. (If server is not running, start server first!)");
        } catch (Exception e) {
            System.out.printf("❌ Some errors were occurred: %s.\n", e.getMessage());
        }
    }
}
